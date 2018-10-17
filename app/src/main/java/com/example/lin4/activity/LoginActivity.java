package com.example.lin4.activity;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.lin4.R;
import com.example.lin4.model.LIN4DB;
import com.example.lin4.model.Password;



public class LoginActivity extends BaseActivity {

    private EditText accountLogin;
    private Button inputPwd;
    private String name;
    private LIN4DB linDB;
    private int hour;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accountLogin = (EditText) findViewById(R.id.account_login);
        inputPwd = (Button) findViewById(R.id.password_input);
        linDB = linDB.getInstance(this);

        inputPwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = accountLogin.getText().toString();
                //��ת��������󷵻������ٽ�����֤
                Intent intent = new Intent(LoginActivity.this, LINActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Password pwd = (Password) data.getSerializableExtra("password");
                    hour = data.getIntExtra("hour", 0);
                    minute = data.getIntExtra("minute", 0);
                    second = data.getIntExtra("second", 0);
                    if (linDB.validate(name, pwd)) {
                        Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putInt("hour", hour);
                        bundle.putInt("minute", minute);
                        bundle.putInt("second", second);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }
        }
    }
}



