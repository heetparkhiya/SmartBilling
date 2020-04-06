package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bean_Transport {
    @SerializedName("TransportID")
    @Expose
    private String transportID;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("TransportName")
    @Expose
    private String transportName;
    @SerializedName("TransportAddress")
    @Expose
    private String transportAddress;
    @SerializedName("TransportMobileNumber")
    @Expose
    private String transportMobileNumber;
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

    public String getTransportID() {
        return transportID;
    }

    public void setTransportID(String transportID) {
        this.transportID = transportID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportAddress() {
        return transportAddress;
    }

    public void setTransportAddress(String transportAddress) {
        this.transportAddress = transportAddress;
    }

    public String getTransportMobileNumber() {
        return transportMobileNumber;
    }

    public void setTransportMobileNumber(String transportMobileNumber) {
        this.transportMobileNumber = transportMobileNumber;
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
