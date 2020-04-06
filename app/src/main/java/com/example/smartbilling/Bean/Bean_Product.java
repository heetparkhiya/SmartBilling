package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bean_Product {
    @SerializedName("ProductID")
    @Expose
    private String productID;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Size")
    @Expose
    private ArrayList<Bean_Size> size = null;
    @SerializedName("ProductDesignNumber")
    @Expose
    private String productDesignNumber;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductStyle")
    @Expose
    private String productStyle;
    @SerializedName("IsDelete")
    @Expose
    private String isDelete;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("ProductMRP_PR")
    @Expose
    private String productMRPPR;
    @SerializedName("Remarks")
    @Expose
    private String remarks;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Bean_Size> getSize() {
        return size;
    }

    public void setSize(ArrayList<Bean_Size> size) {
        this.size = size;
    }

    public String getProductDesignNumber() {
        return productDesignNumber;
    }

    public void setProductDesignNumber(String productDesignNumber) {
        this.productDesignNumber = productDesignNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStyle() {
        return productStyle;
    }

    public void setProductStyle(String productStyle) {
        this.productStyle = productStyle;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProductMRPPR() {
        return productMRPPR;
    }

    public void setProductMRPPR(String productMRPPR) {
        this.productMRPPR = productMRPPR;
    }
}
