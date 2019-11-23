package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 10/25/2017.
 */

public class ResponseVoucherInsertData {
            private String Status ;
            private String Business_id;
            private String money ;
            private String emp_id ;
            private String mobile ;
            private String remark ;
            private String date ;
            private String IsAdvance ;
            private String closeAccount ;
            private String VoucherNo ;
            private String assignTo ;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBusiness_id() {
        return Business_id;
    }

    public void setBusiness_id(String business_id) {
        Business_id = business_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsAdvance() {
        return IsAdvance;
    }

    public void setIsAdvance(String isAdvance) {
        IsAdvance = isAdvance;
    }

    public String getCloseAccount() {
        return closeAccount;
    }

    public void setCloseAccount(String closeAccount) {
        this.closeAccount = closeAccount;
    }

    public String getVoucherNo() {
        return VoucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        VoucherNo = voucherNo;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }
}
