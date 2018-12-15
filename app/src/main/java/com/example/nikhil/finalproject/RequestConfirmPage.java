package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RequestConfirmPage extends AppCompatActivity implements View.OnClickListener{
    Button buttonViewReal;
    String recipientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirm_page);
        buttonViewReal=findViewById(R.id.buttonViewReal);
        buttonViewReal.setOnClickListener(this);

        recipientInfo =  getIntent().getStringExtra("Recipient ID");
    }

    @Override
    public void onClick(View v) {
        if(v == buttonViewReal){
            Intent intentRealTime = new Intent(this,RequestStatusPage.class);
            intentRealTime.putExtra("Recipient ID", recipientInfo);
            startActivity(intentRealTime);

    }
}}
