package com.taohuh.breathingtraining;

import android.app.Application;

import com.taohuh.breathingtraining.manager.Contextor;

/**
 * Created by Administrator on 10/6/2560.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
