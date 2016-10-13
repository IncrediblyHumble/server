package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.models.WaterReport;
import com.incredibly_humble.server.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.ObjectInputStream;

public class AddWaterReport implements Route {
    @Inject
    LocalDatabase db;
    @Inject
    Gson gson;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            WaterReport wr = gson.fromJson(request.body(), WaterReport.class);
            return gson.toJson(db.addWaterReoprt(wr));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
