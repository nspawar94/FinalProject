package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
            Toast.makeText(this, "You are here already!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.menuNewRequest) {
            Intent intentNewRequest= new Intent(this, NewRequestPage.class);
            startActivity(intentNewRequest);
        } else if (item.getItemId()==R.id.menuRequestStatus) {
            Intent intentRequestStatus = new Intent(this, RequestStatusPage.class);
            startActivity(intentRequestStatus);
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

    }
}
