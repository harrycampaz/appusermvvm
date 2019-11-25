package com.dezzapps.appusers.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = { AppModule.class, ActivityBindingModel.class})
public interface AppComponent {

    void inject(App app);


}
