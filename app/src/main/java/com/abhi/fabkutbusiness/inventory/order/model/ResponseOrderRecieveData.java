package com.abhi.fabkutbusiness.inventory.order.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siddharthgautam on 04/04/18.
 */

public class ResponseOrderRecieveData implements Parcelable {

    private String product_id;
    private String ItemBrandName;
    private String Type_Code;
    private String Brand;
    private String Item_name;
    private String UnitName;
    private String Product_code;
    private String qty;
    private String remark;

    public ResponseOrderRecieveData(Parcel in) {
        product_id = in.readString();
        ItemBrandName = in.readString();
        Type_Code = in.readString();
        Brand = in.readString();
        Item_name = in.readString();
        UnitName = in.readString();
        Product_code = in.readString();
        qty = in.readString();
        remark = in.readString();
    }

    public static final Creator<ResponseOrderRecieveData> CREATOR = new Creator<ResponseOrderRecieveData>() {
        @Override
        public ResponseOrderRecieveData createFromParcel(Parcel in) {
            return new ResponseOrderRecieveData(in);
        }

        @Override
        public ResponseOrderRecieveData[] newArray(int size) {
            return new ResponseOrderRecieveData[size];
        }
    };


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getItemBrandName() {
        return ItemBrandName;
    }

    public void setItemBrandName(String itemBrandName) {
        ItemBrandName = itemBrandName;
    }

    public String getType_Code() {
        return Type_Code;
    }

    public void setType_Code(String type_Code) {
        Type_Code = type_Code;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getProduct_code() {
        return Product_code;
    }

    public void setProduct_code(String product_code) {
        Product_code = product_code;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(product_id);
        parcel.writeString(ItemBrandName);
        parcel.writeString(Type_Code);
        parcel.writeString(Brand);
        parcel.writeString(Item_name);
        parcel.writeString(UnitName);
        parcel.writeString(Product_code);
        parcel.writeString(qty);
        parcel.writeString(remark);
    }
}
