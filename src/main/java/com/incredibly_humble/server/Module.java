package com.incredibly_humble.server;

import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        bind(LocalDatabase.class).toInstance(new LocalDatabase());
    }
}
