package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestConfirmPage extends Activity implements View.OnClickListener{
    Button buttonViewReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirm_page);
        buttonViewReal=findViewById(R.id.buttonViewReal);
        buttonViewReal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonViewReal){
            Intent intentRealTime = new Intent(this,RequestStatusPage.class);
            startActivity(intentRealTime);

    }
}}
