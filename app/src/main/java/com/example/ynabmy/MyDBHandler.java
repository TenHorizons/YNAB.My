package com.example.ynabmy;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class MyDBHandler extends AbstractDBHandler {
    //information of database
    private static final String ACCOUNT_TABLE = "accounts";

    private static final String CREATE_TABLE_ACCOUNTS =
            "CREATE TABLE " + ACCOUNT_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " budget_type TEXT," +
                    " nickname TEXT," +
                    " balance FLOAT," +
                    " interest_rate FLOAT," +
                    " monthly_payment FLOAT)";

    public MyDBHandler(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db,ACCOUNT_TABLE);
    }

    public long createAccount(final String budget_type,
                              final String nickname,
                              final Float balance,
                              final Float interest_rate,
                              final Float monthly_payment) {
        ContentValues values = new ContentValues();
        values.put("budget_type", budget_type);
        values.put("nickname", nickname);
        values.put("balance", balance);
        values.put("interest_rate", interest_rate);
        values.put("monthly_payment", monthly_payment);

        return super.createRow(values,ACCOUNT_TABLE);
    }

    /**Get single item of table.*/
    public Account getAccount(long account_id) {
        Cursor c = super.getCursor(account_id,ACCOUNT_TABLE);

        Account acc = new Account();
        acc.setAccount( c.getInt(c.getColumnIndexOrThrow("id")),
                c.getString(c.getColumnIndexOrThrow("budget_type")),
                c.getString(c.getColumnIndexOrThrow("nickname")),
                c.getFloat(c.getColumnIndexOrThrow("balance")),
                c.getFloat(c.getColumnIndexOrThrow("interest_rate")),
                c.getFloat(c.getColumnIndexOrThrow("monthly_payment"))
        );

        return acc;
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Cursor c = super.getCursor(ACCOUNT_TABLE);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Account account = new Account();
                account.setAccount( c.getInt(c.getColumnIndexOrThrow("id")),
                        c.getString(c.getColumnIndexOrThrow("budget_type")),
                        c.getString(c.getColumnIndexOrThrow("nickname")),
                        c.getFloat(c.getColumnIndexOrThrow("balance")),
                        c.getFloat(c.getColumnIndexOrThrow("interest_rate")),
                        c.getFloat(c.getColumnIndexOrThrow("monthly_payment"))
                );
                accounts.add(account);
            } while (c.moveToNext());
        }

        return accounts;
    }
}