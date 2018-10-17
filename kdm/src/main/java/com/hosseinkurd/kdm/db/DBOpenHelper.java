package com.hosseinkurd.kdm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "KdmDb.db";
    private static final int DB_VERSION = 1;

    DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private void createTable(SQLiteDatabase db){
        KDMInfoDao.createTable(db);
    }

    private void dropTable(SQLiteDatabase db){
        KDMInfoDao.dropTable(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(db);
        createTable(db);
    }
}
