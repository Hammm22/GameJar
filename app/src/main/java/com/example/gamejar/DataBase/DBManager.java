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
        return db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_USERS +
                " WHERE " + DataBaseHelper.Col_Phone + " = ?", new String[]{phone});
    }

    public boolean addWishlist(String userPhone, String gameName, String price, String savingPerDay, String plan){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Col_User_Phone, userPhone);
        values.put(DataBaseHelper.Col_Game_Name, gameName);
        values.put(DataBaseHelper.Col_Game_Price, price);
        values.put(DataBaseHelper.Col_Saving_Per_Day, savingPerDay);
        values.put(DataBaseHelper.Col_Saving_Plan, plan);

        long result = db.insert(DataBaseHelper.TABLE_WISHLIST, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getWishlist(String userPhone){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_WISHLIST +
                " WHERE " + DataBaseHelper.Col_User_Phone + " = ?", new String[]{userPhone});
    }

    public boolean deleteWish(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(DataBaseHelper.TABLE_WISHLIST, DataBaseHelper.Col_Wish_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    public boolean updateWishlist(int id, String gameName, String price, String savingPerDay, String plan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Col_Game_Name, gameName);
        values.put(DataBaseHelper.Col_Game_Price, price);
        values.put(DataBaseHelper.Col_Saving_Per_Day, savingPerDay);
        values.put(DataBaseHelper.Col_Saving_Plan, plan);
        int result = db.update(DataBaseHelper.TABLE_WISHLIST, values, DataBaseHelper.Col_Wish_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

}
