package com.github.varenytsiamykhailo.BigDataLabs.lab3;

import java.io.Serializable;

public class FlightInfo implements Serializable {

    private Double delay;

    private Boolean canceled; // true if canceled (1.00 value in csv file)

    public FlightInfo(String delayStr, String cancelledStr) {
        if (delayStr.isEmpty()) {
            this.delay = 0.0;
        } else {
            this.delay = Double.parseDouble(delayStr);
        }
        if (cancelledStr.equals("1.00")) {
            canceled = true;
        }
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }
}
