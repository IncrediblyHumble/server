package com.incredibly_humble.models;

import java.util.Date;

public class WaterQualityReport {
    public enum Condition {
        Safe,
        Treatable,
        Unsafe
    }

    private Date dateReported;
    private int id;
    private String workerName;
    private Location location;
    private Condition condition;
    private int virus;
    private int contaminant;

    public WaterQualityReport(int id, Date dateReported, Location location,
                              String workerName, Condition condition,
                              int virus, int contaminant) {
        this.dateReported = dateReported;
        this.id = id;
        this.workerName = workerName;
        this.location = location;
        this.condition = condition;
        this.virus = virus;
        this.contaminant = contaminant;
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

    public void setWorkerName(String name) {
        this.workerName = name;
    }

    public Location getLocation() {
        return this.location;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getVirus() {
        return virus;
    }

    public int getContaminant() {
        return contaminant;
    }

}
