package com.abhi.fabkutbusiness.accounting.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseLedgerInsert {

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




    private ArrayList<ResponseLedgerInsertData> data;

    public ArrayList<ResponseLedgerInsertData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseLedgerInsertData> data) {
        this.data = data;
    }
}
