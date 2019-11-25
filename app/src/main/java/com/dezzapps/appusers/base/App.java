package com.dezzapps.appusers.base;

import android.app.Application;

import com.dezzapps.appusers.di.ActivityInjector;

import javax.inject.Inject;

public class App extends Application {

    @Inject
    ActivityInjector activityInjector;

    private  AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);
    }

    public ActivityInjector getActivityInjector() {

        return activityInjector;

    }
}
