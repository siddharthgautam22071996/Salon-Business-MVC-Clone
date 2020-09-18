package com.admin.Model;

import java.util.List;

public class AllServicesModel {
    /**
     * MESSAGE : SUCCESS
     * STATUS : 200
     * data : [{"Service_id":"16","Service_name":"Hair"},{"Service_id":"17","Service_name":"Face"},{"Service_id":"18","Service_name":"Massage"},{"Service_id":"20","Service_name":"Nails"},{"Service_id":"21","Service_name":"BODY"},{"Service_id":"22","Service_name":"Makeup"},{"Service_id":"23","Service_name":"Manicure"},{"Service_id":"24","Service_name":"Padicure"},{"Service_id":"25","Service_name":"Tatoo"},{"Service_id":"26","Service_name":"Packages"}]
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
         * Service_id : 16
         * Service_name : Hair
         */

        private String Service_id;
        private String Service_name;

        public String getService_id() {
            return Service_id;
        }

        public void setService_id(String Service_id) {
            this.Service_id = Service_id;
        }

        public String getService_name() {
            return Service_name;
        }

        public void setService_name(String Service_name) {
            this.Service_name = Service_name;
        }
    }
}
