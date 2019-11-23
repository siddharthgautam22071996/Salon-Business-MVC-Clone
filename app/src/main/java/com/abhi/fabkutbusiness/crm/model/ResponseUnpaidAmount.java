package com.abhi.fabkutbusiness.crm.model;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public class ResponseUnpaidAmount {

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

    private ArrayList<ResponseUnpaidAmountData> data;

    public ArrayList<ResponseUnpaidAmountData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseUnpaidAmountData> data) {
        this.data = data;
    }
}
