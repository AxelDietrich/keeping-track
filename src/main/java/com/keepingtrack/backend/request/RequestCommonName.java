package com.keepingtrack.backend.request;

import java.io.Serializable;

public class RequestCommonName implements Serializable {

    private static final long serialVersionUID = 5544022451468668893L;
    private String name;

    public RequestCommonName(String name) {
        this.name = name;
    }

    public RequestCommonName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
