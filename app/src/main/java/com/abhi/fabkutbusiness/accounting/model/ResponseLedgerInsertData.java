package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseLedgerInsertData {

       public String Status;
       public String Ledger_balance;
       public int  business_id ;
       public String ledger_date;
       public String remark ;

    public String getStatus() {

        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLedger_balance() {
        return Ledger_balance;
    }

    public void setLedger_balance(String ledger_balance) {
        Ledger_balance = ledger_balance;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getLedger_date() {
        return ledger_date;
    }

    public void setLedger_date(String ledger_date) {
        this.ledger_date = ledger_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
