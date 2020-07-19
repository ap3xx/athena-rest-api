package com.ap3x.ifoodtest.dto;

public class OrderByStateData {

    private String state;
    private Integer orders;
    private String date;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
