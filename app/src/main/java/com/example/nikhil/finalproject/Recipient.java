package com.example.nikhil.finalproject;

public class Recipient {
    String fname, lname, btype, location, story;
    int age;

    public Recipient(String fname, String lname, String btype, String location, String story, int age) {
        this.fname = fname;
        this.lname = lname;
        this.btype = btype;
        this.location = location;
        this.story = story;
        this.age = age;
    }

    public Recipient() {
    }
}
