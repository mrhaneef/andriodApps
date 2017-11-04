package com.example.mhaneef.myp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mhaneef.myp.data.P;
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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //init
        mDbHelper = new PDbHelper(getBaseContext());
        p = loadStatusofP();
        DisplayViews(p);
    }



    public P loadStatusofP(){
        mDbHelper.deleteHistoryOlderThen30Dya(30);
        return mDbHelper.getPFromDB();
    }

    public void saveStatusofP(P p, String colName){
        //save to DB;
        int db = mDbHelper.saveStatusOfP(p);
        mDbHelper.saveHistory(p, colName);
        //mDbHelper.getHistoryFromDB();

        DisplayViews(p);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    public void onClickF(View view){
        TextView input = (TextView) findViewById(R.id.f_textView);
        p.setF(p.getF() + 1);
        saveStatusofP(p,"F");
    }

    public void onClickZ(View view){
        TextView input = (TextView) findViewById(R.id.z_textView);
        p.setZ(p.getZ() + 1);
        saveStatusofP(p,"Z");
    }

    public void onClickA(View view){
        TextView input = (TextView) findViewById(R.id.a_textView);
        p.setA(p.getA() + 1);
        saveStatusofP(p,"A");
    }

    public void onClickM(View view){
        TextView input = (TextView) findViewById(R.id.m_textView);
        p.setM(p.getM() + 1);
        saveStatusofP(p,"M");
    }

    public void onClickI(View view){
        TextView input = (TextView) findViewById(R.id.i_textView);
        p.setI(p.getI() + 1);
        saveStatusofP(p,"I");
    }

    public void DisplayViews(P p){
        TextView input = (TextView) findViewById(R.id.days_textView);
        input.setText(p.getInDays());
        input = (TextView) findViewById(R.id.month_textView);
        input.setText(p.getInMonth());
        input = (TextView) findViewById(R.id.Year_textView);
        input.setText(p.getInYears());

        input = (TextView) findViewById(R.id.f_textView);
        input.setText(p.getTimeAfterSubstractions(p.getF()));
        input = (TextView) findViewById(R.id.z_textView);
        input.setText(p.getTimeAfterSubstractions(p.getZ()));
        input = (TextView) findViewById(R.id.a_textView);
        input.setText(p.getTimeAfterSubstractions(p.getA()));
        input = (TextView) findViewById(R.id.m_textView);
        input.setText(p.getTimeAfterSubstractions(p.getM()));
        input = (TextView) findViewById(R.id.i_textView);
        input.setText(p.getTimeAfterSubstractions(p.getI()));
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


    //Click on settings
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                //newGame();
                return true;
            case R.id.action_history:
                //setContentView(R.layout.activity_history);
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //public void onClickCloseHistory(View view){
       // setContentView(R.layout.activity_main);
    //}

}
