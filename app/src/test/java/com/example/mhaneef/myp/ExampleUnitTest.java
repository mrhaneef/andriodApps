package com.example.mhaneef.myp;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void DateFormatTest(){
        String d = "2017-10-31 14:53:26";
        //Timestamp t = new Timestamp(d);
        //Date currentTime_1 = new Date(d);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //String dateString = formatter.format(currentTime_1);
    }
}