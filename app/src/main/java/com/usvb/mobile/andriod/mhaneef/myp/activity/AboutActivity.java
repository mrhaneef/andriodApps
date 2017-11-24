package com.usvb.mobile.andriod.mhaneef.myp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.usvb.mobile.andriod.mhaneef.myp.R;
import com.usvb.mobile.andriod.mhaneef.myp.data.Constants;
import com.usvb.mobile.andriod.mhaneef.myp.data.P;
import com.usvb.mobile.andriod.mhaneef.myp.data.Settings;
import com.usvb.mobile.andriod.mhaneef.myp.db.PDbHelper;

import java.util.HashMap;


/**
 * Created by user on 11/20/17.
 */

public class AboutActivity extends AppCompatActivity {

    PDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //init
        //mDbHelper = new PDbHelper(getBaseContext());
        //HashMap<String, Settings> currentSettings = mDbHelper.getSettingsFromDB();
        //Log.e("Info", currentSettings.size()+" <-- SIZE --> ");

        //setSettingsFromDBandDisplay(currentSettings);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/



    public void onClickCloseSettings(View view){
        this.finish();
    }


    @Override
    protected void onDestroy() {
        //mDbHelper.close();
        super.onDestroy();
    }


}
