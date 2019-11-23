package com.abhi.fabkutbusiness.crm.model;

import com.abhi.fabkutbusiness.retrofit.RetrofitApi;
import com.abhi.fabkutbusiness.retrofit.RetrofitClient;

/**
 * Created by siddharth on 1/10/2018.
 */

public  class ResponseCrmHistoryData {
    private String    Booking_id;
    private String    Bookingdate;
    private String    service_name;
    private String    eta;
    private String    rate;
    private String     Paid_Unpiad;
    private String     Unpiad_Amount;
    private String     Amount;
    private String     emp_name;
    private String emp_profile_image;

    public String getEmp_profile_image() {
        return emp_profile_image;
    }

    public void setEmp_profile_image(String emp_profile_image) {
        this.emp_profile_image = emp_profile_image;
    }

    public String getBooking_id() {
        return Booking_id;
    }

    public void setBooking_id(String booking_id) {
        Booking_id = booking_id;
    }

    public String getBookingdate() {
        return Bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        Bookingdate = bookingdate;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPaid_Unpiad() {
        return Paid_Unpiad;
    }

    public void setPaid_Unpiad(String paid_Unpiad) {
        Paid_Unpiad = paid_Unpiad;
    }

    public String getUnpiad_Amount() {
        return Unpiad_Amount;
    }

    public void setUnpiad_Amount(String unpiad_Amount) {
        Unpiad_Amount = unpiad_Amount;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
}
