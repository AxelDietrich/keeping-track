package com.keepingtrack.backend.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUpdateRecord implements Serializable {

    private static final long serialVersionUID = 5442045288204681039L;
    private String name;
    private Double amount;

    public RequestUpdateRecord(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public RequestUpdateRecord(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
