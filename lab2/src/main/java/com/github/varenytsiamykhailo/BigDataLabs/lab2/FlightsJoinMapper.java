package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.Arrays;

public class FlightsJoinMapper extends Mapper<LongWritable, Text, TextPair, Text> {

    private static final Integer FILE_NUMBER = 1; // номер входного файла (для этапа Reduce)

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (!key.equals(new LongWritable(0))) { // Пропускаем первую строку csv файла (наименования столбцов)

            FlightsParser flightsParser = new FlightsParser(value);

            Integer destAirportId = flightsParser.parseDestAirportId();
            String delay = flightsParser.parseDelay();

            if (!(delay.isEmpty())) {
                context.write(new TextPair(destAirportId, FILE_NUMBER), new Text(delay));
            }
        }
    }
}

class FlightsParser {

    //т.к. отсчет с нуля, то индексы на единицу меньше, чем в самом csv файле
    private static final int DEST_AIRPORT_ID_COLUMN_NUMBER = 14; // DEST_AIRPORT_ID — Идентификатор аэропорта

    private static final int ARR_DELAY_NEW_COLUMN_NUMBER = 18; // ARR_DELAY_NEW - разница в минутах между расчетным временем приземления и реальным (>=0)

    private String[] columns;

    public FlightsParser(Text value) {
        String[] columns = value.toString().replaceAll(" ","").split(",");
    }

    public Integer parseDestAirportId() {
        return Integer.parseInt(columns[DEST_AIRPORT_ID_COLUMN_NUMBER].replaceAll("\"",""));
    }

    public String parseDelay() {
        return columns[ARR_DELAY_NEW_COLUMN_NUMBER];
    }
}