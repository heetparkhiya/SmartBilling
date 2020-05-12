package com.example.smartbilling.Bean;

public class Bean_ProductItems {
    int ItemID;
    String DesignNo;
    String Size;
    String Rate;
    String Qty;
    String Amount;
    String RateSize;
    String MRP_Size;

    public Bean_ProductItems(String designNo, String size, String rate, String qty, String amount, String rateSize, String mrp_size) {
        DesignNo = designNo;
        Size = size;
        Rate = rate;
        Qty = qty;
        Amount = amount;
        RateSize = rateSize;
        MRP_Size = mrp_size;
    }

    public Bean_ProductItems() {

    }

    public String getRateSize() {
        return RateSize;
    }

    public void setRateSize(String rateSize) {
        RateSize = rateSize;
    }

    public String getMRP_Size() {
        return MRP_Size;
    }

    public void setMRP_Size(String MRP_Size) {
        this.MRP_Size = MRP_Size;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public String getDesignNo() {
        return DesignNo;
    }

    public void setDesignNo(String designNo) {
        DesignNo = designNo;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
