package com.example.letus.model;

import java.util.Date;

public class User{
    public String email;
    public String login;
    public String password;
    public String birthDate;
    public String id;

    public User() {

    }

    public User(String login, String email, String birthDate, String id) {
        this.login = login;
        this.email = email;
        this.birthDate = birthDate;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }
    public String getEmail(){
        return this.email;
    }
}
