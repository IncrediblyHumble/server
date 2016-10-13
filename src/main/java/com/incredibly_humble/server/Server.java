package com.incredibly_humble.server;


import com.google.inject.Inject;
import com.incredibly_humble.server.routes.RouteDefiner;

import java.io.File;

import static spark.Spark.*;

public class Server {
    @Inject LocalDatabase db;
    @Inject RouteDefiner routeDefiner;

    //runs at http://localhost:4567/
    public void run() {
        String path = new File("").getAbsolutePath()+"/db/db"; //db in folder db
        db.establishConnection(path);
        routeDefiner.defineRoutes();
    }

}