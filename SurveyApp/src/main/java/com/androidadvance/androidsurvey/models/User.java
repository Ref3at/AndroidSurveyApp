package com.androidadvance.androidsurvey.models;

public class User {
    private String user_id;
    private String user_name;
    private String signature_path;


    public User(String user_id, String user_name, String signature_path) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.signature_path = signature_path;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSignature_path() {
        return signature_path;
    }

    public void setSignature_path(String signature_path) {
        this.signature_path = signature_path;
    }
}