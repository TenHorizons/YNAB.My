package com.example.ynabmy.BudgetComponent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ynabmy.AbstractDBHandler;

import java.util.ArrayList;

public class BudgetDBHandler extends AbstractDBHandler {
    private static final String TAG = "BudgetDBHandler";

    protected BudgetDBHandler(Context context) {
        super(context);
    }


    public long createBudget(final int userId, final String budgetName) {
        ContentValues values = new ContentValues();
        values.put("user_id",userId);
        values.put("budget_name", budgetName);

        return super.createRow(values,super.BUDGET_TABLE);
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

        return super.createRow(values,super.BUDGET_CATEGORY_TABLE);
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

        return super.createRow(values,super.BUDGET_ITEM_TABLE);
    }

    protected Budget getBudget(long budget_id) {
        Cursor c = super.getCursor(budget_id,super.BUDGET_TABLE);

        if(c==null) return null;

        Budget budget = new Budget();
        budget.setBudgetName(c.getString(c.getColumnIndexOrThrow("budget_name")));
        return budget;
    }

    public ArrayList<Budget> getBudgetsByUser (final int userId){
        ArrayList<Budget> budgets = new ArrayList<>();
        Cursor c = super.getCursor(super.BUDGET_TABLE,"user_id",userId);

        if(c==null) return null;

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do{
                Budget budget = new Budget();
                budget.setBudgetName(c.getString(c.getColumnIndexOrThrow("budget_name")));
                budgets.add(budget);
            }while(c.moveToNext());
        }

        return budgets;
    }

    public BudgetCategory getBudgetCategory(long category_id) {
        Cursor c = super.getCursor(category_id,super.BUDGET_CATEGORY_TABLE);

        BudgetCategory category = new BudgetCategory();
        category.setCategoryName(c.getString(c.getColumnIndexOrThrow("category_name")));
        category.setTotalAssigned(c.getDouble(c.getColumnIndexOrThrow("total_assigned")));
        category.setTotalAvailable(c.getDouble(c.getColumnIndexOrThrow("total_available")));

        return category;
    }

    public ArrayList<BudgetCategory> getCategoriesByBudget (final int budgetId){
        ArrayList<BudgetCategory> categories = new ArrayList<>();
        Cursor c = super.getCursor(super.BUDGET_CATEGORY_TABLE,"budget_id",budgetId);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do{
                BudgetCategory category = new BudgetCategory();
                category.setCategoryName(c.getString(c.getColumnIndexOrThrow("category_name")));
                category.setTotalAssigned(c.getDouble(c.getColumnIndexOrThrow("total_assigned")));
                category.setTotalAvailable(c.getDouble(c.getColumnIndexOrThrow("total_available")));
                categories.add(category);
            }while(c.moveToNext());
        }

        return categories;
    }

    public BudgetItem getBudgetItem(long item_id) {
        Cursor c = super.getCursor(item_id,super.BUDGET_ITEM_TABLE);

        BudgetItem item = new BudgetItem();
        item.setItemName(c.getString(c.getColumnIndexOrThrow("item_name")));
        item.setAssigned(c.getDouble(c.getColumnIndexOrThrow("assigned")));
        item.setAvailable(c.getDouble(c.getColumnIndexOrThrow("available")));

        return item;
    }

    public ArrayList<BudgetItem> getBudgetItemsByCategory(final int categoryId) {
        ArrayList<BudgetItem> items = new ArrayList<BudgetItem>();
        Cursor c = super.getCursor(super.BUDGET_ITEM_TABLE,"category_id",categoryId);

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
