package com.example.nikhil.finalproject;

import java.util.Date;
//

public class Donor {
    String location;
    Date createdDate;

    public Donor(Date createdDate, String location) {
        this.createdDate = createdDate;
        this.location = location;
    }

    public Donor() {


    }
}
