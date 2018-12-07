package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CloseRequestPage extends Activity implements View.OnClickListener{

    Button buttonBackToRequest, buttonConfirmClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_request_page);

        buttonBackToRequest = findViewById(R.id.buttonBackToRequest);
        buttonConfirmClose = findViewById(R.id.buttonConfirmClose);

        buttonBackToRequest.setOnClickListener(this);
        buttonConfirmClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == buttonConfirmClose){
            Intent intentHomePage = new Intent(this,HomePage.class);
            startActivity(intentHomePage);
        } else if (v == buttonBackToRequest){
            Intent intentCloseRequest = new Intent(this,RequestStatusPage.class);
            startActivity(intentCloseRequest);
        }

    }
}
