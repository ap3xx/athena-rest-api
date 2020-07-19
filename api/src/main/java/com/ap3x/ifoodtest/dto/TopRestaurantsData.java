package com.ap3x.ifoodtest.dto;

import java.util.ArrayList;


public class TopRestaurantsData {

    private Integer customerId;
    private ArrayList<String> topRestaurants;

    public TopRestaurantsData(Integer customerId) {
        this.customerId = customerId;
        this.topRestaurants = new ArrayList<>();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public ArrayList<String> getTopRestaurants() {
        return topRestaurants;
    }

    public void setTopRestaurants(ArrayList<String> topRestaurants) {
        this.topRestaurants = topRestaurants;
    }
}
