package com.example.gamejar.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private final DataBaseHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public boolean registerUser(String name, String phone, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Col_Name, name);
        values.put(DataBaseHelper.Col_Phone, phone);
        values.put(DataBaseHelper.Col_Password, password);

        long result = db.insert(DataBaseHelper.TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean loginUser(String nameOrPhone, String password){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DataBaseHelper.TABLE_USERS +
                " WHERE (" + DataBaseHelper.Col_Name + "=? OR " + DataBaseHelper.Col_Phone + "=?) AND " + DataBaseHelper.Col_Password + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{nameOrPhone, nameOrPhone, password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public Cursor getUser(String phone){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_USERS + " WHERE " + DataBaseHelper.Col_Phone + " = ?", new String[]{phone});
        return cursor;
    }
}
