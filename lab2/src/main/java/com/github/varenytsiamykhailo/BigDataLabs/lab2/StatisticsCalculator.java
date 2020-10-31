package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import org.apache.hadoop.io.Text;

import java.util.Iterator;

public class StatisticsCalculator {

    private double minDelay = Double.MAX_VALUE;

    private double maxDelay = 0.0;

    private double sumOfDelays = 0;

    private int countOfDelays = 0;

    private double averageDelay = 0;

    public StatisticsCalculator(Iterable<Text> values) {
        calculateStatistics(values);
    }

    public double getMinDelay() {
        return minDelay;
    }

    public double getMaxDelay() {
        return maxDelay;
    }

    public double getSumOfDelays() {
        return sumOfDelays;
    }
s
    public int getCountOfDelays() {
        return countOfDelays;
    }

    public double getAverageDelay() {
        return averageDelay;
    }

    private void calculateStatistics(Iterable<Text> values) {
        Iterator<Text> iter = values.iterator();
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
        averageDelay = sumOfDelays / countOfDelays;
    }

}
