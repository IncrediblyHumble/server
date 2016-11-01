package com.incredibly_humble.models;

import java.io.Serializable;
import java.util.ArrayList;

public class WaterSourceReports implements Serializable {
    private ArrayList<WaterSourceReport> reports;
    public WaterSourceReports(ArrayList<WaterSourceReport> reports){
        this.reports = reports;
    }
    public ArrayList<WaterSourceReport> getReports(){
        return reports;
    }
}
