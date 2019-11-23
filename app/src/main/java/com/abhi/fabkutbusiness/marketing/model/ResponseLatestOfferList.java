package com.abhi.fabkutbusiness.marketing.model;

import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoData;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 16/10/18.
 */

public class ResponseLatestOfferList {
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

    public ArrayList<ResponseLatestOfferListData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseLatestOfferListData> data) {
        this.data = data;
    }

    private ArrayList<ResponseLatestOfferListData> data;
}
