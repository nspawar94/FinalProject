package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends Activity implements View.OnClickListener {
    Button buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            if(v == buttonNext){
                Intent intentNext = new Intent(this,TermsOfUse.class);
                startActivity(intentNext);
            }}

    }
