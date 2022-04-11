package com.example.letus.model;

public class User {
    private String Email;
    private String login;
    private String password;
    private String birthDate;

    public User(String email, String login, String password, String birthDate) {
        this.Email = email;
        this.login = login;
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
