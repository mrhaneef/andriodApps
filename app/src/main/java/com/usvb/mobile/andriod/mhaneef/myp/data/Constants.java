package com.usvb.mobile.andriod.mhaneef.myp.data;

import java.util.HashMap;

/**
 * Created by user on 11/20/17.
 */

public class Constants {
    public enum PNames
    {
        F, Z, A,M,I,W;
    }

    public enum SettingsNames
    {
        CONFIRMINCREASE,DAYCOMPLETEDISPLAY;
    }

    public static HashMap<String,String> defaultSettings = new HashMap();
    static {
        defaultSettings.put(SettingsNames.CONFIRMINCREASE.toString(),"true");
    }
}
