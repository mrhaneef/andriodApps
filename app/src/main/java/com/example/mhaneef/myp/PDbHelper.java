package com.example.mhaneef.myp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by mhaneef on 10/17/2017.
 */

public class PDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Precords.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PContract.PEntry.TABLE_NAME + " (" +
                    PContract.PEntry._ID + " INTEGER PRIMARY KEY," +
                    PContract.PEntry.COLUMN_NAME_F + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_Z + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_A + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_M + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_I + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_MODIFIED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PContract.PEntry.TABLE_NAME;

    public PDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
