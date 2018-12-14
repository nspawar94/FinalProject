package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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

    Button buttonExtendRequest, buttonCloseRequest;
    String recipientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend_request_page);

        buttonExtendRequest = findViewById(R.id.buttonExtendRequest);
        buttonCloseRequest = findViewById(R.id.buttonCloseRequest);

        buttonExtendRequest.setOnClickListener(this);
        buttonCloseRequest.setOnClickListener(this);
        recipientInfo =  getIntent().getStringExtra("Recipient ID");
    }

    @Override
    public void onClick(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefR = database.getReference("Recipient").child(recipientInfo);

        if (v == buttonCloseRequest){
            Intent intentClosePage = new Intent(this,CloseRequestPage.class);
            intentClosePage.putExtra("Recipient ID", recipientInfo);
            Toast.makeText(this,recipientInfo,Toast.LENGTH_LONG).show();
            startActivity(intentClosePage);
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
            Toast.makeText(this,recipientInfo,Toast.LENGTH_LONG).show();
            intentRequestStatus.putExtra("Recipient ID",recipientInfo);
            startActivity(intentRequestStatus);
        }

    }
}
