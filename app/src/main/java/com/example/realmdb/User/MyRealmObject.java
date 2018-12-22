package com.example.realmdb.User;

import io.realm.RealmObject;
//import io.realm.annotations.PrimaryKey;
//import io.realm.annotations.PrimaryKey;

public class MyRealmObject extends RealmObject {

    private String email;
    private String fullname;
    private String password,confirmpassword;

    public MyRealmObject(){

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
