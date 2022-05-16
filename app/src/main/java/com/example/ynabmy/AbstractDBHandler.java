package com.example.ynabmy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AbstractDBHandler extends SQLiteOpenHelper {
    private static final String TAG = "AbstractDBHandler";
    //information of database
    protected static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "ynabmy.db";

    /**All strings and queries for database creation.*/
    protected static final String ACCOUNT_TABLE = "accounts";
    protected static final String BUDGET_TABLE = "budget";
    protected static final String BUDGET_CATEGORY_TABLE = "budget_category";
    protected static final String BUDGET_ITEM_TABLE = "budget_item";
    protected static final String USER_TABLE = "user";

    private static final String CREATE_TABLE_ACCOUNTS =
            "CREATE TABLE " + ACCOUNT_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " budget_type TEXT," +
                    " nickname TEXT," +
                    " balance FLOAT," +
                    " interest_rate FLOAT," +
                    " monthly_payment FLOAT)";
    private static final String CREATE_TABLE_BUDGET =
            "CREATE TABLE " + BUDGET_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " user_id INTEGER," +
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
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + USER_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " username TEXT," +
                    " password TEXT)";

    protected AbstractDBHandler(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNTS);
        db.execSQL(CREATE_TABLE_BUDGET);
        db.execSQL(CREATE_TABLE_BUDGET_CATEGORY);
        db.execSQL(CREATE_TABLE_BUDGET_ITEM);
        db.execSQL(CREATE_TABLE_USER);
    }

    protected void onCreate(final SQLiteDatabase db,
                            final String tableToCreate) {
        db.execSQL(tableToCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_CATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BUDGET_ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        // create new tables
        onCreate(db);
    }

    public void onUpgrade(final SQLiteDatabase db,
                          final String tableToUpgrade) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + tableToUpgrade);

        // create new tables
        onCreate(db,tableToUpgrade);
    }

    /**Adds an object to a table as a row.
     * @param values The object to be added.
     * @param table The table to add the object.*/
    public long createRow(final ContentValues values,
                          final String table){
        SQLiteDatabase db = this.getWritableDatabase();
        // insert row
        long id = db.insert(table, null, values);
        return id;
    }

    /**Retrieves table rows
     * filtered by table name*/
    protected Cursor getCursor(final String table){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + table;
        return db.rawQuery(selectQuery, null);
    }

    /** Retrieves table row
     * filtered by table name and row id*/
    protected Cursor getCursor(final long id,
                               final String table){
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

    /**Retrieves table rows
     * filtered by table, foreign key type, and foreign key.*/
    protected Cursor getCursor(final String table,
                               final String foreignKeyType,
                               final long foreignKey){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + table + " WHERE " + foreignKeyType + " = " + foreignKey;
        return db.rawQuery(selectQuery, null);
    }

    public void createDefaultAdmin() {
        ContentValues values = new ContentValues();
        values.put("username", "admin");
        values.put("password", "admin");

        createRow(values,USER_TABLE);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
