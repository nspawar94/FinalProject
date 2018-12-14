package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;


public class Donor {

    public String location;
    public String donateType;
    public String donorEmail;
    public String donationID;
    public int donateDay,donateMonth,donateYear;

    public Donor() {
    }

    public Donor(String location, String donateType,String donorEmail, String donationID) {
        this.location = location;
        this.donateType = donateType;
        this.donorEmail = donorEmail;
        this.donationID = donationID;
    }

    public String getLocation(){
        return  this.location;
    }

    public String getDonateType (){
        return this.donateType;
    }

    public void setDonateDate(int day, int month, int year){
        this.donateDay = day;
        this.donateMonth = month;
        this.donateYear = year;
    }

    public String getDonorEmail(){
        return this.donorEmail;
    }


}

