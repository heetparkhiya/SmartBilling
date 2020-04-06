package com.example.smartbilling.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bean_Response_Collection {
    @SerializedName("Response")
    @Expose
    private Integer response;
    @SerializedName("Data")
    @Expose
    private List<Bean_Collection> data = null;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Bean_Collection> getData() {
        return data;
    }

    public void setData(List<Bean_Collection> data) {
        this.data = data;
    }
}
