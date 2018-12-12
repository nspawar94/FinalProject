package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RequestStatusPage extends Activity implements View.OnClickListener {

    Button buttonRefresh, buttonCloseRequest, buttonReturnHome, buttonExtendRequest, buttonPm ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status_page);

        buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonCloseRequest = findViewById(R.id.buttonCloseRequest);
        buttonReturnHome = findViewById(R.id.buttonReturnHome);
        buttonExtendRequest = findViewById(R.id.buttonExtendRequest);
        buttonPm = findViewById(R.id.buttonPm);


        buttonRefresh.setOnClickListener(this);
        buttonCloseRequest.setOnClickListener(this);
        buttonReturnHome.setOnClickListener(this);
        buttonExtendRequest.setOnClickListener(this);
        buttonPm.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dropdown, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menuHome){
            Intent intentMenuHome = new Intent(this,HomePage.class);
            startActivity(intentMenuHome);
        } else if (item.getItemId()==R.id.menuDashboard) {
            Intent intentRequestDashboard = new Intent(this, RequestDashboard.class);
            startActivity(intentRequestDashboard);
        } else if (item.getItemId()==R.id.menuNewRequest) {
            Intent intentNewRequest= new Intent(this, NewRequestPage.class);
            startActivity(intentNewRequest);
        } else if (item.getItemId()==R.id.menuRequestStatus) {
            Toast.makeText(this, "You are here already!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.menuDonorMessage) {
            Intent intentDonorMessage = new Intent(this, DonorMessagePage.class);
            startActivity(intentDonorMessage);
        } else if (item.getItemId()==R.id.menuDonorProfile) {
            Intent intentDonorProfile = new Intent(this, DonorProfilePage.class);
            startActivity(intentDonorProfile);
        } else if (item.getItemId()==R.id.menuManualDonation) {
            Intent intentManualDonation = new Intent(this, ManualDonationPage.class);
            startActivity(intentManualDonation);
        } else if (item.getItemId()==R.id.menuLogOut) {
            Intent intentLogout = new Intent(this, MainActivity.class);
            startActivity(intentLogout);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonCloseRequest){
            Intent intentCloseRequest = new Intent(this,CloseRequestPage.class);
            startActivity(intentCloseRequest);

        }else if (v == buttonExtendRequest){
            Intent intentExtend = new Intent(this,ExtendRequestPage.class);
            startActivity(intentExtend);

        }else if (v == buttonRefresh){


        }else if (v == buttonReturnHome){
            Intent intentHome = new Intent(this, HomePage.class);
            startActivity(intentHome);

        }else if (v == buttonPm){
            Intent intentPm = new Intent(this,DonorMessagePage.class );
            startActivity(intentPm);

        }

    }
}
