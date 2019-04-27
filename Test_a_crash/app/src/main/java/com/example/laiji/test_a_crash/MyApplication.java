package com.example.laiji.test_a_crash;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("lylog","ssss1");
        CrashCollectHandler.getInstance().init(this);
    }
}
