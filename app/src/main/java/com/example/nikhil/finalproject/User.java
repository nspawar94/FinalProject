package com.example.nikhil.finalproject;

import java.util.Date;

public class User {

    public String fname, lname, gender, btype;
    public double age;
    //public Date latestDonate;

    public User(String fname, String lname, String gender, String btype, double age) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.btype = btype;
        this.age = age;
    }

    public User() {
    }

    public String getBtype(){
        return this.btype;
    }



}
