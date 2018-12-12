package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PreventAcceptPage extends AppCompatActivity implements View.OnClickListener{

    Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevent_accept_page);

        buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == buttonOk){
            Intent intentRequestDashboard = new Intent(this,RequestDashboard.class);
            startActivity(intentRequestDashboard);
        }

    }
}
