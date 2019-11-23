package com.abhi.fabkutbusiness.inventory.order.model;

import com.abhi.fabkutbusiness.inventory.itemMaster.model.ResponseItemColorData;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseVendor {

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




    private ArrayList<ResponseVendorData> data;

    public ArrayList<ResponseVendorData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseVendorData> data) {
        this.data = data;
    }
}
