package com.example.mhaneef.myp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.support.constraint.solver.ArrayLinkedVariables;

import com.example.mhaneef.myp.data.H;
import com.example.mhaneef.myp.data.P;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Created by mhaneef on 10/17/2017.
 */

public class PDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Precords.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + PContract.PEntry.TABLE_NAME + " (" +
                    PContract.PEntry._ID + " INTEGER PRIMARY KEY," +
                    PContract.PEntry.COLUMN_NAME_F + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_Z + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_A + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_M + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_I + " INTEGER," +
                    PContract.PEntry.COLUMN_NAME_MODIFIED + " TEXT)";

    private static final String SQL_CREATE_HISTORY_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + PContract.HistoryEntry.TABLE_NAME + " (" +
                    PContract.HistoryEntry._ID + " INTEGER PRIMARY KEY," +
                    PContract.HistoryEntry.COLUMN_NAME_F + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_Z + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_A + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_M + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_I + " INTEGER," +
                    PContract.HistoryEntry.COLUMN_NAME_CHANNGED + " TEXT," +
                    PContract.HistoryEntry.COLUMN_NAME_MODIFIED + " timestamp not null default current_timestamp )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PContract.PEntry.TABLE_NAME;
    private static final String SQL_DELETE_HISTORY_ENTRIES =
            "DROP TABLE IF EXISTS " + PContract.HistoryEntry.TABLE_NAME;

    private static final String SQL_DELETE30DAYS = "DELETE FROM " + PContract.HistoryEntry.TABLE_NAME   +
            " WHERE "+ PContract.HistoryEntry.COLUMN_NAME_MODIFIED ;

    //private static final String SQL_SELECT_BY_ID = "Select "

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
        onCreate(db);
    }

    public void deleteHistoryOlderThen30Dya(int days){
        SQLiteDatabase db = this.getReadableDatabase();
        String d = " <= date('now','-"+days+" day')";
        db.execSQL(SQL_DELETE30DAYS+d);
    }

    public ArrayList<H> getHistoryFromDB()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                PContract.HistoryEntry._ID,
                PContract.HistoryEntry.COLUMN_NAME_F,
                PContract.HistoryEntry.COLUMN_NAME_Z,
                PContract.HistoryEntry.COLUMN_NAME_A,
                PContract.HistoryEntry.COLUMN_NAME_M,
                PContract.HistoryEntry.COLUMN_NAME_I,
                PContract.HistoryEntry.COLUMN_NAME_CHANNGED,
                PContract.HistoryEntry.COLUMN_NAME_MODIFIED,
        };
        // Filter results WHERE "title" = 'My Title'
        String selection = PContract.HistoryEntry.COLUMN_NAME_MODIFIED + " < ?";
        //String[] selectionArgs = { "2010-10-28 16:20:55" };
        String[] selectionArgs = {"date('now','-30 days')"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PContract.HistoryEntry.COLUMN_NAME_MODIFIED + " DESC";
        Cursor cursor = db.query(
                PContract.HistoryEntry.TABLE_NAME,                     // The table to query
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
        H h;
        ArrayList<H> listH = new ArrayList<>();
        while(cursor.moveToNext()) {
            h = new H();
            itemId = cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry._ID));
            h.ID = itemId;
            h.setF((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_F)));
            h.setZ((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_Z)));
            h.setA((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_A)));
            h.setM((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_M)));
            h.setI((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_I)));
            h.setColumnChangedName(cursor.getString(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_CHANNGED)));
            h.setModifiedTime(Timestamp.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_MODIFIED))));
            listH.add(h);
        }
        cursor.close();
        return listH;
    }



    public H getHistoryFromDBByID(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                PContract.HistoryEntry._ID,
                PContract.HistoryEntry.COLUMN_NAME_F,
                PContract.HistoryEntry.COLUMN_NAME_Z,
                PContract.HistoryEntry.COLUMN_NAME_A,
                PContract.HistoryEntry.COLUMN_NAME_M,
                PContract.HistoryEntry.COLUMN_NAME_I,
                PContract.HistoryEntry.COLUMN_NAME_CHANNGED,
                PContract.HistoryEntry.COLUMN_NAME_MODIFIED,
        };
        // Filter results WHERE "title" = 'My Title'
        String selection =  PContract.HistoryEntry._ID + " = ?";
        //String[] selectionArgs = { "2010-10-28 16:20:55" };
        String[] selectionArgs = {id +""};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PContract.HistoryEntry.COLUMN_NAME_MODIFIED + " DESC";
        Cursor cursor = db.query(
                PContract.HistoryEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );




        //List itemIds = new ArrayList<>();
        long itemId = 0;
        int count = cursor.getCount();
        H h = null;
        ArrayList<H> listH = new ArrayList<>();
        while(cursor.moveToNext()) {
            h = new H();
            itemId = cursor.getLong(cursor.getColumnIndexOrThrow(PContract.PEntry._ID));
            h.ID = itemId;
            h.setF((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_F)));
            h.setZ((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_Z)));
            h.setA((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_A)));
            h.setM((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_M)));
            h.setI((int)cursor.getLong(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_I)));
            h.setColumnChangedName(cursor.getString(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_CHANNGED)));
            h.setModifiedTime(Timestamp.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(PContract.HistoryEntry.COLUMN_NAME_MODIFIED))));
            listH.add(h);
        }
        cursor.close();
        return h;
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

    public int saveHistory(P p, String colName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PContract.PEntry.COLUMN_NAME_F, p.getF());
        values.put(PContract.PEntry.COLUMN_NAME_Z, p.getZ());
        values.put(PContract.PEntry.COLUMN_NAME_A, p.getA());
        values.put(PContract.PEntry.COLUMN_NAME_M, p.getM());
        values.put(PContract.PEntry.COLUMN_NAME_I, p.getI());
        values.put(PContract.HistoryEntry.COLUMN_NAME_CHANNGED, colName);

        db.insert(PContract.HistoryEntry.TABLE_NAME,null,values);

        return 0;
    }


}
