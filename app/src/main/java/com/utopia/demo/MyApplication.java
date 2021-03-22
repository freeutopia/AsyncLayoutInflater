package com.utopia.demo;

import android.app.Application;

import com.utopia.inflater.config.LayoutInflaterConfig;
import com.utopia.inflater.config.OrientationMode;
import com.utopia.inflater.config.Unit;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new LayoutInflaterConfig.Builder()
                .designSize(600,800, Unit.DP)
                .orientation(OrientationMode.HORIZONTAL)
                .context(this)
                .build();
    }
}
