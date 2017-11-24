package com.usvb.mobile.andriod.mhaneef.myp;

import com.usvb.mobile.andriod.mhaneef.myp.data.P;

import org.junit.Test;

/**
 * Created by mhaneef on 10/20/2017.
 */
public class MainActivityTest {
    @Test
    public void calculateYears() throws Exception {
        P p = new P(5,10,6,11,12);
        System.out.println(p.getInYears());
    }

}