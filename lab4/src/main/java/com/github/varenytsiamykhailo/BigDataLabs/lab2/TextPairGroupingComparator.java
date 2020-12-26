package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TextPairGroupingComparator extends WritableComparator {

    protected TextPairGroupingComparator() {
        super(TextPair.class, true);
    }

    @Override
    public int compare(WritableComparable obj1, WritableComparable obj2) {
        TextPair textPair1 = (TextPair)obj1;
        TextPair textPair2 = (TextPair)obj2;
        return Integer.compare(textPair1.getDestAirportId(), textPair2.getDestAirportId());
    }

}
