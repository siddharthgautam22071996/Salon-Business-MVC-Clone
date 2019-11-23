package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 10/23/2017.
 */

public class ResponseLedgerSelectData {

      public String ledger_balance;
      public int  business_id;
      public String ledger_date ;

    public String getRegisterAmount() {
        return RegisterAmount;
    }

    public void setRegisterAmount(String registerAmount) {
        RegisterAmount = registerAmount;
    }

    public String RegisterAmount;

    public String getLedger_balance() {
        return ledger_balance;
    }

    public void setLedger_balance(String ledger_balance) {
        this.ledger_balance = ledger_balance;
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
}
