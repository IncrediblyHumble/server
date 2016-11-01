package com.incredibly_humble.server.db;


import com.incredibly_humble.models.Location;
import com.incredibly_humble.models.WaterSourceReport;
import com.incredibly_humble.models.WaterSourceReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class WaterReportDatabase {
    //User Columns
    private static String NAME = "name";
    private static String TYPE = "objType";
    private static String DATE = "date";
    private static String LOC = "location";
    private static String LAT = "lattitude";
    private static String LON = "longitude";
    private static String ID = "ID";

    private Connection conn;
    public WaterReportDatabase(Connection conn){
        this.conn = conn;
    }

    public void createTable() throws SQLException{
        String executeString = "CREATE TABLE WaterReports("
                + DATE + " BIGINT,"
                + LOC + " TEXT,"
                + NAME + " TEXT,"
                + TYPE + " TEXT,"
                + LAT + " TEXT,"
                + LON + " TEXT,"
                + ID + " int NOT NULL AUTO_INCREMENT);";
        conn.createStatement().execute(executeString);
    }
    public WaterSourceReport add(WaterSourceReport report) {
        try {
            String executeString = String.format("INSERT INTO WaterSourceReports (%s, %s, %s, %s, %s) " +
                            "VALUES('%s', '%s', '%s', '%f', '%f')",
                    DATE, TYPE, NAME, LAT, LON,
                    report.getDateReported().getTime(),
                    report.getType().toString(),  report.getWorkerName(),
                    report.getLocation().getLatitude(), report.getLocation().getLongitude());
            conn.createStatement().execute(executeString);
            executeString = String.format("SELECT * FROM  WaterSourceReports WHERE %s='%d'",DATE, report.getDateReported().getTime());
            ResultSet set = conn.createStatement().executeQuery(executeString);
            set.next();
            return getWaterReport(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WaterSourceReports get() {
        ArrayList<WaterSourceReport> reports = new ArrayList<>();
        try {
            String executeString = "SELECT * FROM  WaterReports";
            ResultSet set = conn.createStatement().executeQuery(executeString);
            while (set.next()) {
                reports.add(getWaterReport(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WaterSourceReports(reports);
    }

    private WaterSourceReport getWaterReport(ResultSet set) throws Exception {
        return new WaterSourceReport(
                Integer.valueOf(set.getNString(ID)),
                new Date(Long.valueOf(set.getNString(DATE))),
                new Location(Double.valueOf(set.getNString(LAT)), Double.valueOf(set.getNString(LON))),
                set.getNString(NAME),
                WaterSourceReport.WaterType.valueOf(set.getNString(TYPE))
        );
    }

    public void delete(WaterSourceReport r) throws Exception{
        String executeString = String.format("DELETE FROM WaterSourceReports WHERE %s='%d'",ID,r.getId());
        conn.createStatement().execute(executeString);
    }
}
