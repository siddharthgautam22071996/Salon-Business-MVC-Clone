package com.abhi.fabkutbusiness.employee.model;

/**
 * Created by siddharthgautam on 30/04/18.
 */

public class ResponseEmpReportData {

    private String LEAVE_ID;
    private String Name;
    private String FromDate;
    private String ToDate;
    private String LeaveType;
    private String LeaveTotal;
    private String Remark;

    public ResponseEmpReportData(String LEAVE_ID, String name, String fromDate, String toDate, String leaveType, String leaveTotal, String remark) {
        this.LEAVE_ID = LEAVE_ID;
        Name = name;
        FromDate = fromDate;
        ToDate = toDate;
        LeaveType = leaveType;
        LeaveTotal = leaveTotal;
        Remark = remark;
    }

    public String getLeaveTotal() {
        return LeaveTotal;
    }

    public void setLeaveTotal(String leaveTotal) {
        LeaveTotal = leaveTotal;
    }



    public String getLEAVE_ID() {
        return LEAVE_ID;
    }

    public void setLEAVE_ID(String LEAVE_ID) {
        this.LEAVE_ID = LEAVE_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(String leaveType) {
        LeaveType = leaveType;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

}
