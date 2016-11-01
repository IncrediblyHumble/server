package com.incredibly_humble.server.db;

import com.incredibly_humble.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class WaterQualityDatabase {
    private static String NAME = "name";
    private static String COND = "condition";
    private static String DATE = "date";
    private static String LOC = "location";
    private static String LAT = "lattitude";
    private static String LON = "longitude";
    private static String VIRUS = "virus";
    private static String CONTAMINANT = "contaminant";
    private static String ID = "ID";
    private Connection conn;

    public WaterQualityDatabase(Connection conn) {
        this.conn = conn;
    }

    public void createTable() throws SQLException {
        String executeString = "CREATE TABLE WaterQualityReports("
                + DATE + " BIGINT,"
                + LOC + " TEXT,"
                + NAME + " TEXT,"
                + COND + " TEXT,"
                + LAT + " TEXT,"
                + LON + " TEXT,"
                + VIRUS + " int,"
                + CONTAMINANT + " int,"
                + ID + " int NOT NULL AUTO_INCREMENT);";
        conn.createStatement().execute(executeString);
    }

    public WaterQualityReport add(WaterQualityReport report) {
        try {
            String executeString = String.format("INSERT INTO WaterQualityReports (%s, %s, %s, %s, %s, %s, %s) " +
                            "VALUES('%s', '%s', '%s',%s, %s, '%f', '%f')",
                    DATE, COND, NAME, VIRUS, CONTAMINANT, LAT, LON,
                    report.getDateReported().getTime(),
                    report.getCondition().toString(), report.getWorkerName(),
                    report.getVirus(), report.getContaminant(),
                    report.getLocation().getLatitude(), report.getLocation().getLongitude());
            conn.createStatement().execute(executeString);
            executeString = String.format("SELECT * FROM  WaterQualityReports WHERE %s='%d'", DATE, report.getDateReported().getTime());
            ResultSet set = conn.createStatement().executeQuery(executeString);
            set.next();
            return getWaterQualityReport(set);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WaterQualityReports getAll() {
        ArrayList<WaterQualityReport> reports = new ArrayList<>();
        try {
            String executeString = "SELECT * FROM  WaterQualityReports";
            ResultSet set = conn.createStatement().executeQuery(executeString);
            while (set.next()) {
                reports.add(getWaterQualityReport(set));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WaterQualityReports(reports);
    }

    private WaterQualityReport getWaterQualityReport(ResultSet set) throws Exception {
        return new WaterQualityReport(
                Integer.valueOf(set.getNString(ID)),
                new Date(Long.valueOf(set.getNString(DATE))),
                new Location(Double.valueOf(set.getNString(LAT)), Double.valueOf(set.getNString(LON))),
                set.getNString(NAME),
                WaterQualityReport.Condition.valueOf(set.getNString(COND)),
                Integer.valueOf(set.getNString(VIRUS)),
                Integer.valueOf(set.getNString(CONTAMINANT))
        );
    }

}
