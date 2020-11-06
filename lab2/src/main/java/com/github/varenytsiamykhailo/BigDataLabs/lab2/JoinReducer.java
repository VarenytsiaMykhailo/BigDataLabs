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

       /* StatisticsCalculator statisticsCalculator = new StatisticsCalculator(values);

        String result = "Airport name = " + airportDescription + "; Min delay = " + statisticsCalculator.getMinDelay() +
                "; Max delay = " + statisticsCalculator.getMaxDelay() + "; Average delay =  " + statisticsCalculator.getAverageDelay() + ";";
        // Text outValue = new Text("+++++++++ call = " + call.toString() + "------------ airportDescription = " + airportDescription.toString() + "/++++++++++");

*/
        double minDelay = Double.MAX_VALUE;

        double maxDelay = 0.0;

        double sumOfDelays = 0.0;

        int countOfDelays = 0;

        double averageDelay = 0.0;

        while (iter.hasNext()) {
            double delay = Double.parseDouble(iter.next().toString());
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


        context.write(new Text("AirportId = " + key.getDestAirportId().toString() + "; "), new Text(result));
    }

}
