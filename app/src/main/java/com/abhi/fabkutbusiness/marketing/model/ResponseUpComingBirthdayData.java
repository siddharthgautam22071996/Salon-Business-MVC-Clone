package com.abhi.fabkutbusiness.marketing.model;

/**
 * Created by siddharthgautam on 16/10/18.
 */

public class ResponseUpComingBirthdayData {
    private String enduser_NAME;
    private String   lname;
    private String   date;
    private String   profie_pic;
    private String   enduser_id;
    private String   endusercode;

    public String getEnduser_id() {
        return enduser_id;
    }

    public void setEnduser_id(String enduser_id) {
        this.enduser_id = enduser_id;
    }

    public String getEndusercode() {
        return endusercode;
    }

    public void setEndusercode(String endusercode) {
        this.endusercode = endusercode;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    private String   contact_no;

    public String getEnduser_NAME() {
        return enduser_NAME;
    }

    public void setEnduser_NAME(String enduser_NAME) {
        this.enduser_NAME = enduser_NAME;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfie_pic() {
        return profie_pic;
    }

    public void setProfie_pic(String profie_pic) {
        this.profie_pic = profie_pic;
    }
}
