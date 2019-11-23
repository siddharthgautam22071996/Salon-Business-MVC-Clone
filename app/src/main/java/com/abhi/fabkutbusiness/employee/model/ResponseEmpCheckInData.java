package com.abhi.fabkutbusiness.employee.model;

import com.abhi.fabkutbusiness.retrofit.RetrofitClient;

/**
 * Created by siddharthgautam on 26/04/18.
 */

public class ResponseEmpCheckInData {
    private String emp_name;
    private String emp_contact_No;
    private String emp_id;
    private String emp_profile_image;

    public String getEmp_profile_image() {
        return RetrofitClient.BASE_Image_URL_EMP+""+emp_profile_image;
    }

    public void setEmp_profile_image(String emp_profile_image) {
        this.emp_profile_image = RetrofitClient.BASE_Image_URL_EMP+""+emp_profile_image;
    }

    private String attid = "0";

    public String getAttid() {
        return attid;
    }

    public void setAttid(String attid) {
        this.attid = attid;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_contact_No() {
        return emp_contact_No;
    }

    public void setEmp_contact_No(String emp_contact_No) {
        this.emp_contact_No = emp_contact_No;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }
}
