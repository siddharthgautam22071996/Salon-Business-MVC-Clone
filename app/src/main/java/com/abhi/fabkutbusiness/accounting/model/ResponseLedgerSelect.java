package com.abhi.fabkutbusiness.accounting.model;

import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoData;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseLedgerSelect {

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


    public ArrayList<ResponseLedgerSelectData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseLedgerSelectData> data) {
        this.data = data;
    }

    private ArrayList<ResponseLedgerSelectData> data;

}
