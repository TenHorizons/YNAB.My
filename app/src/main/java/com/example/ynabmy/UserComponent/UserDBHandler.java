package com.example.ynabmy.UserComponent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ynabmy.AbstractDBHandler;
import com.example.ynabmy.BudgetComponent.BudgetDBHandler;


public final class UserDBHandler extends AbstractDBHandler {
    private static final String TAG = "UserDBHandler";
    //information of database
    private static final String USER_TABLE = "user";

    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + USER_TABLE + " (id INTEGER primary key autoincrement NOT NULL," +
                    " username TEXT," +
                    " password TEXT)";

    public UserDBHandler(Context context) {super(context);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db,USER_TABLE);
    }

    public long createUser(final String username, final String password){
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password",password);

        return super.createRow(values,USER_TABLE);
    }

//    public User getUser(long user_id){
//        Cursor c = super.getCursor(user_id,USER_TABLE);
//
//        String username = c.getString(c.getColumnIndexOrThrow("username"));
//        String password = c.getString(c.getColumnIndexOrThrow("password"));
//
//        return new User(username,password);
//    }

    /**Designed so that
     * Either db is empty and return true if user enters default admin account, or
     * Returns true if user meets username and password combination.
     * Returns false in other cases.*/
    public boolean checkUser(final String username, final String password){
        Cursor c = getCursor(USER_TABLE);
        if(c == null) createDefaultUser();
        c = getCursor(username, password);
        if(c != null) return true;
        return false;
    }

    /**Returns userId based on username and password combination.*/
    public Integer getUser(final String username, final String password, final Context context){
        Cursor c = getCursor(username, password);
        if(c == null) return -1;
        return c.getInt(c.getColumnIndexOrThrow("id"));
    }

    /**Modification of getCursor to allow getCursor not to be called outside of db classes.*/
    private Cursor getCursor (final String username, final String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + USER_TABLE
                + " WHERE username = " + username +
                " AND password = " + password;
        return db.rawQuery(selectQuery,null);
    }

    private void createDefaultUser(){
        createUser("admin","admin");
    }
}
