package com.incredibly_humble.models;

import java.util.Date;

public class WaterSourceReport {
    public enum WaterType {
        Bottled,
        Well,
        Stream,
        Lake,
        Spring,
        Other
    }
    private Date dateReported;
    private int id;
    private String workerName;
    private WaterType type;
    private Location location;

    public WaterSourceReport(int id, Date dateReported, Location location, String workerName, WaterType type) {
        this.dateReported = dateReported;
        this.id = id;
        this.workerName = workerName;
        this.location = location;
        this.type = type;
    }

    public Date getDateReported() {
        if(dateReported != null){
            return dateReported;
        }
        return new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkerName() {
        return workerName;
    }
    public void setWorkerName(String name){
        this.workerName = name;
    }

    public WaterType getType() {
        return type;
    }

    public Location getLocation(){return this.location;}
}
