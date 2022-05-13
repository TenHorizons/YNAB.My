package com.example.ynabmy.BudgetComponent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ynabmy.AbstractDBHandler;

import java.util.ArrayList;

public class BudgetDBHandler extends AbstractDBHandler {
    private static final String TAG = "BudgetDBHandler";
    //information of database
    private static final String BUDGET_TABLE = "budget";
    private static final String BUDGET_CATEGORY_TABLE = "budget_category";
    private static final String BUDGET_ITEM_TABLE = "budget_item";

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

    public BudgetDBHandler(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_BUDGET_CATEGORY);
        db.execSQL(CREATE_TABLE_BUDGET_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_ITEM_TABLE);

        // create new tables
        onCreate(db);
    }

    public long createBudget(final String budgetName) {
        ContentValues values = new ContentValues();
        values.put("budget_name", budgetName);

        return super.createRow(values,BUDGET_TABLE);
    }

    public long createBudgetCategory(final int budgetId,
                                     final String categoryName,
                                     final double totalAssigned,
                                     final double totalAvailable) {
        ContentValues values = new ContentValues();
        values.put("budget_id", budgetId);
        values.put("category_name", categoryName);
        values.put("total_assigned", totalAssigned);
        values.put("total_available", totalAvailable);

        return super.createRow(values,BUDGET_CATEGORY_TABLE);
    }

    public long createBudgetItem(final int categoryId,
                                 final String itemName,
                                 final double assigned,
                                 final double available) {
        ContentValues values = new ContentValues();
        values.put("category_id", categoryId);
        values.put("item_name", itemName);
        values.put("assigned", assigned);
        values.put("available", available);

        return super.createRow(values,BUDGET_ITEM_TABLE);
    }

    public Budget getBudget(long budget_id) {
        Cursor c = super.getCursor(budget_id,BUDGET_TABLE);

        Budget budget = new Budget();
        budget.setBudgetName(c.getString(c.getColumnIndexOrThrow("budget_name")));
        return budget;
    }

    public BudgetCategory getBudgetCategory(long category_id) {
        Cursor c = super.getCursor(category_id,BUDGET_CATEGORY_TABLE);

        BudgetCategory category = new BudgetCategory();
        category.setCategoryName(c.getString(c.getColumnIndexOrThrow("category_name")));
        category.setTotalAssigned(c.getDouble(c.getColumnIndexOrThrow("total_assigned")));
        category.setTotalAvailable(c.getDouble(c.getColumnIndexOrThrow("total_available")));

        return category;
    }

    public BudgetItem getBudgetItem(long item_id) {
        Cursor c = super.getCursor(item_id,BUDGET_ITEM_TABLE);

        BudgetItem item = new BudgetItem();
        item.setItemName(c.getString(c.getColumnIndexOrThrow("item_name")));
        item.setAssigned(c.getDouble(c.getColumnIndexOrThrow("assigned")));
        item.setAvailable(c.getDouble(c.getColumnIndexOrThrow("available")));

        return item;
    }

    public ArrayList<Budget> getAllBudgets() {
        ArrayList<Budget> budgets = new ArrayList<Budget>();
        Cursor c = super.getCursor(BUDGET_TABLE);

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

    public ArrayList<BudgetCategory> getBudgetCategoriesByBudgetId(final int budgetId) {
        ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
        String selectQuery = "SELECT  * FROM " + BUDGET_CATEGORY_TABLE + " WHERE "
                + "budget_id = " + budgetId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

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
        Cursor c = super.getCursor(BUDGET_ITEM_TABLE);

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
