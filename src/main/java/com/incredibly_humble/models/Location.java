package com.incredibly_humble.models;

/**
 * Created by noam on 10/17/16.
 */
public class Location {
    private double longitude;
    private double latitude;

    public Location(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String toString(){
        return String.format("<%f, %f>",this.latitude, this.longitude);
    }
}
