package com.uitox.asapapp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seven on 2015/9/10.
 */
public class priceInfo {

    @SerializedName("show_price")
    public priceDetail show_price;

    @SerializedName("final_price")
    public priceDetail final_price;

    public priceInfo(priceDetail show_price, priceDetail final_price) {
        this.show_price = show_price;
        this.final_price = final_price;
    }

    public priceDetail getShow_price() {
        return show_price;
    }

    public void setShow_price(priceDetail show_price) {
        this.show_price = show_price;
    }

    public priceDetail getFinal_price() {
        return final_price;
    }

    public void setFinal_price(priceDetail final_price) {
        this.final_price = final_price;
    }
}
