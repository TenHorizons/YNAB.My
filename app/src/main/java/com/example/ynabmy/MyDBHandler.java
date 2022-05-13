package com.example.ynabmy;

import com.example.ynabmy.BudgetComponent.Budget;
import com.example.ynabmy.BudgetComponent.BudgetCategory;
import com.example.ynabmy.BudgetComponent.BudgetItem;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TAG = "MyDBHandler";
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ynabmy.db";
    private static final String ACCOUNT_TABLE = "accounts";
    private static final String BUDGET_TABLE = "budget";
    private static final String BUDGET_CATEGORY_TABLE = "budget_category";
    private static final String BUDGET_ITEM_TABLE = "budget_item";

    private static final String CREATE_TABLE_ACCOUNTS =
            "CREATE TABLE " + ACCOUNT_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " budget_type TEXT," +
                    " nickname TEXT," +
                    " balance FLOAT," +
                    " interest_rate FLOAT," +
                    " monthly_payment FLOAT)";
    private static final String CREATE_TABLE_BUDGET =
            "CREATE TABLE " + BUDGET_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " budget_name TEXT)";
    private static final String CREATE_TABLE_BUDGET_CATEGORY =
            "CREATE TABLE " + BUDGET_CATEGORY_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " budgetId INTEGER," +
                    " categoryName TEXT," +
                    " totalAssigned DOUBLE," +
                    " totalAvailable DOUBLE)";
    private static final String CREATE_TABLE_BUDGET_ITEM =
            "CREATE TABLE " + BUDGET_ITEM_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " categoryId INTEGER," +
                    " itemName TEXT," +
                    " assigned DOUBLE," +
                    " available DOUBLE)";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNTS);
        db.execSQL(CREATE_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_BUDGET_CATEGORY);
        db.execSQL(CREATE_TABLE_BUDGET_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);

        // create new tables
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**Create single item to add to table as row.
     * For Account, Budget, Category, Item.*/
    public long createAccount(final String budget_type,
                              final String nickname,
                              final Float balance,
                              final Float interest_rate,
                              final Float monthly_payment) {

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

    public long createBudget(final String budgetName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("budget_name", budgetName);

        // insert row
        long budget_id = db.insert(BUDGET_TABLE, null, values);

        return budget_id;
    }

    public long createBudgetCategory(final int budgetId,
                                     final String categoryName,
                                     final double totalAssigned,
                                     final double totalAvailable) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("budget_id", budgetId);
        values.put("category_name", categoryName);
        values.put("total_assigned", totalAssigned);
        values.put("total_available", totalAvailable);

        // insert row
        long category_id = db.insert(BUDGET_CATEGORY_TABLE, null, values);

        return category_id;
    }

    public long createBudgetItem(final int categoryId,
                                 final String itemName,
                                 final double assigned,
                                 final double available) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("category_id", categoryId);
        values.put("item_name", itemName);
        values.put("assigned", assigned);
        values.put("available", available);

        // insert row
        long item_id = db.insert(BUDGET_ITEM_TABLE, null, values);

        return item_id;
    }

    /** Retrieves table row
     * filtered by table name and row id*/
    public Cursor getCursor(final long id, final String table){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + table + " WHERE " + "id = " + id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null){
            c.moveToFirst();
            return c;
        }else{
            Log.e(TAG, "getQuery: error retrieving query for" + table + ", cursor == null");
            return null;
        }
    }

    /**Get single item of table.
     * For Account, Budget, Category, Item*/
    public Account getAccount(long account_id) {
        Cursor c = getCursor(account_id,ACCOUNT_TABLE);

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

    public Budget getBudget(long budget_id) {
        Cursor c = getCursor(budget_id,BUDGET_TABLE);

        Budget budget = new Budget();
        budget.setBudgetName(c.getString(c.getColumnIndexOrThrow("budget_name")));
        return budget;
    }

    public BudgetCategory getBudgetCategory(long category_id) {
        Cursor c = getCursor(category_id,BUDGET_CATEGORY_TABLE);

        BudgetCategory category = new BudgetCategory();
        category.setCategoryName(c.getString(c.getColumnIndexOrThrow("category_name")));
        category.setTotalAssigned(c.getDouble(c.getColumnIndexOrThrow("total_assigned")));
        category.setTotalAvailable(c.getDouble(c.getColumnIndexOrThrow("total_available")));
        return category;
    }

    public BudgetItem getBudgetItem(long item_id) {
        Cursor c = getCursor(item_id,BUDGET_ITEM_TABLE);

        BudgetItem item = new BudgetItem();
        item.setItemName(c.getString(c.getColumnIndexOrThrow("item_name")));
        item.setAssigned(c.getDouble(c.getColumnIndexOrThrow("assigned")));
        item.setAvailable(c.getDouble(c.getColumnIndexOrThrow("available")));
        return item;
    }

    /**Retrieves table rows
     * filtered by table name*/
    public Cursor getCursor(final String table){
        String selectQuery = "SELECT  * FROM " + table;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(selectQuery, null);
    }

    /**Get all items of table.
     * For Accounts, Budget, Category, Item.*/
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Cursor c = getCursor(ACCOUNT_TABLE);

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

    public ArrayList<Budget> getAllBudgets() {
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        Cursor c = getCursor(BUDGET_TABLE);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Budget budget = new Budget();
                budget.setBudgetName(c.getString(c.getColumnIndexOrThrow("budget_name")));
                budgets.add(budget);
            } while (c.moveToNext());
        }

        return budgets;
    }

    public ArrayList<BudgetCategory> getAllBudgetCategories() {
        ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
        Cursor c = getCursor(BUDGET_CATEGORY_TABLE);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                BudgetCategory category = new BudgetCategory();
                category.setCategoryName(c.getString(c.getColumnIndexOrThrow("category_name")));
                category.setTotalAssigned(c.getDouble(c.getColumnIndexOrThrow("total_assigned")));
                category.setTotalAvailable(c.getDouble(c.getColumnIndexOrThrow("total_available")));

                categories.add(category);
            } while (c.moveToNext());
        }

        return categories;
    }

    public ArrayList<BudgetItem> getAllBudgetItems() {
        ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();
        Cursor c = getCursor(BUDGET_ITEM_TABLE);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                BudgetItem item = new BudgetItem();
                item.setItemName(c.getString(c.getColumnIndexOrThrow("item_name")));
                item.setAssigned(c.getDouble(c.getColumnIndexOrThrow("assigned")));
                item.setAvailable(c.getDouble(c.getColumnIndexOrThrow("available")));

                items.add(item);
            } while (c.moveToNext());
        }

        return items;
    }
}