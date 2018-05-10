package com.unisrobot.firstmodule.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WEI on 2018/5/10.
 */

public class Dbhelp extends SQLiteOpenHelper {
    private static final String TAG = "Dbhelp";
    public static final int Version = 1;
    private String tableName ;
    public Dbhelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.tableName = name ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: ");
        String sql = "create table "+tableName+"(id int , name varchar(20),sage int,ssex varchar(10))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
