package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import java.io.Serializable;

public class FlightInfo implements Serializable {

    private Double delay = 0.0;

    private Boolean cancelled = false; // true if canceled (1.00 value in csv file)

    private Integer delayedFlightsCounter = 0;

    private Integer cancelledFlightsCounter = 0;

    private Integer totalFlights = 0;

    public FlightInfo(String delayStr, String cancelledStr) {
        if (!delayStr.isEmpty()) {
            this.delay = Double.parseDouble(delayStr);
            // this.delayedFlightsCounter++;
        }
        if (cancelledStr.equals("1.00")) {
            this.cancelled = true;
            // this.cancelledFlightsCounter++;
        }
    }

    public Double getDelay() {
        return delay;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public Integer getDelayedFlightsCounter() {
        return delayedFlightsCounter;
    }

    public Integer getCancelledFlightsCounter() {
        return cancelledFlightsCounter;
    }

    public Integer getTotalFlights() {
        return totalFlights;
    }



    public FlightInfo updateStatistics(FlightInfo newFlightInfoForAccumulating) {
        Double newDelay = newFlightInfoForAccumulating.getDelay();
        if (newDelay > delay) {
            delay = newDelay;
        }
        if (newFlightInfoForAccumulating.getCancelled()) {
            this.cancelledFlightsCounter++;
        }

        totalFlights++;

        return this;
    }

    public Double calculateCancelledAndDelayedRatio() {
        return cancelledFlightsCounter
    }
}
