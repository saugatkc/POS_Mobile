package com.example.pos_mobile.models;

public class UserModel {
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel(String fullname, String email, String phone, String password) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    private String fullname,email  ,phone,password;
}
