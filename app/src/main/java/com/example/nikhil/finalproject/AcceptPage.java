package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AcceptPage extends AppCompatActivity implements View.OnClickListener{

    Button buttonYes, buttonNo;
    private FirebaseAuth mAuth;
    String recipientinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_page);

        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        buttonNo.setOnClickListener(this);
        buttonYes.setOnClickListener(this);

        recipientinfo =  getIntent().getStringExtra("text_value");
    }


    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (view == buttonNo) {
            Intent intentRequestDashboard = new Intent(this, RequestDashboard.class);
            startActivity(intentRequestDashboard);

        } else if (view == buttonYes){


            final FirebaseUser user = mAuth.getCurrentUser();// donor
            //Recipient r = new Recipient(get from recycler view)// recipient
            //r.setAccepted(true);
            //r.setDonorEmail(user.getEmail());

            final DatabaseReference myRef = database.getReference("Recipient");
            final DatabaseReference myRef2 = database.getReference("Donor");
            String job ="123";//recipient ID
            myRef.orderByChild("UID").equalTo(job).addChildEventListener(new ChildEventListener() {
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String editDonorEmail = user.getEmail();
                    String editKey = dataSnapshot.getKey();
                    Recipient findR = dataSnapshot.getValue(Recipient.class);
                    myRef.child(editKey).child("donorEmail").setValue(editDonorEmail);


                    String location = findR.getLocation();
                    String donateType = "urgent";

                    Donor urgentDonor = new Donor(location,donateType,editDonorEmail);
                    myRef2.push().setValue(urgentDonor);
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
            Intent intentRequestDashboard = new Intent(this, RequestDashboard.class);
            startActivity(intentRequestDashboard);
        }

    }

}


