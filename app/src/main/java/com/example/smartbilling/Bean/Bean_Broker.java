package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bean_Broker {
    @SerializedName("BrokerID")
    @Expose
    private String brokerID;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("BrokerName")
    @Expose
    private String brokerName;
    @SerializedName("BrokerAddress")
    @Expose
    private String brokerAddress;
    @SerializedName("BrokerTelephoneNumber")
    @Expose
    private String brokerTelephoneNumber;
    @SerializedName("BrokerFaxNumber")
    @Expose
    private String brokerFaxNumber;
    @SerializedName("BrokerMobileNumber")
    @Expose
    private String brokerMobileNumber;
    @SerializedName("BrokerEmail")
    @Expose
    private String brokerEmail;
    @SerializedName("BrokerRate")
    @Expose
    private String brokerRate;
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

    public String getBrokerID() {
        return brokerID;
    }

    public void setBrokerID(String brokerID) {
        this.brokerID = brokerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public String getBrokerTelephoneNumber() {
        return brokerTelephoneNumber;
    }

    public void setBrokerTelephoneNumber(String brokerTelephoneNumber) {
        this.brokerTelephoneNumber = brokerTelephoneNumber;
    }

    public String getBrokerFaxNumber() {
        return brokerFaxNumber;
    }

    public void setBrokerFaxNumber(String brokerFaxNumber) {
        this.brokerFaxNumber = brokerFaxNumber;
    }

    public String getBrokerMobileNumber() {
        return brokerMobileNumber;
    }

    public void setBrokerMobileNumber(String brokerMobileNumber) {
        this.brokerMobileNumber = brokerMobileNumber;
    }

    public String getBrokerEmail() {
        return brokerEmail;
    }

    public void setBrokerEmail(String brokerEmail) {
        this.brokerEmail = brokerEmail;
    }

    public String getBrokerRate() {
        return brokerRate;
    }

    public void setBrokerRate(String brokerRate) {
        this.brokerRate = brokerRate;
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
