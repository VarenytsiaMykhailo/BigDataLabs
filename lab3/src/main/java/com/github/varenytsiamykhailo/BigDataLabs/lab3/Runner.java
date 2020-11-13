package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class Runner {

    //т.к. отсчет с нуля, то индексы на единицу меньше, чем в самом csv файле

    private static final int AIRPORT_ID_COLUMN_NUMBER = 0; // AIRPORT_ID — Идентификатор аэропорта (code) в airports.csv

    private static final int ORIGIN_AIRPORT_ID_COLUMN_NUMBER = 11; // DEST_AIRPORT_ID — Идентификатор аэропорта отлета

    private static final int DEST_AIRPORT_ID_COLUMN_NUMBER = 14; // DEST_AIRPORT_ID — Идентификатор аэропорта прибытия

    private static final int ARR_DELAY_NEW_COLUMN_NUMBER = 18; // ARR_DELAY_NEW - разница в минутах между расчетным временем приземления и реальным (>=0)

    private static final int CANCELLED_COLUMN_NUMBER = 19; // CANCELLED — признак отмены рейса (1 в случае отмены)


    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsDataRDD = sc.textFile("airports.csv");
        JavaRDD<String> flightsDataRDD = sc.textFile("flights.csv");

        JavaPairRDD<Long, String> airportsInfoRDD = airportsDataRDD.mapToPair(
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

        JavaPairRDD<String, String> resultStatistic = flightsStatisticRDD.mapToPair(
                e -> {
                    String name = " " + e._1._1 + e._1._2 + " ";
                    String resStat = " total flights: " + e._2.getTotalFlights() + " max delay: " + e._2.getDelay() + " ratio: " + e._2.calculateCancelledAndDelayedRatio();
                    return new Tuple2<>(name, resStat);
                }
        );

        //System.out.println("123");
        //lines.collect();
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        //System.out.println(lines.collect());
        resultStatistic.saveAsTextFile("test_result");
    }
}
