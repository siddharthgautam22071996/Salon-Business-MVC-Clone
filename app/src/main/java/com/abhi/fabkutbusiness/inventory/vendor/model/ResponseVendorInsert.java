package com.abhi.fabkutbusiness.inventory.vendor.model;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseVendorInsert {

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


    public ArrayList<ResponseVendorInsertData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseVendorInsertData> data) {
        this.data = data;
    }

    private ArrayList<ResponseVendorInsertData> data;

}
