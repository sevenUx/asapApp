package com.uitox.asapapp;

/**
 * Created by seven on 2015/8/26.
 */
public class Product {
    private String Title, Describe,OrgPrice,RelPrice;

    public Product(String title,String describe, String orgPrice, String relPrice) {
        Title = title;
        Describe = describe;
        OrgPrice = orgPrice;
        RelPrice = relPrice;

    }

    public String getDescribe() {
        return Describe;
    }

    public String getOrgPrice() {
        return OrgPrice;
    }

    public String getRelPrice() {
        return RelPrice;
    }

    public String getTitle() {
        return Title;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public void setOrgPrice(String orgPrice) {
        OrgPrice = orgPrice;
    }

    public void setRelPrice(String relPrice) {
        RelPrice = relPrice;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
