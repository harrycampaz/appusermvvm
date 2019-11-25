package com.dezzapps.appusers.home;

import com.bluelinelabs.conductor.Controller;
import com.dezzapps.appusers.di.ControllerKey;
import com.dezzapps.appusers.trending.TrendingRepoComponent;
import com.dezzapps.appusers.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {TrendingRepoComponent.class})
public abstract class MainScreenBindingModule {



    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingRepoInjetor(TrendingRepoComponent.Builder builder);


}
