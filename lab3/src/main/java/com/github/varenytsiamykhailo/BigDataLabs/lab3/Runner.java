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
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // JavaRDD<String> lines = sc.parallelize(Arrays.asList("pandas", "i like pandas"));

        JavaRDD<String> airportsData = sc.textFile("airports.csv");
        JavaRDD<String> flightsData = sc.textFile("flights.csv");

//        JavaRDD<String> airportsSplittedData = airportsData.flatMap(
//                (FlatMapFunction<String, String>) s -> Arrays.stream(s.split(",")).iterator()
//        );

       /* JavaRDD<String> flightsSplittedData = flightsData.flatMap(
                (FlatMapFunction<String, String>) s -> Arrays.stream(s.replaceAll(" ","").split(",")).iterator()
        );*/

        JavaPairRDD<Tuple2<Long, Long>, FlightInfo> flightsInfoRDD = flightsData.filter(s -> !s.startsWith("\"YEAR\",\"QUARTER\"")).mapToPair(
                s -> {
                    return new Tuple2<>(new Tuple2<Long, Long>(888L, 999L), new FlightInfo(s));
                }
        );
        //System.out.println("123");
        //lines.collect();
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        //System.out.println(lines.collect());
        flightsInfoRDD.saveAsTextFile("test_result");
    }
}
