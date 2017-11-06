package com.example.mhaneef.myp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mhaneef.myp.data.H;
import com.example.mhaneef.myp.data.P;
import com.example.mhaneef.myp.db.PDbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

public class HistoryActivity extends AppCompatActivity {

    protected H h;
    PDbHelper mDbHelper;
    TableLayout tl;

    TableRow tr;

    TextView f,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //init
        mDbHelper = new PDbHelper(getBaseContext());
        displayHistory();
        //p = loadStatusofP();
        //DisplayViews(p);
    }

    public void displayHistory(){
        tl = (TableLayout) findViewById(R.id.tableLayout);
        addHeaders();
        addData();
    }

    private void addData() {
        ArrayList<H> history = mDbHelper.getHistoryFromDB();
        int count =0;
        for (H h : history){
            count++;
            tr = new TableRow(this);
            if(count%2 == 0){
                tr.setBackgroundColor(Color.rgb(207,228,242));
            }else
            {
                tr.setBackgroundColor(Color.rgb(213,218,220));
            }

            TableLayout.LayoutParams lo = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            lo.height=1;
            tr.setLayoutParams(lo);

            TextView dc = new TextView(this);
            dc.setText(h.getInDays()+"");
            dc.setPadding(5, 5, 5, 5);
            tr.addView(dc);  // Adding textView to tablerow.

            f = new TextView(this);
            f.setText(h.getTimeAfterSubstractionsPlan(h.getF()));
            if(h.getColumnChangedName().equalsIgnoreCase( "F"))
                f.setBackgroundColor(Color.YELLOW);
            f.setPadding(5, 5, 5, 5);
            tr.addView(f);  // Adding textView to tablerow.

            z = new TextView(this);
            z.setText(h.getTimeAfterSubstractionsPlan(h.getZ()));
            if(h.getColumnChangedName().equalsIgnoreCase( "Z"))
                z.setBackgroundColor(Color.YELLOW);
            z.setPadding(5, 5, 5, 5);
            tr.addView(z);  // Adding textView to tablerow.

            TextView a = new TextView(this);
            a.setText(h.getTimeAfterSubstractionsPlan(h.getA()));
            if(h.getColumnChangedName().equalsIgnoreCase( "A"))
                a.setBackgroundColor(Color.YELLOW);
            a.setPadding(5, 5, 5, 5);
            tr.addView(a);  // Adding textView to tablerow.

            TextView m = new TextView(this);
            m.setText(h.getTimeAfterSubstractionsPlan(h.getM()));
            if(h.getColumnChangedName() .equalsIgnoreCase( "M"))
                m.setBackgroundColor(Color.YELLOW);
            m.setPadding(5, 5, 5, 5);
            tr.addView(m);  // Adding textView to tablerow.

            TextView i = new TextView(this);
            i.setText(h.getTimeAfterSubstractionsPlan(h.getI()) );
            if(h.getColumnChangedName().equalsIgnoreCase(  "I"))
                i.setBackgroundColor(Color.YELLOW);
            i.setPadding(5, 5, 5, 5);
            tr.addView(i);  // Adding textView to tablerow.

            TextView t = new TextView(this);
            SimpleDateFormat formatter = new SimpleDateFormat("yy-mm-dd hh:mm aa",Locale.getDefault());
            formatter.setTimeZone(TimeZone.getDefault());
            String dateString = formatter.format(h.getModifiedTime());

            t.setText(dateString);
            t.setPadding(5, 5, 5, 5);
            tr.addView(t);  // Adding textView to tablerow.

            Button b = new Button(this);
            b.setText("Restore");
            b.setId(h.ID.intValue());
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        onClickRestoreFromHistory(v);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

            });

            b.setPadding(5, 5, 5, 5);

            tr.addView(b);  // Adding textView to tablerow.

            Log.i("INFO","ID " + h.ID + " sl "+ h.getModifiedTime() + " time " + h.getColumnChangedName());

            tl.addView(tr, new TableLayout.LayoutParams(

                    TableLayout.LayoutParams.FILL_PARENT,

                    TableLayout.LayoutParams.WRAP_CONTENT));


        }


    }

    private void addHeaders()
    {
        /** Create a TableRow dynamically **/

        tr = new TableRow(this);
        int pad = 10;
        tr.setLayoutParams(new TableLayout.LayoutParams(

                TableLayout.LayoutParams.FILL_PARENT,

                TableLayout.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/

        TextView dc = new TextView(this); dc.setText("DC");
        dc.setPadding(pad, 0, 0, 0);
        tr.addView(dc);  // Adding textView to tablerow.

        TextView f = new TextView(this); f.setText("F");
        f.setPadding(pad, 0, 0, 0);
        tr.addView(f);  // Adding textView to tablerow.

        TextView z = new TextView(this); z.setText("Z");
        z.setPadding(pad, 0, 0, 0);
        tr.addView(z); // Adding textView to tablerow.

        TextView a = new TextView(this); a.setText("A");
        a.setPadding(pad, 0, 0, 0);
        tr.addView(a); // Adding textView to tablerow.

        TextView m = new TextView(this); m.setText("M");
        m.setPadding(pad, 0, 0, 0);
        tr.addView(m); // Adding textView to tablerow.

        TextView i = new TextView(this); i.setText("I");
        i.setPadding(pad, 0, 0, 0);
        tr.addView(i); // Adding textView to tablerow.

        TextView tm = new TextView(this); tm.setText("Time");
        tm.setPadding(pad, 0, 0, 0);
        tr.addView(tm); // Adding textView to tablerow.
        // Add the TableRow to the TableLayout

        TextView r = new TextView(this); r.setText("Restore");
        r.setPadding(pad, 0, 0, 0);
        tr.addView(r); // Adding textView to tablerow.

        tl.addView(tr, new TableLayout.LayoutParams(

                TableLayout.LayoutParams.FILL_PARENT,

                TableLayout.LayoutParams.WRAP_CONTENT));


    }
    /*
    public P loadStatusofP(){
        return mDbHelper.getPFromDB();
    }

    public void saveStatusofP(P p, String colName){
        //save to DB;
        int db = mDbHelper.saveStatusOfP(p);
        mDbHelper.saveHistory(p, colName);
        mDbHelper.getHistoryFromDB();
        //DisplayViews(p);
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    protected void onDestroy() {
        //mDbHelper.close();
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
                setContentView(R.layout.activity_history);

                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onClickCloseHistory(View view){
        this.finish();
    }


    public void onClickRestoreFromHistory(final View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure, you want to restore from history ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int id = view.getId();
                H h = mDbHelper.getHistoryFromDBByID(id);
                if(h != null){
                    P p = new P(h.getF(),h.getZ(),h.getA(),h.getM(),h.getI());
                    mDbHelper.saveStatusOfP(p);
                    mDbHelper.saveHistory(p, h.getColumnChangedName());
                }
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                        //saveStatusofP(p,columName);
            }
        }).show();
    }


    /*DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked

                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };*/


}
