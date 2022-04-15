package com.example.letus.model;

import android.provider.ContactsContract;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;

public class DiscussionModel implements Serializable {


    private String lastmessage, date;
    private FirebaseUser fuser;
    private User user;
    ImageView imageProfil;

    public DiscussionModel(User user) {
        this.user = user;
        //this.lastmessage = lastmessage;
        //this.date = date;
        fuser = FirebaseAuth.getInstance().getCurrentUser();
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
