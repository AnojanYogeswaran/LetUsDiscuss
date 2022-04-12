package com.example.letus.model;

public class User {
    public String Email;
    public String login;
    public String password;
    public String birthDate;

    public User() {

    }

    public User(String login, String Email , String birthDate) {
        this.login = login;
        this.Email = Email;
        this.birthDate = birthDate;
    }

}
