package com.keepingtrack.backend.request;

import java.io.Serializable;

public class RequestAddSubcategory implements Serializable {

    private String name;

    public RequestAddSubcategory(String name) {
        this.name = name;
    }

    public RequestAddSubcategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
