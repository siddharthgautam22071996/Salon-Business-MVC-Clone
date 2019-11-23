package com.abhi.fabkutbusiness.employee.model;

/**
 * Created by siddharthgautam on 26/04/18.
 */

public class ResponseModelEmpSummary {
    private String empid;
    private String AttID;
    private String emp_name;
    private String AttDate;
    private String InTime;
    private String OutTime;
    private String Remark;
    private String total_service;
    private String todayRevenue;

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getAttID() {
        return AttID;
    }

    public void setAttID(String attID) {
        AttID = attID;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getAttDate() {
        return AttDate;
    }

    public void setAttDate(String attDate) {
        AttDate = attDate;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getTotal_service() {
        return total_service;
    }

    public void setTotal_service(String total_service) {
        this.total_service = total_service;
    }

    public String getTodayRevenue() {
        return todayRevenue;
    }

    public void setTodayRevenue(String todayRevenue) {
        this.todayRevenue = todayRevenue;
    }
}
