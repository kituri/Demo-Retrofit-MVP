package com.mvp.mobile.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by kirijin on 2016/4/20.
 */
public class MobileApplication extends Application {

    protected static final Object object = new Object();

    static private MobileApplication app = null;

    public static MobileApplication getInstance() {
        synchronized (object) {
            return app;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        app = this;
    }
}
