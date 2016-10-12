package com.incredibly_humble.models;

import java.util.Date;

public class WaterReport {
    public enum WaterType {
        Bottled,
        Well,
        Stream,
        Lake,
        Spring,
        Other
    }

    public enum WaterCondition {
        Waste,
        Treatable_Clear,
        Treatable_Muddy,
        Potable
    }

    private Date dateReported;
    private int id;
    private String workerName;
    private String location;
    private WaterType type;
    private WaterCondition condition;

    public WaterReport(Date dateReported, String location, WaterType type, WaterCondition condition) {
        this.dateReported = dateReported;
        this.id = id;
        this.workerName = workerName;
        this.location = location;
        this.type = type;
        this.condition = condition;
    }

    public Date getDateReported() {
        return dateReported;
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
    public String getLocation() {
        return location;
    }

    public WaterType getType() {
        return type;
    }

    public WaterCondition getCondition() {
        return condition;
    }

}
