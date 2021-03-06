package com.example.nikhil.finalproject;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class RequestStatusPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonRefresh, buttonCloseRequest, buttonReturnHome, buttonExtendRequest, buttonPm ;
    TextView textViewPeopleNotified, textViewPeopleAccepted,textViewPeopleDonatedBefore;
    String recipientInfo, neededBType;
    int countNotified, countAccept, countDonateBefore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status_page);

        buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonCloseRequest = findViewById(R.id.buttonCloseRequest);
        buttonReturnHome = findViewById(R.id.buttonReturnHome);
        buttonExtendRequest = findViewById(R.id.buttonExtendRequest);
        buttonPm = findViewById(R.id.buttonPm);
        textViewPeopleAccepted = findViewById(R.id.textViewPeopleAccepted);
        textViewPeopleDonatedBefore = findViewById(R.id.textViewPeopleDonatedBefore);
        textViewPeopleNotified = findViewById(R.id.textViewPeopleNotified);

        buttonRefresh.setOnClickListener(this);
        buttonCloseRequest.setOnClickListener(this);
        buttonReturnHome.setOnClickListener(this);
        buttonExtendRequest.setOnClickListener(this);
        buttonPm.setOnClickListener(this);

        recipientInfo =  getIntent().getStringExtra("Recipient ID");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        //find out what blood type this request needs
        database.getReference("Recipient").child(recipientInfo)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Recipient thisRecipient = dataSnapshot.getValue(Recipient.class);
                        neededBType = (String) thisRecipient.getBtype();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        final DatabaseReference myRefU = database.getReference("User");
        //find out how many have been notified
        myRefU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    User countUser = snapshot.getValue(User.class);
                    String userBType = (String) countUser.getBtype();
                    if(userBType.equals(neededBType)){
                        countNotified+=1;
                    }
                }
                textViewPeopleNotified.setText(Integer.toString(countNotified));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


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
        if (v == buttonCloseRequest){
            Intent intentCloseRequest = new Intent(this,CloseRequestPage.class);
            intentCloseRequest.putExtra("Recipient ID", recipientInfo);
            //Toast.makeText(this,recipientInfo,Toast.LENGTH_LONG).show();
            startActivity(intentCloseRequest);

        }else if (v == buttonExtendRequest){
            Intent intentExtend = new Intent(this,ExtendRequestPage.class);
            intentExtend.putExtra("Recipient ID",recipientInfo);
            //Toast.makeText(this,recipientInfo,Toast.LENGTH_LONG).show();
            startActivity(intentExtend);

        }else if (v == buttonRefresh){
            Intent intentRefresh = new Intent(RequestStatusPage.this,RequestStatusPage.class);
            intentRefresh.putExtra("Recipient ID",recipientInfo);
            startActivity(intentRefresh);


        }else if (v == buttonReturnHome){
            Intent intentHome = new Intent(this, HomePage.class);
            startActivity(intentHome);

        }else if (v == buttonPm){
            Intent intentPm = new Intent(this,DonorMessagePage.class );
            //supposed to send information to pm page, but for now, dont need to
            intentPm.putExtra("Recipient ID", recipientInfo);
            startActivity(intentPm);

        }

    }
}
