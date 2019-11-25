package com.dezzapps.appusers.di;

import android.app.Activity;
import android.content.Context;

import com.dezzapps.appusers.base.App;
import com.dezzapps.appusers.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

public class ActivityInjector {


    private final Map<Class < ? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors;

    private final Map<String, AndroidInjector<? extends  Activity>> cache = new HashMap<>();
    @Inject
    public ActivityInjector(Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors) {
        this.activityInjectors = activityInjectors;
    }


    void inject(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must extents BaseActivity");
        }

        String instanceId = ((BaseActivity) activity).getInstanceId();

        if(cache.containsKey(instanceId)){
            //noinspection unchecked
            ((AndroidInjector<Activity>) cache.get(instanceId)).inject(activity);
            return;
        }

        AndroidInjector.Factory<Activity> injectFactory =
                (AndroidInjector.Factory<Activity>) activityInjectors.get(activity.getClass()).get();

        AndroidInjector<Activity> injector = injectFactory.create(activity);

        cache.put(instanceId, injector);
        injector.inject(activity);

    }

    void clear(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must extents BaseActivity");
        }

        cache.remove(((BaseActivity) activity).getInstanceId());
    }

    static  ActivityInjector get(Context context){
       return  ((App) context.getApplicationContext()).getActivityInjector();
    }


}
