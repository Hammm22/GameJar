package com.example.gamejar.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    // Tabel Users
    public static final String TABLE_USERS = "Users";
    public static final String Col_ID = "ID";
    public static final String Col_Name = "Name";
    public static final String Col_Phone = "Phone";
    public static final String Col_Password = "Password";

    // Query untuk membuat tabel Users
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Col_Name + " TEXT," +
                    Col_Phone + " TEXT UNIQUE," +
                    Col_Password + " TEXT);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
