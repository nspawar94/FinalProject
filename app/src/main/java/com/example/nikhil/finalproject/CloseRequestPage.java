package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CloseRequestPage extends AppCompatActivity implements View.OnClickListener{

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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefR = database.getReference("Recipient");

        if (v == buttonConfirmClose){
            String job ="123";//recipient ID
            //update open status to close, and if someone has donated, update that person's information and create new donation
            myRefR.orderByChild("UID").equalTo(job).addChildEventListener(new ChildEventListener() {
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String editKey = dataSnapshot.getKey();
                    Recipient findR = dataSnapshot.getValue(Recipient.class);
                    myRefR.child(editKey).child("isOpen").setValue(false);

                    //if a donor has accepted when requester close, this donation will be used to update donation and user
                    if(myRefR.child(editKey).child("isAccepted").equals(true)){
                        final DatabaseReference myRefD = database.getReference("Donor");
                        final DatabaseReference myRefU = database.getReference("User");

                        String location = findR.getLocation();
                        String donateType = "urgent";

                        DatabaseReference newRef = myRefD.push();
                        Donor urgentDonor = new Donor(location,donateType,findR.donorEmail,myRefD.getKey());
                        final int year,month,day;
                        day = findR.getAcceptDay();
                        month = findR.getAcceptMonth();
                        year = findR.getAcceptYear();
                        urgentDonor.setDonateDate(day,month,year);
                        newRef.setValue(urgentDonor);

                        //update user status
                        myRefU.orderByChild("email").equalTo(findR.donorEmail).addChildEventListener(new ChildEventListener() {
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                String editKey2 = dataSnapshot.getKey();
                                //set last donated date
                                myRefU.child(editKey2).child("lastDonateDay").setValue(day);
                                myRefU.child(editKey2).child("lastDonateMonth").setValue(month);
                                myRefU.child(editKey2).child("lastDonateYear").setValue(year);

                                //set next available donate day
                                int newMonth, newYear;
                                if(month>9){
                                    newMonth = month-9;
                                    newYear = year+1;
                                }else{
                                    newMonth = month+3;
                                    newYear = year;
                                }

                                myRefU.child(editKey2).child("nextDonateDay").setValue(day);
                                myRefU.child(editKey2).child("nextDonateMonth").setValue(newMonth);
                                myRefU.child(editKey2).child("nextDonateYear").setValue(newYear);
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
                    }

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

            Intent intentHomePage = new Intent(this,HomePage.class);
            startActivity(intentHomePage);
        } else if (v == buttonBackToRequest){
            Intent intentCloseRequest = new Intent(this,RequestStatusPage.class);
            startActivity(intentCloseRequest);
        }

    }
}
