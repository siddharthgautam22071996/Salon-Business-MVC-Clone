package com.abhi.fabkutbusiness.inventory.itemMaster.model;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 03/04/18.
 */

public class ResponseMyStock {

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
    private ArrayList<ResponseMyStockData> data;


}
