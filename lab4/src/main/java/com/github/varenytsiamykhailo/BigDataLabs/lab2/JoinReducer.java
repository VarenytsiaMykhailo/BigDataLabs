package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Reducer;
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
            context.write(new Text("Airport id = " + key.getDestAirportId().toString() + "; "), new Text(result));
        }
    }

}
