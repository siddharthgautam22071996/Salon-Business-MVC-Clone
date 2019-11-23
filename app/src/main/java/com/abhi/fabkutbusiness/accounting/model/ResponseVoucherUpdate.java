package com.abhi.fabkutbusiness.accounting.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 11/26/2017.
 */

public class ResponseVoucherUpdate {

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




    private ArrayList<ResponseVoucherUpdateData> data;

    public ArrayList<ResponseVoucherUpdateData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseVoucherUpdateData> data) {
        this.data = data;
    }
}
