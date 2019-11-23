package com.abhi.fabkutbusiness.billing.model;

import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by siddharthgautam on 20/01/19.
 */

public class ResponsePomoCode {
    private String MESSAGE;

    private String STATUS;

    private ArrayList<ResponsePomoCodeData> data;

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

    public ArrayList<ResponsePomoCodeData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponsePomoCodeData> data) {
        this.data = data;
    }
}
