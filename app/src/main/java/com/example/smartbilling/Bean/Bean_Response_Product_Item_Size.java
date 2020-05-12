package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bean_Response_Product_Item_Size {

    @SerializedName("Response")
    @Expose
    private Integer response;
    @SerializedName("Data")
    @Expose
    private List<Bean_Product_Item_Size> data = null;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Bean_Product_Item_Size> getData() {
        return data;
    }

    public void setData(List<Bean_Product_Item_Size> data) {
        this.data = data;
    }
}
