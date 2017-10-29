package com.example.mhaneef.myp.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Created by mhaneef on 10/16/2017.
 */

public class P {
    public Long ID;
    public int f;
    public int z;
    public int a;
    public int m;
    public int i;

    public P()
    {}


    public P (int f, int z, int a, int m, int i){
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }


    public String getInYears(){
        String years = "";
        BigDecimal prod = new BigDecimal(getAllValuesArray()[0]);
        BigDecimal y = (prod.divide(new BigDecimal(365),3,RoundingMode.HALF_UP));
        return years = y + "";
    }

    public String getInMonth(){
        String month = "";
        BigDecimal prod = new BigDecimal(getAllValuesArray()[0]);
        BigDecimal m = (prod.divide(new BigDecimal(30),3,RoundingMode.HALF_UP));
        return month = m + "";
    }

    public String getInDays(){
        String day = "";
        BigDecimal prod = new BigDecimal(getAllValuesArray()[0]);
        return day = prod + "";
    }

    private int[] getAllValuesArray(){
        int array[]={getF(),getZ(),getA(),getM(),getI()};
        Arrays.sort(array);
        return array;
    }
}
