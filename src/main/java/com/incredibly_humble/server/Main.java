package com.incredibly_humble.server;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.incredibly_humble.models.User;

import java.io.ObjectInputStream;
import java.util.Arrays;

import static spark.Spark.post;

public class Main {
    private GuiceContext context = new GuiceContext(this, () -> Arrays.asList(new Module()));

    //TODO move to external file and get that way.
    public static String path;//= "/home/noam/Desktop/incredibly_humble/server/db/";
    public static void main(String[] args) {
        path = args[0];
        System.out.println(path);
        Injector injector = Guice.createInjector(new Module());
        Server server = injector.getInstance(Server.class);
        server.run();
    }
}
