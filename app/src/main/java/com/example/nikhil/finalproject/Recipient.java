package com.example.nikhil.finalproject;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story,donorEmail,recipientID;
    public double age;
    public boolean isOpen,isAccepted;
    public int acceptDay, acceptMonth, acceptYear;
    public int endDay,endMonth,endYear;


    public Recipient(String fname, String lname, String btype,
                     String location, String story, double age, boolean isOpen,
                     boolean isAccepted, String donorEmail, String recipientID) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
        this.isOpen = isOpen;
        this.isAccepted = isAccepted;
        this.donorEmail = donorEmail;
        this.recipientID = recipientID;
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


    //set expiry date to 2 days after the input date
    public void setExpiredDate(int day, int month, int year){
        // Creating a Calendar for the current date
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        // Creating a Date for the expiry date
        Date d = new Date(c.getTime().getTime() + 2*24*60*60*1000);
        // Creating a Calendar for the expiry date
        Calendar newC = Calendar.getInstance();
        newC.setTime(d);

        // Setting the object expiry date values
        this.endYear = newC.get(Calendar.YEAR);
        this.endMonth = newC.get(Calendar.MONTH);
        this.endDay = newC.get(Calendar.DATE);


    }

    public void setIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    public void setAcceptDate(int day, int month, int year){
        this.acceptDay = day;
        this.acceptMonth = month;
        this.acceptYear = year;
    }

    public String getLocation(){
        return location;
    }
    public boolean getIsOpen(){
        return this.isOpen;
    }
    public boolean getIsAccepted(){ return this.isAccepted;}
    public String getDonorEmail(){return this.donorEmail;}
    public int getAcceptDay(){
        return this.acceptDay;
    }
    public int getAcceptMonth(){
        return this.acceptMonth;
    }
    public int getAcceptYear(){
        return this.acceptYear;
    }
    public int getEndDay(){
        return this.endDay;
    }
    public int getEndMonth(){
        return this.endMonth;
    }
    public int getEndYear(){
        return this.endYear;
    }
    public String getRecipientID(){return this.recipientID;}


    }

