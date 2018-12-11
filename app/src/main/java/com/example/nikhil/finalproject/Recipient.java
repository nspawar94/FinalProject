package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story;
    public double age;
    private boolean recipientStatus;


    public Recipient(String fname, String lname, String btype, String location, String story, double age) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.recipientStatus = true;

    }

    public Recipient() {
    }

    public void closeRequest(){
        this.recipientStatus = false;
    }

    public boolean getRequestStatus(){
        return this.recipientStatus;
    }

    public void setEndDate(){

    }

    public Calendar getEndDate(){
        return null;
    }
}
