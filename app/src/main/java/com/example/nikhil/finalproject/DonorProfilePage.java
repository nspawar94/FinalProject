package com.example.nikhil.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonorProfilePage extends Activity implements View.OnClickListener{

   Button buttonAddNewDonation, switchDonationType, buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile_page);

        buttonAddNewDonation = findViewById(R.id.buttonAddNewDonation);
        switchDonationType = findViewById(R.id.switchDonationType);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonAddNewDonation.setOnClickListener(this);
        switchDonationType.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
