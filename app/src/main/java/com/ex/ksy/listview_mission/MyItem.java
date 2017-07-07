package com.ex.ksy.listview_mission;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kor on 2017-06-29.
 */

public class MyItem {
    private int iNum;
    private String strName;
    private int iPrice;
    private String strDate;

    public MyItem(int iNum, String strName, int iPrice) {
        this.iNum = iNum;
        this.strName = strName;
        this.iPrice = iPrice;
        this.strDate = getDate();
    }

    public int getiNum() {
        return iNum;
    }

    public String getStrName() {
        return strName;
    }

    public int getiPrice() {
        return iPrice;
    }

    public String getStrDate() {
        return strDate;
    }

    public String getDate(){
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SSS");
        return sdf.format(new Date(time));
    }
}
