package com.example.mhaneef.myp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;

import com.example.mhaneef.myp.R;
import com.example.mhaneef.myp.db.PDbHelper;

/**
 * Created by user on 11/20/17.
 */

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //init
        //mDbHelper = new PDbHelper(getBaseContext());
        //displayHistory();
        //p = loadStatusofP();
        //DisplayViews(p);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
