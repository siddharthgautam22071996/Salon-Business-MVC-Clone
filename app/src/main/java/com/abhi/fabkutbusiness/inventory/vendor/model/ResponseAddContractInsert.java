package com.abhi.fabkutbusiness.inventory.vendor.model;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseAddContractInsert {

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


    public ArrayList<ResponseAddContractInsertData>  data;

    public ArrayList<ResponseAddContractInsertData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseAddContractInsertData> data) {
        this.data = data;
    }
}
