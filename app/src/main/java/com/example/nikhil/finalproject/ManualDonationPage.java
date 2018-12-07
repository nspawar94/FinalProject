package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class ManualDonationPage extends Activity implements View.OnClickListener {

    EditText editLocationEnter, editDate;
    Button buttonClear, buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_donation_page);

        editLocationEnter = findViewById(R.id.editLocationEnter);
        editDate = findViewById(R.id.editDate);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonClear.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSubmit){

            Intent intentProfile = new Intent(this,DonorProfilePage.class);
            startActivity(intentProfile);
            }

    }
}
