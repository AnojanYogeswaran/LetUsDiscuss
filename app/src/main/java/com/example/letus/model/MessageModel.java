package com.example.letus.model;

import java.util.Date;

public class MessageModel {
    String message;
    String receiver;
    String sender;
    long sentAt;


    public MessageModel(String message, String receiver,String sender,long sentAt) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.sentAt = sentAt;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSentAt() {
        return sentAt;
    }

    public void setSentAt(int sentAt) {
        this.sentAt = sentAt;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
