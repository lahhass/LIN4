package com.example.lin4.activity;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lin4.R;
import com.example.lin4.model.Account;
import com.example.lin4.model.LIN4DB;
import com.example.lin4.util.MyApplication;

public class RegisterActivity extends BaseActivity {

    private EditText accountName;
    private EditText passwordFirst;
    private EditText passwordAgain;
    private Button register;
    private LIN4DB linDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        accountName = (EditText) findViewById(R.id.account_register);
        passwordFirst = (EditText) findViewById(R.id.password_first);
        passwordAgain = (EditText) findViewById(R.id.password_again);
        register = (Button) findViewById(R.id.register);
        linDB = linDB.getInstance(this);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountName.getText().toString();
                String passwordFst = passwordFirst.getText().toString();
                String passwordAgn = passwordAgain.getText().toString();
                //如果两次密码不同需重新输入
                if (!passwordFst.equals(passwordAgn)) {
                    Toast.makeText(RegisterActivity.this, "Two passwords are different, please retype.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Account newAccount = new Account();
                    newAccount.setName(account);
                    newAccount.setPassword(passwordFst);
                    //用户名有效则注册成功
                    if (linDB.validate(newAccount)) {
                        linDB.saveAccount(newAccount);
                        if (MyApplication.getContext() != null) {
                            Toast.makeText(MyApplication.getContext(), "Register success", Toast.LENGTH_SHORT).show();
                        }
                        finish();


                    }
                }
            }
        });
    }
}



