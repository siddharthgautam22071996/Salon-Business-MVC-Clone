package com.abhi.fabkutbusiness.inventory.itemMaster.model;

import com.abhi.fabkutbusiness.inventory.vendor.model.ResponseCityData;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseItemBrand {

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




    private ArrayList<ResponseItemBrandData> data;

    public ArrayList<ResponseItemBrandData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseItemBrandData> data) {
        this.data = data;
    }
}
