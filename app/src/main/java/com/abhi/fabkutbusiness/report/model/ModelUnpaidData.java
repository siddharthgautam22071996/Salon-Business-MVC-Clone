package com.abhi.fabkutbusiness.report.model;

/**
 * Created by siddharthgautam on 25/07/18.
 */

public class ModelUnpaidData {
    private String customName;
    private String amt;

    public ModelUnpaidData(String customName, String amt) {
        this.customName = customName;
        this.amt = amt;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
