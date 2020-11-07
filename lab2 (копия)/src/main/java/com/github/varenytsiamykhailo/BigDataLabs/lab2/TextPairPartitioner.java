package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class TextPairPartitioner extends Partitioner<TextPair, Text> {

    @Override
    public int getPartition(TextPair key, Text value, int numReduceTasks) {
        return (key.getDestAirportId().hashCode() & Integer.MAX_VALUE) % numReduceTasks;
    }

}
