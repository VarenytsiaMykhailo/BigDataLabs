
package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.Arrays;

public class AirportsJoinMapper extends Mapper<LongWritable, Text, TextPair, Text> {

    //т.к. отсчет с нуля, то индексы на единицу меньше, чем в самом csv файле
    private static final int DEST_AIRPORT_ID_COLUMN_NUMBER = 0; // DEST_AIRPORT_ID — Идентификатор аэропорта

    private static final int FILE_NUMBER = 0; // номер входного файла (для этапа Reduce)

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (!key.equals(new LongWritable(0))) { // Пропускаем первую строку csv файла (наименования столбцов)
            String[] columns = value.toString().split(",");

            Integer destAirportId = Integer.parseInt(columns[DEST_AIRPORT_ID_COLUMN_NUMBER].replaceAll("\"",""));

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < columns.length; i++) {
                sb.append(columns[i]);
            }
            String airportDescription = sb.toString();


            System.out.println("$$$$$$$$$$$$$ AirportsJoinMapper: columns:" + Arrays.toString(columns) + " -___column[1] = " + airportDescription + " destAirportId = " + destAirportId);
            context.write(new TextPair(destAirportId, FILE_NUMBER), new Text(airportDescription));  // key, value
        }
    }
}
