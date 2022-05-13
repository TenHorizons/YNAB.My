package com.example.ynabmy.UserComponent;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHandler extends SQLiteOpenHelper {
    private static final String TAG = "MyDBHandler";
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ynabmy.db";
    private static final String USER_TABLE = "ynabmy.db";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
