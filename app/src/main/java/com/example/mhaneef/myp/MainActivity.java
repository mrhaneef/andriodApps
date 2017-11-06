package com.example.mhaneef.myp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        DisplayViews(p);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onClickF(View view){
        TextView input = (TextView) findViewById(R.id.f_textView);
        displayToast("Increase "+ " Fjr " + "Salah");
        p.setF(p.getF() + 1);
        saveStatusofP(p,"F");
    }

    public void onClickZ(View view){
        TextView input = (TextView) findViewById(R.id.z_textView);
        displayToast("Increase "+ " Zhr " + "Salah");
        p.setZ(p.getZ() + 1);
        saveStatusofP(p,"Z");
    }

    public void onClickA(View view){
        TextView input = (TextView) findViewById(R.id.a_textView);
        displayToast("Increase "+ " Asr " + "Salah");
        p.setA(p.getA() + 1);
        saveStatusofP(p,"A");
    }

    public void onClickM(View view){
        TextView input = (TextView) findViewById(R.id.m_textView);
        displayToast("Increase "+ " Magrib " + "Salah");
        p.setM(p.getM() + 1);
        saveStatusofP(p,"M");
    }

    public void onClickI(View view){
        TextView input = (TextView) findViewById(R.id.i_textView);
        displayToast("Increated "+ " Isha " + "Salah");
        p.setI(p.getI() + 1);
        saveStatusofP(p,"I");
    }

    public void DisplayViews(P p){

        TextView input = (TextView) findViewById(R.id.month_textView);
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

        input = (TextView) findViewById(R.id.days_textView);
        if(Integer.parseInt(input.getText().toString()) < Integer.parseInt(p.getInDays()) && Integer.parseInt(input.getText().toString()) != 0){
            displayToast("Completed one DAY subscriting 1 from every Salah");
        }
        input.setText(p.getInDays());
    }

    public void displayToast(String msg)
    {

        // Inflating the layout for the toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_custom));

        // Typecasting and finding the view in the inflated layout
        TextView text = (TextView) layout.findViewById(R.id.tvtoast);

        // Setting the text to be displayed in the Toast
        text.setText(msg);
        text.setWidth(800);
        text.setHeight(800);


        // Setting the color of the Text to be displayed in the toast
        text.setTextColor(Color.rgb(0, 132, 219));

        // Creating the Toast
        Toast toast = new Toast(getApplicationContext());

        // Setting the position of the Toast to centre
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        // Setting the duration of the Toast
        toast.setDuration(Toast.LENGTH_LONG);

        // Setting the Inflated Layout to the Toast
        toast.setView(layout);

        // Showing the Toast
        toast.show();
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


}
