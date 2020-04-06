package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bean_ListEntry {
    @SerializedName("ListID")
    @Expose
    private String listID;
    @SerializedName("ListType")
    @Expose
    private String listType;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("PartyName")
    @Expose
    private String partyName;
    @SerializedName("PartyMobileNo")
    @Expose
    private String partyMobileNo;
    @SerializedName("PartyAddress")
    @Expose
    private String partyAddress;
    @SerializedName("PartyCSTNo")
    @Expose
    private String partyCSTNo;
    @SerializedName("PartyTransportName")
    @Expose
    private String partyTransportName;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ListNo")
    @Expose
    private String listNo;
    @SerializedName("ListDate")
    @Expose
    private String listDate;
    @SerializedName("NoofCases")
    @Expose
    private String noofCases;
    @SerializedName("TotalQuantity")
    @Expose
    private String totalQuantity;
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

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyMobileNo() {
        return partyMobileNo;
    }

    public void setPartyMobileNo(String partyMobileNo) {
        this.partyMobileNo = partyMobileNo;
    }

    public String getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(String partyAddress) {
        this.partyAddress = partyAddress;
    }

    public String getPartyCSTNo() {
        return partyCSTNo;
    }

    public void setPartyCSTNo(String partyCSTNo) {
        this.partyCSTNo = partyCSTNo;
    }

    public String getPartyTransportName() {
        return partyTransportName;
    }

    public void setPartyTransportName(String partyTransportName) {
        this.partyTransportName = partyTransportName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getListNo() {
        return listNo;
    }

    public void setListNo(String listNo) {
        this.listNo = listNo;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getNoofCases() {
        return noofCases;
    }

    public void setNoofCases(String noofCases) {
        this.noofCases = noofCases;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
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
