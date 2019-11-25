package com.dezzapps.appusers.base;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.dezzapps.appusers.di.Injector;

public abstract class BaseController extends Controller {


    private  boolean injected = false;
    @Override
    protected void onContextAvailable(@NonNull Context context) {

        /*
        * Controller instances are retained across config changes, so this method can be called than once.
        * this makes sure we don't waste any time injecting more than once, though technically it wouldn't change  functionality
        * */

        if (!injected) {
            Injector.inject(this);
            injected = true;
        }

        super.onContextAvailable(context);
    }



}
