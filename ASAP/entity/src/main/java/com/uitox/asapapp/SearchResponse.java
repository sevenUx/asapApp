package com.uitox.asapapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seven on 2015/9/10.
 */
public class SearchResponse {
    @SerializedName("SM_NAME")
    private String SM_NAME;

    @SerializedName("SM_PIC")
    private String SM_PIC;

    @SerializedName("market_info")
    private marketInfo market_info;

    public SearchResponse(String SM_NAME, String SM_PIC, marketInfo market_info) {
        this.SM_NAME = SM_NAME;
        this.SM_PIC = SM_PIC;
        this.market_info = market_info;
    }

    public String getSM_NAME() {
        return SM_NAME;
    }

    public void setSM_NAME(String SM_NAME) {
        this.SM_NAME = SM_NAME;
    }

    public String getSM_PIC() {
        return SM_PIC;
    }

    public void setSM_PIC(String SM_PIC) {
        this.SM_PIC = SM_PIC;
    }

    public marketInfo getMarket_info() {
        return market_info;
    }

    public void setMarket_info(marketInfo market_info) {
        this.market_info = market_info;
    }
}
