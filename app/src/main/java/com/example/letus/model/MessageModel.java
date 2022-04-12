package com.example.letus.model;

import java.util.Date;

public class MessageModel {
    String message;
    Date sentAt;
    User sender;
    User receiver;

    public MessageModel(String message, Date sentAt, User sender) {
        this.message = message;
        this.sentAt = sentAt;
        this.sender = sender;
        this.receiver= receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
