package com.incredibly_humble.models;

import java.io.Serializable;
import java.util.ArrayList;

public class WaterReports implements Serializable {
    private ArrayList<WaterReport> reports;
    public WaterReports(ArrayList<WaterReport> reports){
        this.reports = reports;
    }
    public ArrayList<WaterReport> getReports(){
        return reports;
    }
}
