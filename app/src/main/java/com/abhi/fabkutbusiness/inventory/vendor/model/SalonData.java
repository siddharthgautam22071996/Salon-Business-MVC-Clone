package com.abhi.fabkutbusiness.inventory.vendor.model;

/**
 * Created by Ashish on 6/15/2017.
 */

public class SalonData {
private String name;

    public SalonData(String name, int contact, int code, int tin) {
        this.name = name;
        this.contact = contact;
        this.code = code;
        this.tin = tin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    private int contact;
    private int code;
    private int tin;

}
