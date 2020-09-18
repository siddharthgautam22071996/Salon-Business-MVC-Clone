package com.admin.Model;

import java.util.List;

public class Form1Model {

    /**
     * MESSAGE : SUCCESS
     * STATUS : 200
     * data : [{"business_id":"67","business_Name":"","Contact_Person":"","contact_No":"","business_email_id":"","Salon_password":""}]
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
         * business_id : 67
         * business_Name :
         * Contact_Person :
         * contact_No :
         * business_email_id :
         * Salon_password :
         */

        private String business_id;
        private String business_Name;
        private String Contact_Person;
        private String contact_No;
        private String business_email_id;
        private String Salon_password;

        public String getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(String business_id) {
            this.business_id = business_id;
        }

        public String getBusiness_Name() {
            return business_Name;
        }

        public void setBusiness_Name(String business_Name) {
            this.business_Name = business_Name;
        }

        public String getContact_Person() {
            return Contact_Person;
        }

        public void setContact_Person(String Contact_Person) {
            this.Contact_Person = Contact_Person;
        }

        public String getContact_No() {
            return contact_No;
        }

        public void setContact_No(String contact_No) {
            this.contact_No = contact_No;
        }

        public String getBusiness_email_id() {
            return business_email_id;
        }

        public void setBusiness_email_id(String business_email_id) {
            this.business_email_id = business_email_id;
        }

        public String getSalon_password() {
            return Salon_password;
        }

        public void setSalon_password(String Salon_password) {
            this.Salon_password = Salon_password;
        }
    }
}
