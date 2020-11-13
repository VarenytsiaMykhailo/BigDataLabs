package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import java.io.Serializable;

public class FlightInfo implements Serializable {

    private Double delay;

    private Boolean canceled; // true if canceled (1.00 value in csv file)

    public FlightInfo(String delayStr) {
        if (delayStr.isEmpty()) {
            this.delay = 0.0;
        } else {
            this.delay = Double.parseDouble(delayStr);
        }
    }
}
