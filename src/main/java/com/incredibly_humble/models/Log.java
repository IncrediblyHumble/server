package com.incredibly_humble.models;

import java.util.Date;

public class Log {
    private String request;
    private Date date;
    private String body;
    private String response;

    public Log(String request, Date date, String body, String response) {
        this.request = request;
        this.date = date;
        this.body = body;
        this.response = response;
    }

    public String getRequest() {
        return request;
    }

    public Date getDate() {
        return date;
    }

    public String getBody() {
        return body;
    }

    public String getResponse() {
        return response;
    }


}
