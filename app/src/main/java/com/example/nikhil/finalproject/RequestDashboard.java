package com.example.nikhil.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestDashboard extends Activity implements View.OnClickListener {
    Button buttonAccept1, buttonAccept2, buttonCant1, buttonCant2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_dashboard);

        buttonAccept1 = findViewById(R.id.buttonAccept1);
        buttonAccept2 = findViewById(R.id.buttonAccept2);
        buttonCant1 = findViewById(R.id.buttonCant1);
        buttonCant2 = findViewById(R.id.buttonCant2);

        buttonAccept1.setOnClickListener(this);
        buttonAccept2.setOnClickListener(this);
        buttonCant1.setOnClickListener(this);
        buttonCant2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
