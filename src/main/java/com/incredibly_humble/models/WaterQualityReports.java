package com.incredibly_humble.models;

import java.io.Serializable;
import java.util.ArrayList;

public class WaterQualityReports implements Serializable {
    private ArrayList<WaterQualityReport> reports;

    public WaterQualityReports(ArrayList<WaterQualityReport> reports){
        this.reports = reports;
    }
    public ArrayList<WaterQualityReport> getReports(){
        return reports;
    }
}
