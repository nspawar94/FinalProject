package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends Activity implements View.OnClickListener{
    Button buttonNewRequest, buttonNewDonation, buttonCurrentRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        buttonNewRequest = findViewById(R.id.buttonNewRequest);
        buttonNewDonation = findViewById(R.id.buttonNewDonation);
        buttonCurrentRequest = findViewById(R.id.buttonCurrentRequest);
        buttonNewRequest.setOnClickListener(this);
        buttonNewDonation.setOnClickListener(this);
        buttonCurrentRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        if(v==buttonNewRequest){
            i = new Intent(this,NewRequestPage.class);
            this.startActivity(i);
        }else if(v==buttonNewDonation){
            i = new Intent(this,ManualDonationPage.class);
            this.startActivity(i);
        }else if(v==buttonCurrentRequest){
            i = new Intent(this, RequestDashboard.class);
            this.startActivity(i);
        }
    }
}
