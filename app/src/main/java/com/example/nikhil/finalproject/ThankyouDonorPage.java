package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ThankyouDonorPage extends AppCompatActivity implements View.OnClickListener   {

    Button buttonProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou_donor_page);

        buttonProfile = findViewById(R.id.buttonProfile);

        buttonProfile.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == buttonProfile){
            Intent intentDonorProfile = new Intent(this,DonorProfilePage.class);
            startActivity(intentDonorProfile);
        }


    }
}
