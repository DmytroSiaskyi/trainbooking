package com.trainbooking.dto;

public class TicketStatisticDto {
    private String client;
    private int count;
    public TicketStatisticDto(){}

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

