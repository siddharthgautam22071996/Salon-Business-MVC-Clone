package com.abhi.fabkutbusiness.crm.model;

import com.abhi.fabkutbusiness.retrofit.RetrofitClient;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class ResponseCrmListData {
    String Enduser_id;
    String enduser_name;
    String Contact_no;
    String email;
    String TotalVisit;
    String profie_pic;
    int Profile_Comp_total;
    String Total_Revinew;
    private String endusercode;

    public String getEndusercode() {
        return endusercode;
    }

    public void setEndusercode(String endusercode) {
        this.endusercode = endusercode;
    }

    public String getTotal_Revinew() {
        return Total_Revinew;
    }

    public void setTotal_Revinew(String total_Revinew) {
        Total_Revinew = total_Revinew;
    }

    public int getProfile_Comp_total() {
        return Profile_Comp_total;
    }

    public void setProfile_Comp_total(int profile_Comp_total) {
        Profile_Comp_total = profile_Comp_total;
    }

    public ResponseCrmListData(String enduser_id, String enduser_name, String contact_no, String email, String totalVisit, String profie_pic, int profile_Comp_total) {
        Enduser_id = enduser_id;
        this.enduser_name = enduser_name;
        Contact_no = contact_no;
        this.email = email;
        TotalVisit = totalVisit;
        this.profie_pic = profie_pic;
        Profile_Comp_total = profile_Comp_total;
    }

    public String getEnduser_id() {
        return Enduser_id;
    }

    public void setEnduser_id(String enduser_id) {
        Enduser_id = enduser_id;
    }

    public String getEnduser_name() {
        return enduser_name;
    }

    public void setEnduser_name(String enduser_name) {
        this.enduser_name = enduser_name;
    }

    public String getContact_no() {
        return Contact_no;
    }

    public void setContact_no(String contact_no) {
        Contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotalVisit() {
        return TotalVisit;
    }

    public void setTotalVisit(String totalVisit) {
        TotalVisit = totalVisit;
    }

    public String getProfie_pic() {
        return RetrofitClient.BASE_Image_URL_CUSTOMER+""+profie_pic;
    }

    public void setProfie_pic(String profie_pic) {
        this.profie_pic = profie_pic;
    }
}
