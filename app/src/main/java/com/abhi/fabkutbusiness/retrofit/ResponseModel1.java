package com.abhi.fabkutbusiness.retrofit;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public class ResponseModel1 {
    private String MESSAGE;
    private String STATUS;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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


}
