package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bean_Product_Item_Size {

    @SerializedName("ProductID")
    @Expose
    private String productID;
    @SerializedName("SizeID")
    @Expose
    private String sizeID;
    @SerializedName("Size")
    @Expose
    private String size;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("MRP")
    @Expose
    private String mRP;

    private String SizeWiseQty;

    public Bean_Product_Item_Size(String size, String rate, String mRP, String SizeWiseQty) {
        this.size = size;
        this.rate = rate;
        this.mRP = mRP;
        this.SizeWiseQty = SizeWiseQty;
    }

    public String getSizeWiseQty() {
        return SizeWiseQty;
    }

    public void setSizeWiseQty(String sizeWiseQty) {
        SizeWiseQty = sizeWiseQty;
    }



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSizeID() {
        return sizeID;
    }

    public void setSizeID(String sizeID) {
        this.sizeID = sizeID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMRP() {
        return mRP;
    }

    public void setMRP(String mRP) {
        this.mRP = mRP;
    }
}
