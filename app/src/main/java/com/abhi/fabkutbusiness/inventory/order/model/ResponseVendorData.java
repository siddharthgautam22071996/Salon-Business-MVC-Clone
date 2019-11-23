package com.abhi.fabkutbusiness.inventory.order.model;

/**
 * Created by siddharth on 1/5/2018.
 */

public class ResponseVendorData {
    private String id;
    private String vendorName;
    private String vendorAddress;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
