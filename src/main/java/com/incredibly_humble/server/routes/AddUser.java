package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.server.db.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddUser implements Route {
    @Inject
    LocalDatabase db;
    @Inject Gson gson;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            User u = gson.fromJson(request.body(), User.class);
            System.out.println(u.getEmail());
            String a =  gson.toJson(db.userDb.add(u));
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
