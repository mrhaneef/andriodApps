package com.example.mhaneef.myp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.mhaneef.myp.db.PContract;
import com.example.mhaneef.myp.db.PDbHelper;

public class MainActivity extends AppCompatActivity {

    protected P p;
    PDbHelper mDbHelper;
    boolean userconfim = false;
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
        P p = new P(0,0,0,0,0);
        try {
            p = mDbHelper.getPFromDB();
            mDbHelper.getHistoryFromDB();
            mDbHelper.deleteHistoryOlderThen30Dya(30);
        }catch (Exception e){
            Log.i("ERROR",e.getMessage());
        }
        return p;
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
        if(displayDialogAndSave(view,p,"F"))
        {
            p.setF(p.getF() + 1);
            saveStatusofP(p,"F");
        }
    }

    public void onClickZ(View view){
        TextView input = (TextView) findViewById(R.id.z_textView);
        if(displayDialogAndSave(view,p,"Z"))
        {
            p.setZ(p.getZ() + 1);
            saveStatusofP(p,"Z");
        }
    }

    public void onClickA(View view){
        TextView input = (TextView) findViewById(R.id.a_textView);
        if(displayDialogAndSave(view,p,"A"))
        {
            p.setA(p.getA() + 1);
            saveStatusofP(p,"A");
        }
    }

    public void onClickM(View view){
        TextView input = (TextView) findViewById(R.id.m_textView);
        if(displayDialogAndSave(view,p,"M"))
        {
            p.setM(p.getM() + 1);
            saveStatusofP(p,"M");
        }
    }

    public void onClickI(View view){
        TextView input = (TextView) findViewById(R.id.i_textView);
        if(displayDialogAndSave(view,p,"I"))
        {
            p.setI(p.getI() + 1);
            saveStatusofP(p,"I");
        }
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
        try {
            if (Integer.parseInt(input.getText().toString()) < Integer.parseInt(p.getInDays()) && Integer.parseInt(input.getText().toString()) != 0) {
                displayToast("Completed one DAY subscriting 1 from every Salah",Toast.LENGTH_LONG);
            }
        }catch (Exception e){}
        input.setText(p.getInDays());
    }

    public void displayToast(String msg,int howLong)
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
        toast.setDuration(howLong);

        // Setting the Inflated Layout to the Toast
        toast.setView(layout);

        // Showing the Toast
        toast.show();
    }


    public boolean displayDialogAndSave(View view, final P p, final String columName){

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(view.getContext());

        builder.setMessage("Increase " + columName + " S ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                userconfim = true;
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //CODE HERE Cancel
                userconfim = false;
            }
        }).show();

        return userconfim;
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

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of LoginFragment");
        p = loadStatusofP();
        DisplayViews(p);
        //displayToast("Backbutton pressed",Toast.LENGTH_LONG);
        super.onResume();
    }


}
