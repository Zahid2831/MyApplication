package com.example.myapplication.models;


import androidx.annotation.NonNull;

public class UserDetail {
    public int id;
    public String userName;
    public String userPassword;
    public String role;

    @NonNull
    @Override
    public String toString() {
        return "Id : "+id+
                "\nName : "+userName+
                "\nPassword : "+userPassword+
                "\nRole : "+role;
    }




}
