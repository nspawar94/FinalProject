package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;

public class Donor {


    Date createdDate;
    String location;
    //Boolean isManual;// manual vs urgent
    String donateType;

    public Donor(Date createdDate, String location,String donateType) {
        this.createdDate = createdDate;
        this.location = location;
        this.donateType = donateType;


     }

    public Date getxxx(){
        return this.createdDate;

    }
    public String getLocation(){
        return  this.location;


    }

    }

