package com.example.lin4.model;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.lin4.db.LIN4OpenHelper;
import com.example.lin4.util.LogUtil;
import com.example.lin4.util.MyApplication;

public class LIN4DB {

    public static final String DB_NAME = "login_method";

    public static final int VERSION = 1;

    private static LIN4DB fpDB;

    private SQLiteDatabase db;



    private LIN4DB(Context context) {
        LIN4OpenHelper dbHelper = new LIN4OpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }


    public synchronized static LIN4DB getInstance(Context context) {
        if (fpDB == null) {
            fpDB = new LIN4DB(context);
        }
        return fpDB;
    }


    public void saveAccount(Account account) {
        if (account != null) {
            ContentValues values = new ContentValues();
            values.put("name", account.getName());
            values.put("password", account.getPassword());
            db.insert("Account", null, values);
        }
    }


    public boolean queryPassword(String a, Password password) {
        int i;
        int j;
        int length;
        int count;
        Cursor cursor = db.
                rawQuery("select * from Account where name like ?", new String[]{a});
        if (cursor == null) {
            LogUtil.d("LIN4DB", "cursor == null");
            return false;
        }
        if (cursor.getCount() == 0 || cursor.getCount() > 1 ) {
            Toast.makeText(MyApplication.getContext(), "problem",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (cursor.moveToFirst()) {
                String passwordGet;
                passwordGet = cursor.getString(cursor.getColumnIndex("password"));
                length = passwordGet.length();
                char[] pwdGet = new char[length];
                char[] pwd = new char[length];
                for (i = 0; i < 5; i++) {
                    count = 0;
                    pwdGet = passwordGet.toCharArray();
                    pwd = password.getPwd(i).toCharArray();
                    for (j = 0; j < length; j++) {
                        if (pwdGet[j] == pwd[j]) {
                            count++;
                        }
                    }
                    if (count > Math.ceil(((double)length)/2)) {
                        return false;
                    }
                }
                return true; //attacker
            } else {
                return false;
            }
        }
    }



    public boolean validate(Account account) {
        Cursor cursor = db.
                rawQuery("select * from Account where name like ?", new String[]{account.getName()});

        if (cursor == null) {
            LogUtil.d("LIN4DB", "cursor == null");
            return false;
        }
        if (cursor.getCount() == 0) {
            return true;
        } else {
            if (MyApplication.getContext() != null) {
                Toast.makeText(MyApplication.getContext(), "username registered",
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        }

    }

    public boolean validate(String a, Password password) {
        int i;
        Cursor cursor = db.
                rawQuery("select * from Account where name like ?", new String[]{a});
        Cursor cursor1;
        if (cursor == null) {
            LogUtil.d("LIN4DB", "cursor == null");
            return false;
        }
        if (cursor.getCount() == 0 || cursor.getCount() > 1 ) {
            //Toast.makeText(MyApplication.getContext(), "problem",
            //Toast.LENGTH_SHORT).show();
            if (MyApplication.getContext() != null) {
                Toast.makeText(MyApplication.getContext(), "username or password is wrong",
                        Toast.LENGTH_SHORT).show();
            }
            return false;
        } else {
            if (cursor.moveToFirst()) {
                int flag;
                String passwordGet;
                passwordGet = cursor.getString(cursor.getColumnIndex("password"));
                for (i = 0; i < 10; i++) {
                    if (passwordGet.equals(password.getPwd(i))) {
                        return true;
                    }
                }
            }
        }
        if (MyApplication.getContext() != null) {
            Toast.makeText(MyApplication.getContext(), "username or password is wrong",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }




    public void defence(String a) {
        ContentValues values = new ContentValues();
        values.put("flag", -1);
        db.update("Account", values, "name = ?", new String[] {a});
    }

    public boolean check(String a) {

        Cursor cursor = db.
                rawQuery("select * from Account where name like ?", new String[]{a});

        if (cursor == null) {
            LogUtil.d("LIN4DB", "cursor == null");
            return false;
        }
            if (cursor.moveToFirst()) {
                int flag;
                flag = cursor.getInt(cursor.getColumnIndex("flag"));
                if (flag < 0) {
                    return true;
                }

            }
        return false;
    }

    public void stopAlarm(String a) {
        ContentValues values = new ContentValues();
        values.put("flag", 0);
        db.update("Account", values, "name = ?", new String[] {a});
    }
}


