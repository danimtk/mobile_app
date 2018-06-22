package com.example.dani.app12.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dani on 03/04/18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

        // Logcat tag
        private static final String LOG = "DatabaseHelper";

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "phoup_base";

        // Table Names
        public static final String TABLE_FILE = "file";

        // Common column names file
        public static final String ID     = "id";
        public static final String NAME   = "name";

        // Table Create Statements
        // Citacao table create statement
        private static final String CREATE_TABLE_CITACAO = "CREATE TABLE "
                + TABLE_FILE + "("
                + ID       + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME  + " TEXT"
                + ")";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // creating required tables
            db.execSQL(CREATE_TABLE_CITACAO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILE);

            // create new tables
            onCreate(db);
        }
}
