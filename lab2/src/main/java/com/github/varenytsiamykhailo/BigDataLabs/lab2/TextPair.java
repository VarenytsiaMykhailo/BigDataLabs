package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.io.WritableComparable;

import javax.xml.soap.Text;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TextPair implements WritableComparable<TextPair> {


    @Override
    public int compareTo(TextPair o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }
}
