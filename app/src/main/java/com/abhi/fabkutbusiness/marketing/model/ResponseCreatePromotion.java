package com.abhi.fabkutbusiness.marketing.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 17/12/18.
 */

public class ResponseCreatePromotion {



    private String MESSAGE;


    protected ResponseCreatePromotion(Parcel in) {
        MESSAGE = in.readString();
        STATUS = in.readString();
    }


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

    private ArrayList<ResponseCreatePromotionData> data;

    public ArrayList<ResponseCreatePromotionData> getData() {
        return data;
    }

    public void setData(ArrayList<ResponseCreatePromotionData> data) {
        this.data = data;
    }


}
