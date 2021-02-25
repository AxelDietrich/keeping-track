package com.keepingtrack.backend.request;

import java.io.Serializable;

public class RequestAddRecord implements Serializable {

    private static final long serialVersionUID = -6604949662406433882L;

    private String name;
    private double amount;

    public RequestAddRecord(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public RequestAddRecord() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
