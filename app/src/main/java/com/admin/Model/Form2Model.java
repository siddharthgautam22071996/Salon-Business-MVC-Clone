package com.admin.Model;

import java.util.List;

public class Form2Model {
    /**
     * MESSAGE : SUCCESS
     * STATUS : 200
     * data : [{"Status":"Success","Business_id":"1","business_Name":"Ashish's Spa","No_of_chairs":"5","online":"0","offline":"2","servicetax":"0","opening_hours":"00:00:00.0000000","closing_hours":"00:00:00.0000000","tagline":"We do best work","no_of_emp":"4","latitude":"0","longitute":"0"}]
     */

    private String MESSAGE;
    private String STATUS;
    private List<DataBean> data;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Status : Success
         * Business_id : 1
         * business_Name : Ashish's Spa
         * No_of_chairs : 5
         * online : 0
         * offline : 2
         * servicetax : 0
         * opening_hours : 00:00:00.0000000
         * closing_hours : 00:00:00.0000000
         * tagline : We do best work
         * no_of_emp : 4
         * latitude : 0
         * longitute : 0
         */

        private String Status;
        private String Business_id;
        private String business_Name;
        private String No_of_chairs;
        private String online;
        private String offline;
        private String servicetax;
        private String opening_hours;
        private String closing_hours;
        private String tagline;
        private String no_of_emp;
        private String latitude;
        private String longitute;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getBusiness_id() {
            return Business_id;
        }

        public void setBusiness_id(String Business_id) {
            this.Business_id = Business_id;
        }

        public String getBusiness_Name() {
            return business_Name;
        }

        public void setBusiness_Name(String business_Name) {
            this.business_Name = business_Name;
        }

        public String getNo_of_chairs() {
            return No_of_chairs;
        }

        public void setNo_of_chairs(String No_of_chairs) {
            this.No_of_chairs = No_of_chairs;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }

        public String getOffline() {
            return offline;
        }

        public void setOffline(String offline) {
            this.offline = offline;
        }

        public String getServicetax() {
            return servicetax;
        }

        public void setServicetax(String servicetax) {
            this.servicetax = servicetax;
        }

        public String getOpening_hours() {
            return opening_hours;
        }

        public void setOpening_hours(String opening_hours) {
            this.opening_hours = opening_hours;
        }

        public String getClosing_hours() {
            return closing_hours;
        }

        public void setClosing_hours(String closing_hours) {
            this.closing_hours = closing_hours;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getNo_of_emp() {
            return no_of_emp;
        }

        public void setNo_of_emp(String no_of_emp) {
            this.no_of_emp = no_of_emp;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitute() {
            return longitute;
        }

        public void setLongitute(String longitute) {
            this.longitute = longitute;
        }
    }
}
