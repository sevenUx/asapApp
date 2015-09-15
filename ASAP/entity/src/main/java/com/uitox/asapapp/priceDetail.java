package com.uitox.asapapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seven on 2015/9/10.
 */
public class priceDetail {
    @SerializedName("price")
    public double price;

    public priceDetail(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
