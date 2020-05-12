package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bean_Party {
    @SerializedName("PartyID")
    @Expose
    private String partyID;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("TransportID")
    @Expose
    private String transportID;
    @SerializedName("TransportName")
    @Expose
    private String transportName;
    @SerializedName("TransportAddress")
    @Expose
    private String transportAddress;
    @SerializedName("TransportMobileNumber")
    @Expose
    private String transportMobileNumber;
    @SerializedName("BrokerID")
    @Expose
    private String brokerID;
    @SerializedName("BrokerName")
    @Expose
    private String brokerName;
    @SerializedName("BrokerMobileNumber")
    @Expose
    private String brokerMobileNumber;
    @SerializedName("BrokerTelephoneNumber")
    @Expose
    private String brokerTelephoneNumber;
    @SerializedName("BrokerRate")
    @Expose
    private String brokerRate;
    @SerializedName("BrokerAddress")
    @Expose
    private String brokerAddress;
    @SerializedName("BrokerEmail")
    @Expose
    private String brokerEmail;
    @SerializedName("BrokerFaxNumber")
    @Expose
    private String brokerFaxNumber;
    @SerializedName("PartyName")
    @Expose
    private String partyName;
    @SerializedName("ReferenceName")
    @Expose
    private String referenceName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("BankThrough")
    @Expose
    private String bankThrough;
    @SerializedName("PIN")
    @Expose
    private String pIN;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("TelephoneNumber")
    @Expose
    private String telephoneNumber;
    @SerializedName("FaxNumber")
    @Expose
    private String faxNumber;
    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("EmailID")
    @Expose
    private String emailID;
    @SerializedName("PartyLocation")
    @Expose
    private String partyLocation;
    @SerializedName("CreditDays")
    @Expose
    private String creditDays;
    @SerializedName("TINNo")
    @Expose
    private String tINNo;
    @SerializedName("CSTNo")
    @Expose
    private String cSTNo;
    @SerializedName("DISC")
    @Expose
    private String dISC;
    @SerializedName("BrokerRage")
    @Expose
    private String brokerRage;
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

    public Bean_Party(String name)
    {
        partyName = name;
        partyID = "-1";
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTransportID() {
        return transportID;
    }

    public void setTransportID(String transportID) {
        this.transportID = transportID;
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

    public String getBrokerID() {
        return brokerID;
    }

    public void setBrokerID(String brokerID) {
        this.brokerID = brokerID;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerMobileNumber() {
        return brokerMobileNumber;
    }

    public void setBrokerMobileNumber(String brokerMobileNumber) {
        this.brokerMobileNumber = brokerMobileNumber;
    }

    public String getBrokerTelephoneNumber() {
        return brokerTelephoneNumber;
    }

    public void setBrokerTelephoneNumber(String brokerTelephoneNumber) {
        this.brokerTelephoneNumber = brokerTelephoneNumber;
    }

    public String getBrokerRate() {
        return brokerRate;
    }

    public void setBrokerRate(String brokerRate) {
        this.brokerRate = brokerRate;
    }

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public String getBrokerEmail() {
        return brokerEmail;
    }

    public void setBrokerEmail(String brokerEmail) {
        this.brokerEmail = brokerEmail;
    }

    public String getBrokerFaxNumber() {
        return brokerFaxNumber;
    }

    public void setBrokerFaxNumber(String brokerFaxNumber) {
        this.brokerFaxNumber = brokerFaxNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankThrough() {
        return bankThrough;
    }

    public void setBankThrough(String bankThrough) {
        this.bankThrough = bankThrough;
    }

    public String getPIN() {
        return pIN;
    }

    public void setPIN(String pIN) {
        this.pIN = pIN;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPartyLocation() {
        return partyLocation;
    }

    public void setPartyLocation(String partyLocation) {
        this.partyLocation = partyLocation;
    }

    public String getCreditDays() {
        return creditDays;
    }

    public void setCreditDays(String creditDays) {
        this.creditDays = creditDays;
    }

    public String getTINNo() {
        return tINNo;
    }

    public void setTINNo(String tINNo) {
        this.tINNo = tINNo;
    }

    public String getCSTNo() {
        return cSTNo;
    }

    public void setCSTNo(String cSTNo) {
        this.cSTNo = cSTNo;
    }

    public String getDISC() {
        return dISC;
    }

    public void setDISC(String dISC) {
        this.dISC = dISC;
    }

    public String getBrokerRage() {
        return brokerRage;
    }

    public void setBrokerRage(String brokerRage) {
        this.brokerRage = brokerRage;
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