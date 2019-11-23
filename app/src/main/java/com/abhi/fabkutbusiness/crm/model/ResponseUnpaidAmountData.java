package com.abhi.fabkutbusiness.crm.model;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public  class ResponseUnpaidAmountData {
    private String Enduser_id;
    private String Enduser_name;
    private String BalanceAmount;
    private String totalReveniue;

    public String getTotalReveniue() {
        return totalReveniue;
    }

    public void setTotalReveniue(String totalReveniue) {
        this.totalReveniue = totalReveniue;
    }

    public String getTotalVisit() {
        return TotalVisit;
    }

    public void setTotalVisit(String totalVisit) {
        TotalVisit = totalVisit;
    }

    private String TotalVisit;


    public String getEnduser_id() {
        return Enduser_id;
    }

    public void setEnduser_id(String enduser_id) {
        Enduser_id = enduser_id;
    }

    public String getEnduser_name() {
        return Enduser_name;
    }

    public void setEnduser_name(String enduser_name) {
        Enduser_name = enduser_name;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }
}
