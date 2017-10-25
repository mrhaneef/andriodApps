package com.example.mhaneef.myp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mhaneef.myp.P;
import com.example.mhaneef.myp.db.PContract;


/**
 * Created by mhaneef on 10/17/2017.
 */

public class PDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
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

    private static final String SQL_CREATE_HISTORY_ENTRIES =
            "CREATE TABLE " + PContract.HistoryEntry.TABLE_NAME + " (" +
                    PContract.HistoryEntry._ID + " INTEGER PRIMARY KEY," +
                    PContract.HistoryEntry.COLUMN_NAME_F + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_Z + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_A + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_M + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_I + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_CHANNGED + " TEXT," +
                    PContract.HistoryEntry.COLUMN_NAME_MODIFIED + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PContract.PEntry.TABLE_NAME;
    private static final String SQL_DELETE_HISTORY_ENTRIES =
            "DROP TABLE IF EXISTS " + PContract.HistoryEntry.TABLE_NAME;

    public PDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HISTORY_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        //onCreate(db);
    }


    public P getPFromDB()
    {
        P p = new P(0,0,0,0,0);
        // Gets the data repository in write mode

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                PContract.PEntry._ID,
                PContract.PEntry.COLUMN_NAME_F,
                PContract.PEntry.COLUMN_NAME_Z,
                PContract.PEntry.COLUMN_NAME_A,
                PContract.PEntry.COLUMN_NAME_M,
                PContract.PEntry.COLUMN_NAME_I,
                PContract.PEntry.COLUMN_NAME_MODIFIED,
        };
        // Filter results WHERE "title" = 'My Title'
        String selection = PContract.PEntry.COLUMN_NAME_F + " = ?";
        String[] selectionArgs = { "%" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PContract.PEntry.COLUMN_NAME_F + " DESC";
        Cursor cursor = db.query(
                PContract.PEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        //List itemIds = new ArrayList<>();
        long itemId = 0;
        int count = cursor.getCount();
        while(cursor.moveToNext()) {
            itemId = cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry._ID));
            p.setF((int) cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry.COLUMN_NAME_F)));
            p.setZ((int) cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry.COLUMN_NAME_Z)));
            p.setA((int) cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry.COLUMN_NAME_A)));
            p.setM((int) cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry.COLUMN_NAME_M)));
            p.setI((int) cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry.COLUMN_NAME_I)));
        }
        cursor.close();

        if(count == 0){
            //Running the first time after installation of application initializing DB
            db = this.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(PContract.PEntry.COLUMN_NAME_F, 0);
            values.put(PContract.PEntry.COLUMN_NAME_Z, 0);
            values.put(PContract.PEntry.COLUMN_NAME_A, 0);
            values.put(PContract.PEntry.COLUMN_NAME_M, 0);
            values.put(PContract.PEntry.COLUMN_NAME_I, 0);
            values.put(PContract.PEntry.COLUMN_NAME_MODIFIED, 0);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(PContract.PEntry.TABLE_NAME, null, values);
        }

        return p ;
    }

    public int saveStatusOfP(P p)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PContract.PEntry.COLUMN_NAME_F, p.getF());
        values.put(PContract.PEntry.COLUMN_NAME_Z, p.getZ());
        values.put(PContract.PEntry.COLUMN_NAME_A, p.getA());
        values.put(PContract.PEntry.COLUMN_NAME_M, p.getM());
        values.put(PContract.PEntry.COLUMN_NAME_I, p.getI());
        String selection = PContract.PEntry.COLUMN_NAME_F + " LIKE ?";
        String[] selectionArgs = { "%" };
        int count = db.update(
                PContract.PEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


}
