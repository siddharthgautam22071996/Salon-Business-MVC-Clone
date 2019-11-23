package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public class ResponseAccountReportData {
    
    private  String AutoID;
    private  String ledgerBalance;
    private  String BookingAmount;
    private  String PoAmount;
    private  String AdvanceAmount;
    private  String VoucherAmount;
    private  String FinalAmount;
    private  String DrawAmount;
    private  String CloseAmount;
    private  String business_id;
    private  String addDate;

    public String getAutoID() {
        return AutoID;
    }

    public void setAutoID(String autoID) {
        AutoID = autoID;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public String getBookingAmount() {
        return BookingAmount;
    }

    public void setBookingAmount(String bookingAmount) {
        BookingAmount = bookingAmount;
    }

    public String getPoAmount() {
        return PoAmount;
    }

    public void setPoAmount(String poAmount) {
        PoAmount = poAmount;
    }

    public String getAdvanceAmount() {
        return AdvanceAmount;
    }

    public void setAdvanceAmount(String advanceAmount) {
        AdvanceAmount = advanceAmount;
    }

    public String getVoucherAmount() {
        return VoucherAmount;
    }

    public void setVoucherAmount(String voucherAmount) {
        VoucherAmount = voucherAmount;
    }

    public String getFinalAmount() {
        return FinalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        FinalAmount = finalAmount;
    }

    public String getDrawAmount() {
        return DrawAmount;
    }

    public void setDrawAmount(String drawAmount) {
        DrawAmount = drawAmount;
    }

    public String getCloseAmount() {
        return CloseAmount;
    }

    public void setCloseAmount(String closeAmount) {
        CloseAmount = closeAmount;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
}
