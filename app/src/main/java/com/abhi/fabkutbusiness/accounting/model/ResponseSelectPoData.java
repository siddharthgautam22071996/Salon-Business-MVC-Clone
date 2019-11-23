package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 10/24/2017.
 */

public class ResponseSelectPoData {
    public String PONO ;
    public int order_id ;
    public int business_id ;
    public String POAmount ;
    public String balanceAmt ;

    public String getPONO() {
        return PONO;
    }

    public void setPONO(String PONO) {
        this.PONO = PONO;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getPOAmount() {
        return POAmount;
    }

    public void setPOAmount(String POAmount) {
        this.POAmount = POAmount;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        this.balanceAmt = balanceAmt;
    }
}
