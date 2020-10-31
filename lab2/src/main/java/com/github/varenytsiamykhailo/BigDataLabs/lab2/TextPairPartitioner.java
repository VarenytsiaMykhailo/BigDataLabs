package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.mapreduce.Partitioner;

public class TextPairPartitioner<K, V> extends Partitioner<K, V> {
    @Override
    public int getPartition(K k, V v, int i) {
        return 0;
    }
}
