package com.usvb.mobile.andriod.mhaneef.myp.data;

import java.sql.Timestamp;

/**
 * Created by mhaneef on 10/16/2017.
 */

public class H extends P {
    public   String columnChangedName;
    public   Timestamp  modifiedTime;

    public H()
    {}


    public H(int f, int z, int a, int m, int i){
        this.f=f;this.z=z;this.a=a;this.m=m;this.i=i;
    }

    public void reset(int f,int z,int a,int m,int i){
        f=f;z=z;a=a;m=m;i=i;
    }
    public int getF() {
        return f;
    }



    public void setF(int f) {
        this.f = f;
    }


    public String getColumnChangedName() {
        return columnChangedName;
    }

    public void setColumnChangedName(String columnChangedName) {
        this.columnChangedName = columnChangedName;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

}
