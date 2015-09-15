package com.uitox.asapapp;

/**
 * Created by seven on 2015/9/14.
 */
public class CategoryResponse {
    private String ItemName;

    public CategoryResponse(String itemName) {
        ItemName = itemName;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }
}
