package com.example.strider_sol.multitoolapp.common.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.strider_sol.multitoolapp.common.Constant;

/**
 * Created by HP LAPTOP on 05-04-2016.
 */
public class SQLiteDemo extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "
            + Constant.NOTE_TABLE
            + "("
            + Constant.COLUMN_1D + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constant.COLUMN_TITLE + " TEXT NOT NULL, "
            + Constant.COLUMN_CONTENT + " TEXT NOT NULL, "
            + Constant.COLUMN_COLOR + " INTEGER NOT NULL, "
            + Constant.COLUMN_MODIFIED_TIME + " INTERGER NOT NULL, "
            + Constant.COLUMN_CREATED_TIME + " INTEGER NOT NULL "
            + ")";

    public SQLiteDemo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
