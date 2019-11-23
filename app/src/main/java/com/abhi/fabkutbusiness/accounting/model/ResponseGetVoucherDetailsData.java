package com.abhi.fabkutbusiness.accounting.model;

/**
 * Created by SIDDHARTH on 11/8/2017.
 */

public class ResponseGetVoucherDetailsData {
        String expense_pk_id ;
        String Amount ;
        String remark ;
        String emp_name ;
        String VoucherNo ;
        String assignTo ;

    public String getExpense_pk_id() {
        return expense_pk_id;
    }

    public void setExpense_pk_id(String expense_pk_id) {
        this.expense_pk_id = expense_pk_id;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
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
