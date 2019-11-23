package com.abhi.fabkutbusiness.inventory.order.model;

import java.util.ArrayList;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseOrderRecieve {

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




    private ArrayList<ResponseOrderRecieveData> data;


    public ArrayList<ResponseOrderRecieveData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseOrderRecieveData> data) {
        this.data = data;
    }
}
