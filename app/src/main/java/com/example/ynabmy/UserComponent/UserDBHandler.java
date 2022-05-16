package com.example.ynabmy.UserComponent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ynabmy.AbstractDBHandler;


public final class UserDBHandler extends AbstractDBHandler {
    private static final String TAG = "UserDBHandler";

    public UserDBHandler(Context context) {
        super(context);
    }

    public long createUser(final String username, final String password){
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password",password);

        return super.createRow(values,super.USER_TABLE);
    }

    /**Designed so that
     * Either db is empty and return true if user enters default admin account, or
     * Returns true if user meets username and password combination.
     * Returns false in other cases.*/
    public int checkUser(final String username, final String password){
        Cursor c = getCursor(username, password);

        if(c.moveToFirst()){
            try{
                int userId = c.getInt(c.getColumnIndexOrThrow("id"));
                return userId;
            }catch(Exception e){}
        }

        return -1;
    }

    /**Returns userId based on username and password combination.*/
    public Integer getUser(final String username, final String password){
        Cursor c = getCursor(username, password);
        if(c == null) return -1;
        return c.getInt(c.getColumnIndexOrThrow("id"));
    }

    /**Modification of getCursor to allow getCursor not to be called outside of db classes.*/
    private Cursor getCursor (final String username, final String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + super.USER_TABLE
                + " WHERE username = " + username +
                " AND password = " + password;
        return db.rawQuery(selectQuery,null);
    }
}
