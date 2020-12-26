package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.io.WritableComparable;

import javax.xml.soap.Text;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class TextPair implements WritableComparable<TextPair> {

    private Integer destAirportId;

    private Integer fileNumber;

    public TextPair() {
        super();
    }

    public TextPair(Integer destAirportId, Integer fileNumber ) {
        super();
        this.destAirportId = destAirportId;
        this.fileNumber = fileNumber;
    }

    public Integer getDestAirportId() {
        return destAirportId;
    }

    public Integer getFileNumber() {
        return fileNumber;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(destAirportId);
        dataOutput.writeInt(fileNumber);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.destAirportId = dataInput.readInt();
        this.fileNumber = dataInput.readInt(); //??
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextPair textPair = (TextPair) o;
        return destAirportId.equals(textPair.destAirportId) &&
                fileNumber.equals(textPair.fileNumber);
    }
    @Override
    public int hashCode() {
        return Objects.hash(destAirportId, fileNumber);
    }*/

    @Override
    public int compareTo(TextPair o) {
        int cmp = this.destAirportId.compareTo(o.getDestAirportId());
        if(cmp != 0)
            return cmp;
        return this.fileNumber.compareTo(o.getFileNumber());
    }
}
