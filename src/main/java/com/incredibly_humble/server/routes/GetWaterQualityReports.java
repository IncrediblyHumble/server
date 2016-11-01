package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.server.db.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetWaterQualityReports implements Route {
    @Inject
    LocalDatabase db;
    @Inject
    Gson gson;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            return gson.toJson(db.waterQualityReportDb.getAll());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
