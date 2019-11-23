package com.abhi.fabkutbusiness.inventory.vendor.model;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseCity {

    private String MESSAGE;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    private String STATUS;




    private ArrayList<ResponseCityData> data;

    public ArrayList<ResponseCityData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseCityData> data) {
        this.data = data;
    }
}
