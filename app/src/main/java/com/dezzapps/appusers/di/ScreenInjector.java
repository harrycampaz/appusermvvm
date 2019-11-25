package com.dezzapps.appusers.di;

import android.app.Activity;
import android.content.Context;

import com.bluelinelabs.conductor.Controller;
import com.dezzapps.appusers.base.App;
import com.dezzapps.appusers.base.BaseActivity;
import com.dezzapps.appusers.base.BaseController;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

@ActivityScope
public class ScreenInjector {


    private final Map<Class < ? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjectors;

    private final Map<String, AndroidInjector<? extends  Controller>> cache = new HashMap<>();
    @Inject
    public ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> activityInjectors) {
        this.screenInjectors = activityInjectors;
    }


    void inject(Controller controller){
        if(!(controller instanceof BaseController)){
            throw new IllegalArgumentException("Controller must extents BaseController");
        }

        String instanceId = controller.getInstanceId();

        if(cache.containsKey(instanceId)){

            ((AndroidInjector<Controller>) cache.get(instanceId)).inject(controller);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectFactory =
                (AndroidInjector.Factory<Controller>) screenInjectors.get(controller.getClass()).get();

        AndroidInjector<Controller> injector = injectFactory.create(controller);

        cache.put(instanceId, injector);
        injector.inject(controller);

    }

    void clear(Controller controller){
        if(!(controller instanceof BaseController)){
            throw new IllegalArgumentException("Controller must extents BaseController");
        }

        cache.remove(((BaseController) controller).getInstanceId());
    }

    static ScreenInjector get(Activity activity){

        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Controller must extents BaseController");
        }


       return ((BaseActivity) activity).getScreenInjector();
    }


}
