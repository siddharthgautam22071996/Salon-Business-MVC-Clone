package com.abhi.fabkutbusiness.inventory.vendor.model;

import com.abhi.fabkutbusiness.accounting.model.ResponseGenerateVoucherNoData;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseLocation {

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




    private ArrayList<ResponseLocationData> data;

    public ArrayList<ResponseLocationData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseLocationData> data) {
        this.data = data;
    }
}
