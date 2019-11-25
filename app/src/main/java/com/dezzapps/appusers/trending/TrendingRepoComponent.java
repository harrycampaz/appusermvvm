package com.dezzapps.appusers.trending;

import com.dezzapps.appusers.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface TrendingRepoComponent extends AndroidInjector<TrendingReposController> {

    @Subcomponent.Builder
    abstract class  Builder  extends  AndroidInjector.Builder<TrendingReposController>{

    }
}
