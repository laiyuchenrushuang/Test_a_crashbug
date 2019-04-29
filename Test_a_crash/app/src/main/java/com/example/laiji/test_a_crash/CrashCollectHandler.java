package com.example.laiji.test_a_crash;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

public class CrashCollectHandler implements UncaughtExceptionHandler {
    Context mContext;
    Thread.UncaughtExceptionHandler mDefaultHandler;
    static CrashCollectHandler mCrashCollectHandler;
    public static CrashCollectHandler getInstance() {
        if (mCrashCollectHandler == null) {
            mCrashCollectHandler = new CrashCollectHandler();
        }
        return mCrashCollectHandler;
    }

    public void init(Context context){
        this.mContext = context;
        mDefaultHandler =  Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        List result = MainActivity.list_data;
        SharedPreferences userSettings = mContext.getSharedPreferences("result_data", mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = userSettings.edit();

        editor.putString("data", HttpResultUtil.toJsonString(result));
        Log.d("lylog",HttpResultUtil.toJsonString(result));

        editor.commit();
    }
}
