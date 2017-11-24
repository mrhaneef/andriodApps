package com.usvb.mobile.andriod.mhaneef.myp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;

import com.usvb.mobile.andriod.mhaneef.myp.R;
import com.usvb.mobile.andriod.mhaneef.myp.data.Constants;
import com.usvb.mobile.andriod.mhaneef.myp.data.P;
import com.usvb.mobile.andriod.mhaneef.myp.data.Settings;
import com.usvb.mobile.andriod.mhaneef.myp.db.PDbHelper;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by user on 11/20/17.
 */

public class SettingsActivity extends AppCompatActivity {

    PDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //init
        mDbHelper = new PDbHelper(getBaseContext());
        HashMap<String, Settings> currentSettings = mDbHelper.getSettingsFromDB();
        Log.e("Info", currentSettings.size()+" <-- SIZE --> ");

        setSettingsFromDBandDisplay(currentSettings);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    public void setSettingsFromDBandDisplay(HashMap<String,Settings> settingList){
        Settings s = settingList.get(Constants.SettingsNames.CONFIRMINCREASE.toString());
        Switch confirmToIncrease = (Switch) findViewById(R.id.ConfirmToIncrease);
        Switch dayCompleteDisplay = (Switch) findViewById(R.id.dayCompleteDisplay);

        if(s==null){
            //First time calling
            s = new Settings();
            s.setSettingName(Constants.SettingsNames.CONFIRMINCREASE.toString());
            s.setSettingValue("true");
            int resultcount = mDbHelper.updateSettings(s);

            s = new Settings();
            s.setSettingName(Constants.SettingsNames.DAYCOMPLETEDISPLAY.toString());
            s.setSettingValue("true");
            resultcount = mDbHelper.updateSettings(s);

        }
        if(s!=null && s.getSettingValue().equalsIgnoreCase("true")){
            confirmToIncrease.setChecked(true);
        }else
        {
            confirmToIncrease.setChecked(false);

        }

        //Daycomplete toast display
        Settings toast = settingList.get(Constants.SettingsNames.DAYCOMPLETEDISPLAY.toString());

        if(toast==null){
            //First time calling
            toast = new Settings();
            toast.setSettingName(Constants.SettingsNames.DAYCOMPLETEDISPLAY.toString());
            toast.setSettingValue("true");
            int resultcount = mDbHelper.updateSettings(toast);
        }
        if(toast!=null && toast.getSettingValue().equalsIgnoreCase("true")){
            dayCompleteDisplay.setChecked(true);
        }else
        {
            dayCompleteDisplay.setChecked(false);
        }


    }


    public void onClickConfirmIncrease(View v){
        Switch confirmToIncrease = (Switch) findViewById(R.id.ConfirmToIncrease);
        String confirm = "false";
        if(confirmToIncrease.isChecked()){
            confirm = "true";
        }

        Settings s = new Settings();
        s.setSettingName(Constants.SettingsNames.CONFIRMINCREASE.toString());
        s.setSettingValue(confirm);

        int resultcount = mDbHelper.updateSettings(s);
    }

    public void onClickDisplayToadDayComplete (View v){
        Switch dayComplete = (Switch) findViewById(R.id.dayCompleteDisplay);
        String confirm = "false";
        if(dayComplete.isChecked()){
            confirm = "true";
        }

        Settings s = new Settings();
        s.setSettingName(Constants.SettingsNames.DAYCOMPLETEDISPLAY.toString());
        s.setSettingValue(confirm);

        int resultcount = mDbHelper.updateSettings(s);
    }


    public void onClickResetAllValues(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Reset All values. This cannot be undone!").
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final P p = new P(0,0 ,0,0,0);
                        mDbHelper.saveStatusOfP(p);
                        //p.increaseByColumnName(pNames);
                        //saveStatusofP(p, Constants.PNames.A);
                        //UpdateDisplayViews(p);
                    }
                }).
                setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //userconfim = false;
                    }
                }).
                show();
    }


    public void onClickCloseSettings(View view){
        this.finish();
    }


    @Override
    protected void onDestroy() {
        //mDbHelper.close();
        super.onDestroy();
    }


}
