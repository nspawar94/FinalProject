package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story;
    public double age;
    private boolean isOpen;
    Date date;

    public Recipient(String fname, String lname, String btype, String location, String story, double age, boolean isOpen, Date date) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.isOpen = isOpen;
        this.date = date;
    }



    public Date getDate(){
        return date;
    }

    public String getLocation(){
        return location;
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
