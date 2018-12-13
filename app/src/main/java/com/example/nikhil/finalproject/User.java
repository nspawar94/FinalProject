package com.example.nikhil.finalproject;

import java.util.Date;

public class User {

    public String fname, lname, gender, btype, email;
    public double age;
    public int lastDonateDay, lastDonateMonth,lastDonateYear;
    public int nextDonateDay, nextDonateMonth,nextDonateYear;

    public User(String fname, String lname, String gender, String btype, double age, String email) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.btype = btype;
        this.age = age;
        this.email=email;
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
