package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.parallelize(Arrays.asList("pandas", "i like pandas"));

        JavaPairRDD<LongWritable, Text> data = sc.textFile("war-and-peace-1.txt", TextInputForыыmat.class, LongWritable.class, Text.class);
        System.out.println("123");
        lines.collect();
        // lines.saveAsTextFile("lab3_result");
    }
}
