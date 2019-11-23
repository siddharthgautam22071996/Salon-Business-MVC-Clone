package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 10/30/2017.
 */

public class ResponsePoInsertData {
    String Status ;
    String AutoID ;
           int business_id ;
          int  PONO ;
    String  TotalAmount ;
    String PaidAmount ;
    String status ;
            String AmountBalance ;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAmountBalance() {
        return AmountBalance;
    }

    public void setAmountBalance(String amountBalance) {
        AmountBalance = amountBalance;
    }

    public String getAutoID() {
        return AutoID;
    }

    public void setAutoID(String autoID) {
        AutoID = autoID;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public int getPONO() {
        return PONO;
    }

    public void setPONO(int PONO) {
        this.PONO = PONO;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }
}
