package com.abhi.fabkutbusiness.accounting.model;

import android.widget.EditText;

/**
 * Created by SIDDHARTH on 10/18/2017.
 */

public class AdvancePayModel {
    String name = "siddhath";
    String advance = "200";
    String empId;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String date = "12/12/2017";

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public AdvancePayModel(String name, String advance, String empId, String id, String date) {
        this.name = name;
        this.advance = advance;
        this.empId = empId;
        this.id = id;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
