package com.incredibly_humble.server.routes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.server.db.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

public class Login implements Route {
    @Inject
    LocalDatabase db;
    @Inject
    Gson gson;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            User u = gson.fromJson(request.body(), User.class);
            return gson.toJson(db.userDb.login(u.getEmail(), u.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return new User("UNKNOWN ERROR", null, null, null);
        }
    }
}
