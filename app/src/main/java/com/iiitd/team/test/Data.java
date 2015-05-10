package com.iiitd.team.test;

import android.location.Location;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hp on 22-03-2015.
 */
public class Data implements Serializable{
    public Location mLocation;
    public String fName;
    public double qty;
    public String expectedDateOfArrival;

    public Location getmLocation() {
        return mLocation;
    }

    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getExpectedDateOfArrival() {
        return expectedDateOfArrival;
    }

    public void setExpectedDateOfArrival(String expectedDateOfArrival) {
        this.expectedDateOfArrival = expectedDateOfArrival;
    }
}
