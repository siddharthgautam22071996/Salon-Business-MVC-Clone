package com.abhi.fabkutbusiness.accounting.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseTodayInsert {

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




    private ArrayList<ResponseTodayInsertData> data;

    public ArrayList<ResponseTodayInsertData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseTodayInsertData> data) {
        this.data = data;
    }
}

