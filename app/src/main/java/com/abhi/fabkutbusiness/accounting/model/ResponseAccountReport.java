package com.abhi.fabkutbusiness.accounting.model;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public class ResponseAccountReport {
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


    public ArrayList<ResponseAccountReportData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseAccountReportData> data) {
        this.data = data;
    }

    private ArrayList<ResponseAccountReportData> data;


}
