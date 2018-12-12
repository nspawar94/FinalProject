package com.example.nikhil.finalproject;

import java.util.Calendar;
import java.util.Date;
//

public class Donor {


    //public Date createdDate;
    public String location;
    //Boolean isManual;// manual vs urgent
    public String donateType;
    public String donorEmail;

    public Donor() {
    }

    public Donor(String location, String donateType, String donorEmail) {
        this.location = location;
        this.donateType = donateType;
        this.donorEmail = donorEmail;

    }

    // public Date getCreatedDate(){
     //   return this.getCreatedDate();

    //}
    public String getLocation(){
        return  this.location;

    }

    public String getDonateType (){
        return this.donateType;
    }

    public String getDonorEmail(){

        return this.donorEmail;
    }


}

