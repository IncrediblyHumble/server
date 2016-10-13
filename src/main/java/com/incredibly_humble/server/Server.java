package com.incredibly_humble.server;


import com.google.inject.Inject;
import com.incredibly_humble.server.routes.RouteDefiner;

import static spark.Spark.*;

public class Server {
    //TODO move to external file and get that way.
    private String path = "/home/noam/Desktop/incredibly_humble/server/db/";

    @Inject LocalDatabase db;
    @Inject RouteDefiner routeDefiner;

    //runs at http://localhost:4567/
    public void run() {
        db.establishConnection(Main.path + "/db");
        routeDefiner.defineRoutes();
    }

}