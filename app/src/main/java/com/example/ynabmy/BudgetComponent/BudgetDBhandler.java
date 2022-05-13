package com.example.ynabmy.BudgetComponent;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BudgetDBhandler extends SQLiteOpenHelper {
    private static final String TAG = "BudgetDBHandler";
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ynabmy.db";
    private static final String BUDGET_TABLE = "budget";
    private static final String BUDGET_CATEGORY_TABLE = "budget_category";
    private static final String BUDGET_ITEM_TABLE = "budget_item";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
