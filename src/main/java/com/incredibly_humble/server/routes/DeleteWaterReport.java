package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.server.LocalDatabase;
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
            WaterReport wr = gson.fromJson(request.body(), WaterReport.class);
            db.deleteWaterReport(wr);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
