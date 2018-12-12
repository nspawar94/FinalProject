package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story;
    public double age;
    private boolean isOpen,isAccepted;
    Date date;

    //trying weeee
    public Recipient(String fname, String lname, String btype, String location, String story, double age, boolean isOpen, Date date) {
    }

    public Recipient(String fname, String lname, String btype, String location, String story, double age) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.isOpen = true;
        this.isAccepted = false;
    }

    public Recipient() {
        }

    public void setAccepted(boolean b){
        if(b == true){
            this.isAccepted = true;
        }else this.isAccepted = false;
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
        Calendar cal = Calendar.getInstance();
        this.date = Calendar.getInstance().getTime();
    }


    public Calendar getEndDate(){
        return null;
    }
}
