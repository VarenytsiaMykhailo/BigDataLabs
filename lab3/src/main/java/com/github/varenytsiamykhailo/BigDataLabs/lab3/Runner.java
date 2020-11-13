package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class Runner {

    //т.к. отсчет с нуля, то индексы на единицу меньше, чем в самом csv файле

    private static final int AIRPORT_ID_COLUMN_NUMBER = 0; // AIRPORT_ID — Идентификатор аэропорта (code) в airports.csv

    private static final int ORIGIN_AIRPORT_ID_COLUMN_NUMBER = 11; // DEST_AIRPORT_ID — Идентификатор аэропорта отлета

    private static final int DEST_AIRPORT_ID_COLUMN_NUMBER = 14; // DEST_AIRPORT_ID — Идентификатор аэропорта прибытия

    private static final int ARR_DELAY_NEW_COLUMN_NUMBER = 18; // ARR_DELAY_NEW - разница в минутах между расчетным временем приземления и реальным (>=0)

    private static final int CANCELLED_COLUMN_NUMBER = 19; // CANCELLED — признак отмены рейса (1 в случае отмены)


    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Use correct paths: <input path A> <input path B> <output path>");
            System.exit(-1);
        }

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsDescriptionDataRDD = sc.textFile(args[0]); // airports.csv
        JavaRDD<String> flightsDataRDD = sc.textFile(args[1]); // flights.csv

        JavaPairRDD<Long, String> airportsDescriptionRDD = airportsDescriptionDataRDD.filter(s -> !s.startsWith("\"Code\",\"Description\"")).mapToPair(
                s -> {
                    String[] columns = s.split(",");
                    Long airportId = Long.parseLong(columns[AIRPORT_ID_COLUMN_NUMBER].replaceAll("\"", ""));
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < columns.length; i++) {
                        sb.append(columns[i]);
                    }
                    return new Tuple2<>(airportId, sb.toString());
                }
        );

        JavaPairRDD<Tuple2<Long, Long>, FlightInfo> flightsInfoRDD = flightsDataRDD.filter(s -> !s.startsWith("\"YEAR\",\"QUARTER\"")).mapToPair(
                s -> {
                    String[] columns = s.replaceAll(" ","").split(",");
                    Long originAirportId =  Long.parseLong(columns[ORIGIN_AIRPORT_ID_COLUMN_NUMBER].replaceAll("\"",""));
                    Long destAirportId =  Long.parseLong(columns[DEST_AIRPORT_ID_COLUMN_NUMBER].replaceAll("\"",""));
                    String delay = columns[ARR_DELAY_NEW_COLUMN_NUMBER];
                    String cancelled = columns[CANCELLED_COLUMN_NUMBER];
                    return new Tuple2<>(new Tuple2<Long, Long>(originAirportId, destAirportId), new FlightInfo(delay, cancelled));
                }
        );

        JavaPairRDD<Tuple2<Long, Long>, FlightInfo> flightsStatisticRDD = flightsInfoRDD.reduceByKey(FlightInfo::updateStatistic);

        Map<Long, String> airportsDescriptionMap = airportsDescriptionRDD.collectAsMap(); // Выкачиваем список аэропортов в глобальный контекст (на все узлы)
        final Broadcast<Map<Long, String>> airportsDescriptionBroadcasted = sc.broadcast(airportsDescriptionMap);

        JavaPairRDD<String, String> resultStatistic = flightsStatisticRDD.mapToPair(
                e -> {
                    String originAirportName = airportsDescriptionBroadcasted.value().get(e._1._1);
                    String destAirportName = airportsDescriptionBroadcasted.value().get(e._1._2);
                    String name =  originAirportName + " ===> " + destAirportName;
                    String resStat = " total flights: " + e._2.getTotalFlights() + " max delay: " + e._2.getDelay() + " ratio: " + e._2.calculateCancelledAndDelayedRatio();
                    return new Tuple2<>(name, resStat);
                }
        );

        resultStatistic.saveAsTextFile(args[2]);
    }
}
