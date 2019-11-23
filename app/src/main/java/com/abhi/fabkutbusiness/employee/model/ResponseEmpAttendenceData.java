package com.abhi.fabkutbusiness.employee.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by siddharthgautam on 25/04/18.
 */

public class ResponseEmpAttendenceData  {

    private String  business_id ;
    private String EmpID;
    private String InTime  ;
    private String OutTime ;
    private String AttDate ;
    private  String Remark;
    private  String name;
    private String contact;

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
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

    public String getAttDate() {
        return AttDate;
    }

    public void setAttDate(String attDate) {
        AttDate = attDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
