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

    public AbstractDBHandler(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {}

    public void onCreate(final SQLiteDatabase db, final String tableToCreate) {
        db.execSQL(tableToCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void onUpgrade(final SQLiteDatabase db, final String tableToUpgrade) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + tableToUpgrade);

        // create new tables
        onCreate(db,tableToUpgrade);
    }

    /**Adds an object to a table as a row.
     * @param values The object to be added.
     * @param table The table to add the object.*/
    public long createRow(final ContentValues values, final String table){
        SQLiteDatabase db = this.getWritableDatabase();
        // insert row
        long id = db.insert(table, null, values);
        return id;
    }

    /**Retrieves table rows
     * filtered by table name*/
    public Cursor getCursor(final String table){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + table;
        return db.rawQuery(selectQuery, null);
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

    /**Retrieves table rows
     * filtered by table, foreign key type, and foreign key.*/
    public Cursor getCursor(final String table, final String foreignKeyType, final long foreignKey){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + table + " WHERE " + foreignKeyType + " = " + foreignKey;
        return db.rawQuery(selectQuery, null);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
