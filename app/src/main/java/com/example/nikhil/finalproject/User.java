package com.example.nikhil.finalproject;

import java.util.Date;

public class User {

    public String fname, lname, gender, btype, email,userID;
    public double age;
    public int lastDonateDay, lastDonateMonth,lastDonateYear;
    public int nextDonateDay, nextDonateMonth,nextDonateYear;

    public User(String fname, String lname, String gender, String btype, double age, String email,String userID) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.btype = btype;
        this.age = age;
        this.email=email;
        this.userID = userID;
    }

    public User() {
    }

    public String getBtype(){
        return this.btype;
    }

    public void setLastDonate(int day,int month, int year){
        this.lastDonateDay = day;
        this.lastDonateMonth = month;
        this.lastDonateYear = year;

    }
    public void setNextDonate(int day,int month, int year){
        //take current date and calculate the new date that this user can donate again
        if(month>9){
            this.nextDonateMonth = month-9;
            this.nextDonateYear = year+1;
        }else{
            this.nextDonateMonth = month;
            this.nextDonateYear = year;
        }
        this.nextDonateDay = day;
    }

}
