package com.example.nikhil.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestStatusPage extends Activity implements View.OnClickListener {

    Button buttonRefresh, buttonCloseRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status_page);

        buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonCloseRequest = findViewById(R.id.buttonCloseRequest);

        buttonRefresh.setOnClickListener(this);
        buttonCloseRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
