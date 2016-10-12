package com.incredibly_humble.server.routes;

import com.google.inject.Inject;
import com.incredibly_humble.models.User;
import com.incredibly_humble.server.LocalDatabase;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.ObjectInputStream;

public class Login implements Route {
    @Inject
    LocalDatabase db;
    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            String email = request.params().get("email");
            String pass = request.params().get("password");
            return db.login(email,pass);
        } catch (Exception e) {
            e.printStackTrace();
            return new User("UNKNOWN ERROR", null, null, null);
        }
    }
}
