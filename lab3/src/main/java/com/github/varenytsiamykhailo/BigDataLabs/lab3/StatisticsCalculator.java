package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import java.io.Serializable;

public class StatisticsCalculator implements Serializable {

    public StatisticsCalculator() {
    }

    public static StatisticsCalculator updateStatistics(FlightInfo flightInfo) {
        return new StatisticsCalculator();
    }

    public static StatisticsCalculator updateStatistics(FlightInfo flightInfo, FlightInfo flightInfo1) {
        return new StatisticsCalculator();
    }
}
