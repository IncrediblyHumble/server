package com.incredibly_humble.server.routes;

import com.google.inject.Inject;
import static spark.Spark.*;

public class RouteDefiner {
    @Inject AddUser addUser;
    @Inject Login login;
    @Inject UpdateUser updateUser;
    public void defineRoutes(){
        //post
        post("/addUser",addUser);
        post("/login", login);
        post("/updateUser",updateUser);
    }
}
