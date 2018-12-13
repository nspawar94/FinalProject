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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.YearMonth;
import java.util.Calendar;

public class ExtendRequestPage extends AppCompatActivity implements View.OnClickListener {

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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefR = database.getReference("Recipient");

        if (v == buttonCloseRequest){
            Intent intentClosePage = new Intent(this,CloseRequestPage.class);
            startActivity(intentClosePage);
        } else if (v == buttonExtendRequest){
            String job = "123456";//get request IDfrom page before

            myRefR.orderByChild("UID").equalTo(job).addChildEventListener(new ChildEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String editKey = dataSnapshot.getKey();
                    Calendar c = Calendar.getInstance();
                    int day, month, year;
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DATE);

                    YearMonth yearMonthObject = YearMonth.of(year,month);
                    int daysIntMonth = yearMonthObject.lengthOfMonth();

                    //set end date for this request
                    if(day>daysIntMonth-2){
                        day = day - daysIntMonth + 2;
                        if(month>11){
                            month = month-11;
                            year = year +1;
                        }else{
                            month = month+1;
                        }
                    }else{
                        day = day + 2;
                    }
                    myRefR.child(editKey).child("endDay").setValue(day);
                    myRefR.child(editKey).child("endMonth").setValue(month);
                    myRefR.child(editKey).child("endYear").setValue(year);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            Intent intentRequestStatus = new Intent(this,RequestStatusPage.class);
            startActivity(intentRequestStatus);
        }

    }
}
