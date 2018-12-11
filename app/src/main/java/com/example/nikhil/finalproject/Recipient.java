package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story;
    public double age;
    private boolean isOpen;


    public Recipient(String fname, String lname, String btype, String location, String story, double age) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.isOpen = true;

    }

    public Recipient() {
    }

    public void closeRequest(){
        this.isOpen = false;
    }

    public boolean getRequestStatus(){
        return this.isOpen;
    }

    public void setEndDate(){

    }

    public Calendar getEndDate(){
        return null;
    }
}
