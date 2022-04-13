package com.example.letus.model;

import java.util.Date;

public class MessageModel {
    String message;
    int sentAt;
    User sender;


    public MessageModel(String message, int sentAt, User sender) {
        this.message = message;
        this.sentAt = sentAt;
        this.sender = sender;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSentAt() {
        return sentAt;
    }

    public void setSentAt(int sentAt) {
        this.sentAt = sentAt;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

}
