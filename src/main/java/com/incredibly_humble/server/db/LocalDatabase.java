package com.incredibly_humble.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class LocalDatabase {
    //User Columns
    private static String NAME = "name";
    private static String EMAIL = "email";
    private static String PASS = "password";
    private static String ADDR = "address";
    private static String TYPE = "objType";
    private static String SUBD = "subscribed";
    private static String PHONE = "phone";
    private static String LOG_FAILED = "numFailedLogin";
    private static String DATE = "date";
    private static String LOC = "location";
    private static String LAT = "lattitude";
    private static String LON = "longitude";
    private static String ID = "ID";
    private static String DEFAULT_ERROR_MSG = "Error Occured";
    private static final String DRIVER = "org.h2.Driver";
    private Connection conn = null;

    public UserDatabase userDb;
    public WaterReportDatabase waterReportDb;
    public WaterQualityDatabase waterQualityReportDb;
    //keep in order of database rows
    public void establishConnection(String filePath) {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection("jdbc:h2:/" + filePath + ";IFEXISTS=TRUE");
            establishDbs();
        } catch (Exception e) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection("jdbc:h2:/" + filePath + ";");
                establishDbs();
                createTables();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

    }
    private void establishDbs(){
        this.userDb = new UserDatabase(conn);
        this.waterReportDb = new WaterReportDatabase(conn);
        this.waterQualityReportDb = new WaterQualityDatabase(conn);
    }
    public void closeConnectoin() throws Exception {
        conn.close();
        System.out.println("closed");
    }

    private void createTables() throws Exception {
        this.userDb.createTable();
        this.waterReportDb.createTable();
        this.waterQualityReportDb.createTable();
    }


}