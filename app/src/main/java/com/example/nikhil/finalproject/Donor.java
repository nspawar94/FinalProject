package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;
//

public class Donor {


    public Date createdDate;
    public String location;
    //Boolean isManual;// manual vs urgent
    public String donateType;

    public Donor(Date createdDate, String location, String donateType) {
        this.createdDate = createdDate;
        this.location = location;
        this.donateType = donateType;

     }

    public Date getCreatedDate(){
        return this.getCreatedDate();

    }
    public String getLocation(){
        return  this.location;

    }

    public String getDonateType (){
        return this.donateType;
    }


}

