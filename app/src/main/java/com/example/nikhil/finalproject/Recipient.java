package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story;
    public double age;
    private boolean isOpen;
    Date date;

    public Recipient(String fname, String lname, String btype, String location, String story, double age, boolean isOpen, Date date) {
    }

    public Recipient(String fname, String lname, String btype, String location, String story, double age) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.isOpen = isOpen;
        this.date = date;
    }

    public Recipient() {
        }



    public Date getDate(){
        return date;
    }

    public String getLocation(){
        return location;
    }
    public void closeRequest(){
        this.isOpen = false;
    }

    public boolean getRequestStatus(){
        return this.isOpen;
    }

    public void setCurrentDate(){
        this.date = Calendar.getInstance().getTime();
    }
    
    public Calendar getEndDate(){
        return null;
    }
}
