package com.example.nikhil.finalproject;

public class User {

    public String fname, lname, gender, btype;
    public double age;
    public boolean userStatus;
    //if userStatus = false >>> inactive, else it's active
    public User(String fname, String lname, String gender, String btype, double age) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.btype = btype;
        this.age = age;
    }

    public User() {
    }



}
