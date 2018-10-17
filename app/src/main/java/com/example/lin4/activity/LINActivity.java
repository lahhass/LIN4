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
import com.example.lin4.model.Password;
import com.example.lin4.model.Set;

import java.util.Calendar;

public class LINActivity extends BaseActivity implements OnClickListener {


    private Set set;
    private int[] pattern = new int[10];
    private TextView pt_show;
    private TextView pwdShow;
    private Button delete;
    private Button left;
    private Button right;
    private Button ok;

    private Password password;
    private int pwdlen = 0;
    private Calendar calendar1;
    private long unixTime1;//这是时间戳
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lin4);

        set = new Set();
        password = new Password();
        pwdShow = (TextView) findViewById(R.id.password_show);
        delete = (Button) findViewById(R.id.delete);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        ok= (Button) findViewById(R.id.ok);

        pattern = set.setDisplay();
        setDisplay(pattern);

        delete.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete:
                password.delete();
                pwdDel();
                break;
            case R.id.left:
                set.setLeft();
                pattern = set.setDisplay();
                setDisplay(pattern);
                break;
            case R.id.right:
                set.setRight();
                pattern = set.setDisplay();
                setDisplay(pattern);
                break;
            case R.id.ok:
                if (pwdlen < 4) {
                    pwdShow();
                    if(pwdlen == 1 && flag == 0) {
                        calendar1= Calendar.getInstance();
                        unixTime1 = calendar1.getTimeInMillis();
                        flag = 1;
                    }
                    password.ok(pattern);
                    if (pwdlen == 4) {
                        calculate();
                        Intent intent = new Intent();
                        intent.putExtra("password", password);
                        intent.putExtra("hour", hour);
                        intent.putExtra("minute", minute);
                        intent.putExtra("second", second);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (pwdlen < 4) {
                        set = new Set();
                        pattern = set.setDisplay();
                        setDisplay(pattern);
                    }
                }
                break;
            default:
                break;
        }
    }



    public void setDisplay(int[] a) {
        int i;
        for (i = 0; i < 10; i++) {
            switch (i) {
                case 0:
                    pt_show = (TextView)findViewById(R.id.pattern_1);
                    break;
                case 1:
                    pt_show = (TextView)findViewById(R.id.pattern_2);
                    break;
                case 2:
                    pt_show = (TextView)findViewById(R.id.pattern_3);
                    break;
                case 3:
                    pt_show = (TextView)findViewById(R.id.pattern_4);
                    break;
                case 4:
                    pt_show = (TextView)findViewById(R.id.pattern_5);
                    break;
                case 5:
                    pt_show = (TextView)findViewById(R.id.pattern_6);
                    break;
                case 6:
                    pt_show = (TextView)findViewById(R.id.pattern_7);
                    break;
                case 7:
                    pt_show = (TextView)findViewById(R.id.pattern_8);
                    break;
                case 8:
                    pt_show = (TextView)findViewById(R.id.pattern_9);
                    break;
                case 9:
                    pt_show = (TextView)findViewById(R.id.pattern_10);
                    break;
                default:
                    pt_show = (TextView)findViewById(R.id.pattern_1);
                    break;
            }

            switch (a[i]) {
                case 0:
                    pt_show.setText("○");
                    break;
                case 1:
                    pt_show.setText("◇");
                    break;
                case 2:
                    pt_show.setText("♣");
                    break;
                case 3:
                    pt_show.setText("★");
                    break;
                case 4:
                    pt_show.setText("▲");
                    break;
                case 5:
                    pt_show.setText("☏");
                    break;
                case 6:
                    pt_show.setText("❤");
                    break;
                case 7:
                    pt_show.setText("☞");
                    break;
                case 8:
                    pt_show.setText("♠");
                    break;
                case 9:
                    pt_show.setText("■");
                    break;
                default:
                    pt_show.setText(" ");
                    break;
            }
        }
    }

    public void pwdShow() {
            String data = pwdShow.getText().toString();
            data = data + "●";
            pwdlen = pwdlen + 1;
            if (pwdlen > 0) {
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.VISIBLE);
            }
            pwdShow.setText(data);

    }

    public void pwdDel() {
        String data = pwdShow.getText().toString();
        if (!data.equals("")) {
            data = data.substring(0, data.length()-1);
            pwdlen = pwdlen - 1;
            if (pwdlen == 0) {
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.INVISIBLE);
            }
            pwdShow.setText(data);
        }
    }

    public void calculate() {
        Calendar calendar2 = Calendar.getInstance();
        long unixTime2 = calendar2.getTimeInMillis();
        hour = calendar2.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY);
        minute = calendar2.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE);
        second = calendar2.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND);
        if (minute < 0) {
            hour = hour - 1;
            minute = minute + 60;
        }
        if (second < 0) {
            minute = minute - 1;
            second = second + 60;
        }
    }
}
