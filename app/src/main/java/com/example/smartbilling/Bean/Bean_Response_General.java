package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bean_Response_General {
    @SerializedName("Response")
    @Expose
    private Integer response;
    @SerializedName("Data")
    @Expose
    private List<String> data = null;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
