package com.example.lin4.db;

/**
 * Created by Administrator on 2018/2/15.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LIN4OpenHelper extends SQLiteOpenHelper {


    /**
     *  Account�������
     */
    public static final String CREATE_ACCOUNT = "create table Account ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "password text)";





    public LIN4OpenHelper(Context context, String name, CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_ACCOUNT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}


