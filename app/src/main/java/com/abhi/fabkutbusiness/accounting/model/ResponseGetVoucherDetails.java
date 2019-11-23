package com.abhi.fabkutbusiness.accounting.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseGetVoucherDetails {

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

    public ArrayList<ResponseGetVoucherDetailsData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseGetVoucherDetailsData> data) {
        this.data = data;
    }

    private ArrayList<ResponseGetVoucherDetailsData> data;

}
