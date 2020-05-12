package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bean_Company {
    @SerializedName("CompanyID")
    @Expose
    private String companyID;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("CompanyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("CompanyContactNumber")
    @Expose
    private String companyContactNumber;
    @SerializedName("CompanyFaxNumber")
    @Expose
    private String companyFaxNumber;
    @SerializedName("CompanyEmail")
    @Expose
    private String companyEmail;
    @SerializedName("CompanyVAT_TIN_No")
    @Expose
    private String companyVATTINNo;
    @SerializedName("CompanyCST_TIN_No")
    @Expose
    private String companyCSTTINNo;
    @SerializedName("IsDelete")
    @Expose
    private String isDelete;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("Remarks")
    @Expose
    private String remarks;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContactNumber() {
        return companyContactNumber;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        this.companyContactNumber = companyContactNumber;
    }

    public String getCompanyFaxNumber() {
        return companyFaxNumber;
    }

    public void setCompanyFaxNumber(String companyFaxNumber) {
        this.companyFaxNumber = companyFaxNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyVATTINNo() {
        return companyVATTINNo;
    }

    public void setCompanyVATTINNo(String companyVATTINNo) {
        this.companyVATTINNo = companyVATTINNo;
    }

    public String getCompanyCSTTINNo() {
        return companyCSTTINNo;
    }

    public void setCompanyCSTTINNo(String companyCSTTINNo) {
        this.companyCSTTINNo = companyCSTTINNo;
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
}
