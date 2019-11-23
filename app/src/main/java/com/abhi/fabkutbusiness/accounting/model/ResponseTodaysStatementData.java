package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 10/28/2017.
 */

public class ResponseTodaysStatementData {
            int RegisterAmount;
            int ledger_balance ;
            int Booking_Amount ;
            int AdvancePay ;
            int VoucherAmount ;
            int CloseAmount ;
            int POAmount;

    public int getRegisterAmount() {
        return RegisterAmount;
    }

    public void setRegisterAmount(int registerAmount) {
        RegisterAmount = registerAmount;
    }

    public int getLedger_balance() {
        return ledger_balance;
    }

    public void setLedger_balance(int ledger_balance) {
        this.ledger_balance = ledger_balance;
    }

    public int getBooking_Amount() {
        return Booking_Amount;
    }

    public void setBooking_Amount(int booking_Amount) {
        Booking_Amount = booking_Amount;
    }

    public int getAdvancePay() {
        return AdvancePay;
    }

    public void setAdvancePay(int advancePay) {
        AdvancePay = advancePay;
    }

    public int getVoucherAmount() {
        return VoucherAmount;
    }

    public void setVoucherAmount(int voucherAmount) {
        VoucherAmount = voucherAmount;
    }

    public int getCloseAmount() {
        return CloseAmount;
    }

    public void setCloseAmount(int closeAmount) {
        CloseAmount = closeAmount;
    }

    public int getPOAmount() {
        return POAmount;
    }

    public void setPOAmount(int POAmount) {
        this.POAmount = POAmount;
    }
}
