package com.hosseinkurd.kdm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDao<T> {
    private DBOpenHelper mHelper;

    AbstractDao(Context context) {
        mHelper = new DBOpenHelper(context);
    }

    SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    SQLiteDatabase getReadableDatabase() {
        return mHelper.getReadableDatabase();
    }

    public void close() {
        mHelper.close();
    }
}
