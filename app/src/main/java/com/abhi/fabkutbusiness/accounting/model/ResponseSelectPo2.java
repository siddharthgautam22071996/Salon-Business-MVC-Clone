package com.abhi.fabkutbusiness.accounting.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseSelectPo2 {

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




    private ArrayList<ResponseSelectPo2Data> data;

    public ArrayList<ResponseSelectPo2Data> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseSelectPo2Data> data) {
        this.data = data;
    }
}
