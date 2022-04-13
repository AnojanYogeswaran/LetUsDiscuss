package com.example.letus.model;

import java.util.Date;

public class MessageModel {
    private String message;
    private Date sendat;
    private String sender;

    public MessageModel(String message , Date sendat , String sender) {
        this.message = message;
        this.sendat = sendat;
        this.sender  = sender;
    }

    public String getMessage() {
        return message;
    }

    public Date getSendat() {
        return sendat;
    }

    public String getSender() {
        return sender;
    }
}
