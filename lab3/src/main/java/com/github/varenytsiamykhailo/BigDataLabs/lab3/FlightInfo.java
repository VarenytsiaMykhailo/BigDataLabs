package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import java.io.Serializable;

public class FlightInfo implements Serializable {

    private Double delay = 0.0;

    private Boolean cancelled = false; // true if canceled (1.00 value in csv file)

    private Integer delayedAndCancelledFlightsCounter = 0;

    private Integer totalFlights = 1; // 1 - т.к. созданный экземпляр уже нужно посчитать

    public FlightInfo(String delayStr, String cancelledStr) {
        if (!delayStr.isEmpty()) {
            this.delay = Double.parseDouble(delayStr);
        }
        if (cancelledStr.equals("1.00")) {
            this.cancelled = true;
        }
    }

    public Double getDelay() {
        return delay;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public Integer getDelayedAndCancelledFlightsCounter() {
        return delayedAndCancelledFlightsCounter;
    }

    public Integer getTotalFlights() {
        return totalFlights;
    }

    public FlightInfo updateStatistic(FlightInfo newFlightInfoForAccumulating) {
        Double newDelay = newFlightInfoForAccumulating.getDelay();
        if (newDelay > delay) {
            delay = newDelay;
        }
        if (newFlightInfoForAccumulating.getCancelled() || newDelay > 0.00001) {
            this.delayedAndCancelledFlightsCounter++;
        }

        totalFlights++;

        return this;
    }

    public double calculateCancelledAndDelayedRatio() {
        return (double) delayedAndCancelledFlightsCounter / (double) totalFlights;
    }
}
