package com.abhi.fabkutbusiness.inventory.vendor.model;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseLocationData {
    private String city_Id;
    private String location_Id;
    private String location_Name;
    private String city_Name;
    private String active;

    public String getCity_Id() {
        return city_Id;
    }

    public void setCity_Id(String city_Id) {
        this.city_Id = city_Id;
    }

    public String getLocation_Id() {
        return location_Id;
    }

    public void setLocation_Id(String location_Id) {
        this.location_Id = location_Id;
    }

    public String getLocation_Name() {
        return location_Name;
    }

    public void setLocation_Name(String location_Name) {
        this.location_Name = location_Name;
    }

    public String getCity_Name() {
        return city_Name;
    }

    public void setCity_Name(String city_Name) {
        this.city_Name = city_Name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
