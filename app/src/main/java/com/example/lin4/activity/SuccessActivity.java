package com.example.lin4.activity;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.lin4.R;
import com.example.lin4.util.ActivityCollector;
import com.example.lin4.util.MyApplication;

public class SuccessActivity extends BaseActivity {

    private int hour;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        Button offline = (Button) findViewById(R.id.offline);
        TextView time = (TextView) findViewById(R.id.time);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        hour = bundle.getInt("hour");
        minute = bundle.getInt("minute");
        second = bundle.getInt("second");
        time.setText(hour + ":" + minute + ":" + second);
        offline.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCollector.finishAll();
                Intent intent = new Intent(SuccessActivity.this,
                        OpenAppActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

}


