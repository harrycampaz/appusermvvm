package com.dezzapps.appusers.base;

import android.app.Activity;

import com.dezzapps.appusers.home.MainActivity;
import com.dezzapps.appusers.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MainActivityComponent.class})
public abstract class ActivityBindingModel {


    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    provideMainActivityInjector(MainActivityComponent.Builder builder);

}
