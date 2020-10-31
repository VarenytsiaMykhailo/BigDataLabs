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

        String result = "Airport name = " + airportDescription + "; Min delay = " + statisticsCalculator.getMinDelay() +
                "; Max delay = " + statisticsCalculator.getMaxDelay() + "; Average delay =  " + statisticsCalculator.getAverageDelay() + ";";
        // Text outValue = new Text("+++++++++ call = " + call.toString() + "------------ airportDescription = " + airportDescription.toString() + "/++++++++++");
        context.write(new Text("AirportId = " + key.getDestAirportId().toString() + "; "), new Text(result));
    }

}
