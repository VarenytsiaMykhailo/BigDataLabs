
package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class AirportsJoinMapper extends Mapper<LongWritable, Text, TextPair, Text> {

    //т.к. отсчет с нуля, то индексы на единицу меньше, чем в самом csv файле
    private static final int DEST_AIRPORT_ID_COLUMN_NUMBER = 0; // DEST_AIRPORT_ID — Идентификатор аэропорта

    private static final int AIRPORT_DESCRIPTION_COLUMN_NUMBER = 1; // DEST_AIRPORT_ID — Идентификатор аэропорта

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (!key.equals(new LongWritable(0))) { // Пропускаем первую строку csv файла (наименования столбцов)
            String[] columns = value.toString().split(",");

            Integer destAirportId = Integer.parseInt(columns[DEST_AIRPORT_ID_COLUMN_NUMBER]);
            String airportDescription = columns[AIRPORT_DESCRIPTION_COLUMN_NUMBER];

            context.write(new TextPair(destAirportId, "0"), new Text(airportDescription));  // key, value
        }
    }
}
