package com.abhi.fabkutbusiness.crm.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class ResponseCrmHistory {
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

    public ArrayList<ResponseCrmHistoryData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseCrmHistoryData> data) {
        this.data = data;
    }

    private ArrayList<ResponseCrmHistoryData> data;
}
