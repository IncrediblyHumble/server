package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.WaterQualityReport;
import com.incredibly_humble.models.WaterSourceReport;
import com.incredibly_humble.server.db.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddWaterQualityReport implements Route {
    @Inject
    LocalDatabase db;
    @Inject
    Gson gson;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            WaterQualityReport wr = gson.fromJson(request.body(), WaterQualityReport.class);
            return gson.toJson(db.waterQualityReportDb.add(wr));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
