package com.example.ynabmy.UserComponent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ynabmy.AbstractDBHandler;

public class UserDBHandler extends AbstractDBHandler {
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

    public User getUser(long user_id){
        Cursor c = super.getCursor(user_id,USER_TABLE);

        String username = c.getString(c.getColumnIndexOrThrow("username"));
        String password = c.getString(c.getColumnIndexOrThrow("password"));

        return new User(username,password);
    }
}
