package com.abhi.fabkutbusiness.employee.model;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseEmpReport {

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




    private ArrayList<ResponseEmpReportData> data;

    public ArrayList<ResponseEmpReportData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseEmpReportData> data) {
        this.data = data;
    }
}
