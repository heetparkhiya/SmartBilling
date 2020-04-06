package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bean_Response_Product_Multi {

    @SerializedName("Response")
    @Expose
    private Integer response;
    @SerializedName("Data")
    @Expose
    private List<Integer> data = null;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
