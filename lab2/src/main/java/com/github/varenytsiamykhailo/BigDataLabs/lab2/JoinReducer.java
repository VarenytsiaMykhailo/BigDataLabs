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
        //Text airportDescription = new Text(iter.next());

        while (iter.hasNext()) {
            Text call = iter.next();
            Text outValue = new Text("+++++++++ call = " + call.toString() + "------------ airportDescription = " + airportDescription.toString() + "/++++++++++");
            context.write(new Text("KeyStart... destAirportId = " + key.getDestAirportId().toString() + " keyFinished...."), outValue);
        }
    }

}
