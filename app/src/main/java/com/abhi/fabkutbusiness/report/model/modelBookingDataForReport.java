package com.abhi.fabkutbusiness.report.model;


import com.abhi.fabkutbusiness.billing.model.ResponseModelBillPaymentData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by siddharthgautam on 23/07/18.
 */

public class modelBookingDataForReport {
   private  String employee;
   private String total_revenue;
   private String photo;
   private String date;

    public modelBookingDataForReport(String employee, String total_revenue, String photo ,String date) {
        this.employee = employee;
        this.total_revenue = total_revenue;
        this.photo = photo;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getTotal_revenue() {
        return total_revenue;
    }

    public void setTotal_revenue(String total_revenue) {
        this.total_revenue = total_revenue;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
