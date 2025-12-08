    package com.example.gamejar.DataBase;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class DataBaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "app.db";
        private static final int DATABASE_VERSION = 2; // versi baru karena ada wishlist

        // Tabel Users
        public static final String TABLE_USERS = "Users";
        public static final String Col_ID = "ID";
        public static final String Col_Name = "Name";
        public static final String Col_Phone = "Phone";
        public static final String Col_Password = "Password";

        // Tabel Wishlist
        public static final String TABLE_WISHLIST = "Wishlist";
        public static final String Col_Wish_ID = "ID";
        public static final String Col_Game_Name = "GameName";
        public static final String Col_Game_Price = "Price";
        public static final String Col_Saving_Per_Day = "SavingPerDay";
        public static final String Col_Saving_Plan = "Plan"; // Harian/Mingguan/Bulanan
        public static final String Col_User_Phone = "UserPhone"; // foreign key ke Users

        // Query buat tabel Users
        private static final String CREATE_USERS_TABLE =
                "CREATE TABLE " + TABLE_USERS + " (" +
                        Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        Col_Name + " TEXT," +
                        Col_Phone + " TEXT UNIQUE," +
                        Col_Password + " TEXT);";

        // Query buat tabel Wishlist
        private static final String CREATE_WISHLIST_TABLE =
                "CREATE TABLE " + TABLE_WISHLIST + " (" +
                        Col_Wish_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        Col_Game_Name + " TEXT," +
                        Col_Game_Price + " TEXT," +
                        Col_Saving_Per_Day + " TEXT," +
                        Col_Saving_Plan + " TEXT," +
                        Col_User_Phone + " TEXT," +
                        "FOREIGN KEY(" + Col_User_Phone + ") REFERENCES " + TABLE_USERS + "(" + Col_Phone + "));";

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_WISHLIST_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }
