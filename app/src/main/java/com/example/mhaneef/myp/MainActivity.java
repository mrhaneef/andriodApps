package com.example.mhaneef.myp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mhaneef.myp.db.PContract;
import com.example.mhaneef.myp.db.PDbHelper;

public class MainActivity extends AppCompatActivity {

    protected P p;
    PDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //init
        mDbHelper = new PDbHelper(getBaseContext());
        p = loadStatusofP();
        DisplayViews(p);
        //TextView f = (TextView) findViewById (R.id.f_textView); f.setText(p.getF()+"");
        //TextView z = (TextView) findViewById (R.id.z_textView); z.setText(p.getZ()+"");
        //TextView a = (TextView) findViewById (R.id.a_textView); a.setText(p.getA()+"");
        //TextView m = (TextView) findViewById (R.id.m_textView); m.setText(p.getM()+"");
        //TextView i = (TextView) findViewById (R.id.i_textView); i.setText(p.getI()+"");
    }



    public P loadStatusofP(){
        return mDbHelper.getPFromDB();
/*        p = new P(0,0,0,0,0);
        // Gets the data repository in write mode

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
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

     *//*   if(count == 0){
            db = mDbHelper.getWritableDatabase();

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
        }*//*

        return p ;*/
    }

    public void saveStatusofP(P p){
        //save to DB;
        int db = mDbHelper.saveStatusOfP(p);
        /*getWritableDatabase();
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
                selectionArgs);*/

        DisplayViews(p);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickF(View view){
        TextView input = (TextView) findViewById(R.id.f_textView);
        //String string = input.getText().toString();
        p.setF(p.getF() + 1);
        input.setText(p.getF()-Integer.parseInt(p.getInDays()) +"");
        saveStatusofP(p);
        //Toast.makeText(this, "User " + string + " created.", Toast.LENGTH_LONG).show();
    }

    public void onClickZ(View view){
        TextView input = (TextView) findViewById(R.id.z_textView);
        //String string = input.getText().toString();
        p.setZ(p.getZ() + 1);
        input.setText(p.getZ() -Integer.parseInt(p.getInDays())  + "");
        saveStatusofP(p);
        //Toast.makeText(this, "User " + string + " created.", Toast.LENGTH_LONG).show();
    }

    public void onClickA(View view){
        TextView input = (TextView) findViewById(R.id.a_textView);
        //String string = input.getText().toString();
        p.setA(p.getA() + 1);
        input.setText(p.getA() -Integer.parseInt(p.getInDays())  +"");
        saveStatusofP(p);
        //Toast.makeText(this, "User " + string + " created.", Toast.LENGTH_LONG).show();
    }

    public void onClickM(View view){
        TextView input = (TextView) findViewById(R.id.m_textView);
        //String string = input.getText().toString();
        p.setM(p.getM() + 1);
        input.setText(p.getM()-Integer.parseInt(p.getInDays()) +"");
        saveStatusofP(p);
        //Toast.makeText(this, "User " + string + " created.", Toast.LENGTH_LONG).show();
    }

    public void onClickI(View view){
        TextView input = (TextView) findViewById(R.id.i_textView);
        //String string = input.getText().toString();
        p.setI(p.getI() + 1);
        input.setText(p.getI()-Integer.parseInt(p.getInDays())  +"");
        saveStatusofP(p);
        //Toast.makeText(this, "User " + string + " created.", Toast.LENGTH_LONG).show();
    }

    public void DisplayViews(P p){
        TextView input = (TextView) findViewById(R.id.days_textView);
        input.setText(p.getInDays());
        input = (TextView) findViewById(R.id.month_textView);
        input.setText(p.getInMonth());
        input = (TextView) findViewById(R.id.Year_textView);
        input.setText(p.getInYears());

        input = (TextView) findViewById(R.id.f_textView);
        input.setText(p.getF()-Integer.parseInt(p.getInDays())  +"");
        input = (TextView) findViewById(R.id.z_textView);
        input.setText(p.getZ()-Integer.parseInt(p.getInDays())  +"");
        input = (TextView) findViewById(R.id.a_textView);
        input.setText(p.getA()-Integer.parseInt(p.getInDays())  +"");
        input = (TextView) findViewById(R.id.m_textView);
        input.setText(p.getM()-Integer.parseInt(p.getInDays())  +"");
        input = (TextView) findViewById(R.id.i_textView);
        input.setText(p.getI()-Integer.parseInt(p.getInDays())  +"");
    }

    public void onClickReset(P p)
    {
        p.reset(0,0,0,0,0);
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
