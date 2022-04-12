package com.example.letus.model;

public class User {
    private String Email;
    private String login;
    private String password;
    private String birthDate;

    public User() {

    }

    public User(String login, String Email , String password, String birthDate) {
        this.login = login;
        this.Email = Email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
