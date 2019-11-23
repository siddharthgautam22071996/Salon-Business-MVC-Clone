package com.abhi.fabkutbusiness.marketing.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siddharthgautam on 17/12/18.
 */

public class ResponseCreatePromotionData  implements Parcelable{

    private String fabkut_offer_id;
    private String fabkut_offer_type_id;
    private String fabkut_offer_code;
    private String fabkut_offer_name;
    private String fabkut_offer_desc;
    private String fabkut_offer_pic;
    private String fabkut_offer_start;
    private String fabkut_offer_end;
    private String fabkut_offer_price;
    private String fabkut_offer_disc_perc;
    private String active;
    private String business_id;

    protected ResponseCreatePromotionData(Parcel in) {
        fabkut_offer_id = in.readString();
        fabkut_offer_type_id = in.readString();
        fabkut_offer_code = in.readString();
        fabkut_offer_name = in.readString();
        fabkut_offer_desc = in.readString();
        fabkut_offer_pic = in.readString();
        fabkut_offer_start = in.readString();
        fabkut_offer_end = in.readString();
        fabkut_offer_price = in.readString();
        fabkut_offer_disc_perc = in.readString();
        active = in.readString();
        business_id = in.readString();
    }

    public static final Creator<ResponseCreatePromotionData> CREATOR = new Creator<ResponseCreatePromotionData>() {
        @Override
        public ResponseCreatePromotionData createFromParcel(Parcel in) {
            return new ResponseCreatePromotionData(in);
        }

        @Override
        public ResponseCreatePromotionData[] newArray(int size) {
            return new ResponseCreatePromotionData[size];
        }
    };

    public String getFabkut_offer_id() {
        return fabkut_offer_id;
    }

    public void setFabkut_offer_id(String fabkut_offer_id) {
        this.fabkut_offer_id = fabkut_offer_id;
    }

    public String getFabkut_offer_type_id() {
        return fabkut_offer_type_id;
    }

    public void setFabkut_offer_type_id(String fabkut_offer_type_id) {
        this.fabkut_offer_type_id = fabkut_offer_type_id;
    }

    public String getFabkut_offer_code() {
        return fabkut_offer_code;
    }

    public void setFabkut_offer_code(String fabkut_offer_code) {
        this.fabkut_offer_code = fabkut_offer_code;
    }

    public String getFabkut_offer_name() {
        return fabkut_offer_name;
    }

    public void setFabkut_offer_name(String fabkut_offer_name) {
        this.fabkut_offer_name = fabkut_offer_name;
    }

    public String getFabkut_offer_desc() {
        return fabkut_offer_desc;
    }

    public void setFabkut_offer_desc(String fabkut_offer_desc) {
        this.fabkut_offer_desc = fabkut_offer_desc;
    }

    public String getFabkut_offer_pic() {
        return fabkut_offer_pic;
    }

    public void setFabkut_offer_pic(String fabkut_offer_pic) {
        this.fabkut_offer_pic = fabkut_offer_pic;
    }

    public String getFabkut_offer_start() {
        return fabkut_offer_start;
    }

    public void setFabkut_offer_start(String fabkut_offer_start) {
        this.fabkut_offer_start = fabkut_offer_start;
    }

    public String getFabkut_offer_end() {
        return fabkut_offer_end;
    }

    public void setFabkut_offer_end(String fabkut_offer_end) {
        this.fabkut_offer_end = fabkut_offer_end;
    }

    public String getFabkut_offer_price() {
        return fabkut_offer_price;
    }

    public void setFabkut_offer_price(String fabkut_offer_price) {
        this.fabkut_offer_price = fabkut_offer_price;
    }

    public String getFabkut_offer_disc_perc() {
        return fabkut_offer_disc_perc;
    }

    public void setFabkut_offer_disc_perc(String fabkut_offer_disc_perc) {
        this.fabkut_offer_disc_perc = fabkut_offer_disc_perc;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fabkut_offer_id);
        parcel.writeString(fabkut_offer_type_id);
        parcel.writeString(fabkut_offer_code);
        parcel.writeString(fabkut_offer_name);
        parcel.writeString(fabkut_offer_desc);
        parcel.writeString(fabkut_offer_pic);
        parcel.writeString(fabkut_offer_start);
        parcel.writeString(fabkut_offer_end);
        parcel.writeString(fabkut_offer_price);
        parcel.writeString(fabkut_offer_disc_perc);
        parcel.writeString(active);
        parcel.writeString(business_id);
    }
}
