package com.dmdddm.sws;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建城市表
        String CreateTable = "CREATE TABLE cityname(city VARCHAR(30),code VARCHAR(10))";
        //创建用户表
        String CreateUserTable = "CREATE TABLE user(name VARCHAR(30),pwd VARCHAR(32))";

        db.execSQL(CreateTable);

        db.execSQL(CreateUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
