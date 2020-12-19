package io.rbetik12.eengine.model;

import java.io.Serializable;

public class Response implements Serializable {
    private String text;

    public Response(String text) {
        this.text = text;
    }

    public Response() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
