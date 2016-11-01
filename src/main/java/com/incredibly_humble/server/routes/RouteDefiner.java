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
    @Inject
    GetWaterQualityReports getWaterQualityReports;
    public void defineRoutes() {
        //USER
        //post
        post("/addUser", addUser);
        post("/login", login);
        post("/updateUser", updateUser);

        //Water Source Reports
        //post
        post("/addWaterSourceReport", addWaterReport);
        post("/deleteWaterSourceReport", deleteWaterReport);
        //getAll
        get("/getWaterSourceReports", getWaterReports);

        //Water Quality Reports
        //post
        post("/addWaterQualityReport", addWaterQualityReport);
        //getAll
        get("/getWaterQualityReports", getWaterQualityReports);

    }
}
