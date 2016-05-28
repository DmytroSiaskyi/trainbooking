package com.trainbooking.dto;

import com.trainbooking.entity.Ticket;

public class SalesDto {
    private String cashier;
    private String client;
    private String date;
    private String price;
    public SalesDto(){}

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
