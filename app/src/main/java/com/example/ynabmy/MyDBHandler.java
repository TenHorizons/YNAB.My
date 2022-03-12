package com.example.ynabmy;

import com.example.ynabmy.Account;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ynabmy.db";
    private static final String ACCOUNT_TABLE = "accounts";

    private static final String CREATE_TABLE_ACCOUNTS = "CREATE TABLE " + ACCOUNT_TABLE + " (id INTEGER primary key autoincrement NOT NULL, budget_type TEXT, nickname TEXT, balance FLOAT, interest_rate FLOAT, monthly_payment FLOAT)";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);

        // create new tables
        onCreate(db);
    }

    public long createAccount(String budget_type, String nickname, Float balance, Float interest_rate, Float monthly_payment ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("budget_type", budget_type);
        values.put("nickname", nickname);
        values.put("balance", balance);
        values.put("interest_rate", interest_rate);
        values.put("monthly_payment", monthly_payment);

        // insert row
        long account_id = db.insert(ACCOUNT_TABLE, null, values);

        return account_id;
    }

    public Account getAccount(long account_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + ACCOUNT_TABLE + " WHERE " + "id = " + account_id;
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Account acc = new Account();
        acc.setAccount( c.getInt(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("budget_type")), c.getString(c.getColumnIndexOrThrow("nickname")), c.getFloat(c.getColumnIndexOrThrow("balance")) ,
                c.getFloat(c.getColumnIndexOrThrow("interest_rate"))  ,c.getFloat(c.getColumnIndexOrThrow("monthly_payment"))  );


        return acc;
    }

//    public List<Account> getAllAccounts() {
//        List<Account> accounts = new ArrayList<Account>();
//        String selectQuery = "SELECT  * FROM " + ACCOUNT_TABLE;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Account account = new Account();
//                account.setAccount( c.getInt(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("budget_type")) , c.getString(c.getColumnIndexOrThrow("nickname")), c.getFloat(c.getColumnIndexOrThrow("balance")) ,
//                        c.getFloat(c.getColumnIndexOrThrow("interest_rate"))  ,c.getFloat(c.getColumnIndexOrThrow("monthly_payment"))  );
//
//                // adding to todo list
//                accounts.add(account);
//            } while (c.moveToNext());
//        }
//
//        return accounts;
//    }


    public ArrayList<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        String selectQuery = "SELECT  * FROM " + ACCOUNT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Account account = new Account();
                account.setAccount( c.getInt(c.getColumnIndexOrThrow("id")), c.getString(c.getColumnIndexOrThrow("budget_type")) , c.getString(c.getColumnIndexOrThrow("nickname")), c.getFloat(c.getColumnIndexOrThrow("balance")) ,
                        c.getFloat(c.getColumnIndexOrThrow("interest_rate"))  ,c.getFloat(c.getColumnIndexOrThrow("monthly_payment"))  );

                accounts.add(account);
            } while (c.moveToNext());
        }

        return (ArrayList<Account>) accounts;
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }



}