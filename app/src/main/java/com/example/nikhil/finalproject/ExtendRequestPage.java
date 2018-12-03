package com.example.nikhil.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExtendRequestPage extends Activity implements View.OnClickListener {

    Button buttonExtendRequest, buttonCloseRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_request_page);

        buttonExtendRequest = findViewById(R.id.buttonExtendRequest);
        buttonCloseRequest = findViewById(R.id.buttonCloseRequest);

        buttonExtendRequest.setOnClickListener(this);
        buttonCloseRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
