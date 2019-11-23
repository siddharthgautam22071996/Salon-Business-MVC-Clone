package com.abhi.fabkutbusiness.inventory.itemMaster.model;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseItemCategory {

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




    private ArrayList<ResponseItemCategoryData> data;

    public ArrayList<ResponseItemCategoryData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseItemCategoryData> data) {
        this.data = data;
    }
}
