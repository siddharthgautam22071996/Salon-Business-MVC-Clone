package com.abhi.fabkutbusiness.employee.model;

import com.abhi.fabkutbusiness.accounting.model.ResponseGenerateVoucherNoData;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseEmpAttendence {

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




    private ArrayList<ResponseEmpAttendenceData> data;

    public ArrayList<ResponseEmpAttendenceData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseEmpAttendenceData> data) {
        this.data = data;
    }
}
