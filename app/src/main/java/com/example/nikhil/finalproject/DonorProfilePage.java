package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DonorProfilePage extends Activity implements View.OnClickListener{

   Button buttonAddNewDonation, switchDonationType, buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile_page);

        buttonAddNewDonation = findViewById(R.id.buttonAddNewDonation);
        switchDonationType = findViewById(R.id.switchDonationType);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonAddNewDonation.setOnClickListener(this);
        switchDonationType.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
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
            Intent intentRequestStatus = new Intent(this, RequestStatusPage.class);
            startActivity(intentRequestStatus);
        } else if (item.getItemId()==R.id.menuDonorMessage) {
            Intent intentDonorMessage = new Intent(this, DonorMessagePage.class);
            startActivity(intentDonorMessage);
        } else if (item.getItemId()==R.id.menuDonorProfile) {
            Toast.makeText(this, "You are here already!", Toast.LENGTH_SHORT).show();
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

        if (v == buttonAddNewDonation) {
            Intent intentManualDonation = new Intent(this, ManualDonationPage.class);
            startActivity(intentManualDonation);
        }

    }
}
