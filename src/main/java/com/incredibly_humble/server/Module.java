package com.incredibly_humble.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.incredibly_humble.server.db.LocalDatabase;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(LocalDatabase.class).toInstance(new LocalDatabase());
        bind(Gson.class).toInstance(new GsonBuilder().create());
    }
}
