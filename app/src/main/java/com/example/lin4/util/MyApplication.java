package com.example.lin4.util;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}