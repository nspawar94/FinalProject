package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;

public class ExtendRequestPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonExtendRequest, buttonReturnToRequestStatus;
    String recipientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_request_page);

        buttonExtendRequest = findViewById(R.id.buttonExtendRequest);
        //buttonCloseRequest = findViewById(R.id.buttonCloseRequest);
        buttonReturnToRequestStatus = findViewById(R.id.buttonReturnToRequestStatus);

        buttonExtendRequest.setOnClickListener(this);
        //buttonCloseRequest.setOnClickListener(this);
        buttonReturnToRequestStatus.setOnClickListener(this);
        recipientInfo =  getIntent().getStringExtra("Recipient ID");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefR = database.getReference("Recipient").child(recipientInfo);

        /*if (v == buttonCloseRequest) {
            Intent intentClosePage = new Intent(this, CloseRequestPage.class);
            intentClosePage.putExtra("Recipient ID", recipientInfo);
            Toast.makeText(this, recipientInfo, Toast.LENGTH_LONG).show();
            startActivity(intentClosePage);*/

         if (v == buttonReturnToRequestStatus) {
            Intent intentReturn = new Intent(this, RequestStatusPage.class);
            intentReturn.putExtra("Recipient ID", recipientInfo);
            startActivity(intentReturn);

        } else if (v == buttonExtendRequest){

            myRefR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Recipient thisRecipient = dataSnapshot.getValue(Recipient.class);

                    int year,month,day;
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DATE);
                    thisRecipient.setExpiredDate(day,month,year);
                    myRefR.setValue(thisRecipient);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




            Intent intentRequestStatus = new Intent(this,RequestStatusPage.class);
            //Toast.makeText(this,recipientInfo,Toast.LENGTH_LONG).show();
            intentRequestStatus.putExtra("Recipient ID",recipientInfo);
            startActivity(intentRequestStatus);
        }

    }
}
