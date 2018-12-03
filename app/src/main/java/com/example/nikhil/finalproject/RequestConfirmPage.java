package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestConfirmPage extends Activity implements View.OnClickListener{
    Button buttonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirm_page);

        buttonRequest.findViewById(R.id.buttonRequest);
        buttonRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRequest){
            Intent intentRequest = new Intent(this,MainActivity.class);
            startActivity(intentRequest);

    }
}}
