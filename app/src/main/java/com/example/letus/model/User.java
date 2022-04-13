package com.example.letus.model;

public class User{
    public String Email;
    public String login;
    public String password;


    public User() {

    }

    public User(String login, String Email) {
        this.login = login;
        this.Email = Email;

    }

    public String getLogin() {
        return login;
    }
}
