package com.example.lin4.activity;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.app.Activity;
import android.os.Bundle;

import com.example.lin4.util.ActivityCollector;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}