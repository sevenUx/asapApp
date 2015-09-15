package com.uitox.asapapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seven on 2015/9/10.
 */
public class marketInfo {

    @SerializedName("price")
    public priceInfo price;

    public marketInfo(priceInfo price) {
        this.price = price;
    }

    public priceInfo getPrice() {
        return price;
    }

    public void setPrice(priceInfo price) {
        this.price = price;
    }
}
