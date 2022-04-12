package com.example.letusdiscuss;

import android.provider.ContactsContract;
import android.widget.ImageView;

import java.io.Serializable;

public class DiscussionModel implements Serializable {

    private String firstname,lastmessage, date;
    ImageView imageProfil;

    public DiscussionModel(String firstname, String lastmessage, String date) {
        this.firstname = firstname;
        this.lastmessage = lastmessage;
        this.date = date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ImageView getImage() {
        return imageProfil;
    }

    public void setImage(ImageView imageProfil) {
        this.imageProfil = imageProfil;
    }
}
