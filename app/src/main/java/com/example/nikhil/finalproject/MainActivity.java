package com.example.nikhil.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSignIn,buttonSignUp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignup);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonSignUp){
            Intent intentSignUp = new Intent(this,CreateAccount.class);
            startActivity(intentSignUp);
    }else if(v == buttonSignIn){
            Intent intentSignIn = new Intent(this,NewRequestPage.class);
            startActivity(intentSignIn);
        }

}}
