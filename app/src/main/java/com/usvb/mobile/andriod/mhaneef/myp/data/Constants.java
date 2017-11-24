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
    public static HashMap<PNames,String> sawlahDef = new HashMap<>();
    static {
        defaultSettings.put(SettingsNames.CONFIRMINCREASE.toString(),"true");
        defaultSettings.put(SettingsNames.DAYCOMPLETEDISPLAY.toString(),"ture");
        sawlahDef.put(PNames.F,"Fajr");
        sawlahDef.put(PNames.Z,"Zuhr");
        sawlahDef.put(PNames.A,"Asr");
        sawlahDef.put(PNames.M,"Marib");
        sawlahDef.put(PNames.I,"Isha");
        sawlahDef.put(PNames.W,"Witr");
    }
}
