package com.incredibly_humble.server.routes;

import com.google.inject.Inject;

import static spark.Spark.*;

public class RouteDefiner {
    @Inject
    AddUser addUser;
    @Inject
    Login login;
    @Inject
    UpdateUser updateUser;
    @Inject
    AddWaterReport addWaterReport;
    @Inject
    GetWaterReports getWaterReports;
    @Inject
    DeleteWaterReport deleteWaterReport;
    @Inject
    AddWaterQualityReport addWaterQualityReport;
    public void defineRoutes() {
        //USER
        //post
        post("/add", addUser);
        post("/login", login);
        post("/update", updateUser);

        //Water Source Reports
        //post
        post("/addWaterSourceReport", addWaterReport);
        post("/deleteWaterSourceReport", deleteWaterReport);
        //get
        get("/getWaterSourceReports", getWaterReports);

        //Water Quality Reports
        //post
        post("/addWaterQualityReport", addWaterQualityReport);

    }
}
