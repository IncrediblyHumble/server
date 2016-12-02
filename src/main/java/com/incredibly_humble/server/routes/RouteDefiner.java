package com.incredibly_humble.server.routes;

import com.google.inject.Inject;
import com.incredibly_humble.server.Logger;

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
    @Inject
    GetLogs getLogs;
    public void defineRoutes() {
        enableCORS("*", "*", "*");
        Logger.INIT();
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
        get("/logs",getLogs);
        after((request, response) -> {
            Logger.LOG(request.pathInfo(),request.body(),response.body());
        });

    }
    // Enables CORS on requests. This method is an initialization method and should be called once.
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
