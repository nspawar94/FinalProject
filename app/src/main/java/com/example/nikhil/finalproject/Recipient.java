package com.example.nikhil.finalproject;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class Recipient {
    public String fname, lname, btype, location, story,donorEmail;
    public double age;
    private boolean isOpen,isAccepted;
    public int acceptDay, acceptMonth, acceptYear;
    public int endDay,endMonth,endYear;


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setExpiredDate(int day, int month, int year){
        //receive current date to set expired date
        YearMonth yearMonthObject = YearMonth.of(year,month);
        int daysIntMonth = yearMonthObject.lengthOfMonth();

        if(day>daysIntMonth-2){
            this.endDay = day - daysIntMonth + 2;
            if(month>11){
                this.endMonth = month-11;
                this.endYear = year +1;
            }else{
                this.endMonth = month+1;
                this.endYear = year;
            }
        }else{
            this.endYear = day + 2;
        }

    }

    public String getLocation(){
        return location;
    }
    public void setCloseRequest(){
        this.isOpen = false;
    }

    public boolean getOpenStatus(){
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
    public int getEndDay(){
        return this.endDay;
    }
    public int getEndMonth(){
        return this.endMonth;
    }
    public int getEndYear(){
        return this.endYear;
    }


    }

