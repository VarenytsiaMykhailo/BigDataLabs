package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<TextPair, Text, Text, Text> {

    @Override
    protected void reduce(TextPair key, Iterable<Text> values, Context context) throws
            IOException, InterruptedException {

        Iterator<Text> iter = values.iterator();
        String airportDescription = iter.next().toString();

        StatisticsCalculator statisticsCalculator = new StatisticsCalculator(values);


        if (statisticsCalculator.getCountOfDelays() > 0) { // Iterable<Text> values может прийти пустым. Обрабатываем.
            String result = "Airport name = " + airportDescription + "; Min delay = " + statisticsCalculator.getMinDelay() +
                    "; Max delay = " + statisticsCalculator.getMaxDelay() + "; Average delay =  " + statisticsCalculator.getAverageDelay() + ";";
            context.write(new Text("AirportId = " + key.getDestAirportId().toString() + "; "), new Text(result));
        }
/*
        double minDelay = Double.MAX_VALUE;

        double maxDelay = 0.0;

        double sumOfDelays = 0.0;

        int countOfDelays = 0;

        double averageDelay = 0.0;

        String test = "";

        while (iter.hasNext()) {
            String next = iter.next().toString();
            if (!next.isEmpty())
                test = test + next + " ";
            else
                test += "EMPTY ";
            double delay = Double.parseDouble(next);
            if (delay < minDelay) {
                minDelay = delay;
            }
            if (delay > maxDelay) {
                maxDelay = delay;
            }
            sumOfDelays += delay;
            countOfDelays++;
        }
        averageDelay = sumOfDelays / (double)countOfDelays;
*/

        /*String result = "Airport name = " + airportDescription + "; Min delay = " + minDelay +
                "; Max delay = " + maxDelay + "; Average delay =  " + averageDelay + ";";*/
       /* if (countOfDelays > 0) { // Iterable<Text> values может прийти пустым. Обрабатываем.
            String result = "Airport name = " + airportDescription + " TEST = " + test;
            context.write(new Text("AirportId = " + key.getDestAirportId().toString() + "; "), new Text(result));
        }*/
    }

}
