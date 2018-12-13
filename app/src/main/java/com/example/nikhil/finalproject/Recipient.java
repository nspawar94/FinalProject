package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story,donorEmail;
    public double age;
    private boolean isOpen,isAccepted;
    public int acceptDay, acceptMonth, acceptYear;


    public Recipient(String fname, String lname, String btype, String location, String story, double age, boolean isOpen, boolean isAccepted) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.isOpen = isOpen;
        this.isAccepted = isAccepted;
    }

    public Recipient() {
        }

    public void setAccepted(boolean b){
        if(b == true){
            this.isAccepted = true;
        }else this.isAccepted = false;
    }

    public void setDonorEmail(String donorEmail){
            this.donorEmail= donorEmail;
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

    public int getAcceptDay(){
        return this.acceptDay;
    }
    public int getAcceptMonth(){
        return this.acceptMonth;
    }
    public int getAcceptYear(){
        return this.acceptYear;
    }

    }

