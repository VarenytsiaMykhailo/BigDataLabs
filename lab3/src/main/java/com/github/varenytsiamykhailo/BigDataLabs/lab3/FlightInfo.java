package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import java.io.Serializable;

public class FlightInfo implements Serializable {

    private Double delay = 0.0;

    private Boolean canceled = false; // true if canceled (1.00 value in csv file)

    private Integer canceledFlightsCounter = 0;

    public FlightInfo(String delayStr, String cancelledStr) {
        if (!delayStr.isEmpty()) {
            this.delay = Double.parseDouble(delayStr);
        }
        if (cancelledStr.equals("1.00")) {
            this.canceled = true;
            this.cancelledFlightsCounter++;
        }
    }

    public Double getDelay() {
        return delay;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public Integer getCancelledFlightsCounter() {
        return cancelledFlightsCounter;
    }

    public FlightInfo updateStatistics(FlightInfo newFlightInfoForAccumulating) {
        Double newDelay = newFlightInfoForAccumulating.getDelay();
        if (newDelay > delay) {
            delay = newDelay;
        }
        if (newFlightInfoForAccumulating.getCanceled()) {
            this.cancelledFlightsCounter
        }


        return this;
    }
}
