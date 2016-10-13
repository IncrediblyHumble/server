package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.server.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.ObjectInputStream;

public class UpdateUser implements Route {
    @Inject
    LocalDatabase db;
    @Inject Gson gson;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            User u = gson.fromJson(request.body(), User.class);
            return gson.toJson(db.updateUser(u));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
