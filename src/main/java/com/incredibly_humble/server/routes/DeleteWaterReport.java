package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.WaterSourceReport;
import com.incredibly_humble.server.db.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteWaterReport implements Route {
    @Inject
    LocalDatabase db;
    @Inject
    Gson gson;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            WaterSourceReport wr = gson.fromJson(request.body(), WaterSourceReport.class);
            db.waterReportDb.delete(wr);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
