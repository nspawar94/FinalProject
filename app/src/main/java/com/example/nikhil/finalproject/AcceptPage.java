package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class AcceptPage extends Activity implements View.OnClickListener{

    Button buttonYes, buttonNo;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_page);

        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        buttonNo.setOnClickListener(this);
        buttonYes.setOnClickListener(this);
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


                   Date createdDate = Calendar.getInstance().getTime();
                    String location = findR.getLocation();
                    String donateType = "urgent";
                    Donor urgentDonor = new Donor(createdDate,location,donateType);
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


