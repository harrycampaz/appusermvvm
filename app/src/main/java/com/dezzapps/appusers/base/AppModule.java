package com.dezzapps.appusers.base;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {

        this.app = app;
    }

    @Provides
    Context provideAppContext(){
        return app;
    }
}
