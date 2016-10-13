package com.incredibly_humble.server.routes;

import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.server.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.ObjectInputStream;

public class GetWaterReports implements Route {
    @Inject
    LocalDatabase db;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            return db.getWaterReports();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
