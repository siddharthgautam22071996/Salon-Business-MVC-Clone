package com.abhi.fabkutbusiness.inventory.itemMaster.model;

/**
 * Created by siddharthgautam on 03/04/18.
 */

public class ResponseMyStockData {

    private String qty;
    private String product_name;

    public ResponseMyStockData() {
    }

    private String unit;
    private String expiryDate;

    public ResponseMyStockData(String qty, String product_name, String unit, String expiryDate) {
        this.qty = qty;
        this.product_name = product_name;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
