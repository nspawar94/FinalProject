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
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class AcceptPage extends AppCompatActivity implements View.OnClickListener{

    Button buttonYes, buttonNo;
    private FirebaseAuth mAuth;
    String recipientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_page);

        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);

        buttonNo.setOnClickListener(this);
        buttonYes.setOnClickListener(this);

        recipientInfo =  getIntent().getStringExtra("Recipient ID");
    }


    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (view == buttonNo) {
            Intent intentRequestDashboard = new Intent(this, RequestDashboard.class);
            startActivity(intentRequestDashboard);

        } else if (view == buttonYes){
            final FirebaseUser user = mAuth.getCurrentUser();// donor

            final DatabaseReference myRefR = database.getReference("Recipient").child(recipientInfo);

            //create new donation related to this request, update recipient status, update user status
            myRefR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Recipient thisRecipient = dataSnapshot.getValue(Recipient.class);

                    String editKey = dataSnapshot.getKey();
                    int year,month,day;
                    Calendar c = Calendar.getInstance();

                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DATE);

                    thisRecipient.isAccepted = true;
                    thisRecipient.donorEmail = user.getEmail();
                    thisRecipient.acceptDay = day;
                    thisRecipient.acceptMonth = month;
                    thisRecipient.acceptYear = year;

                    myRefR.setValue(thisRecipient);
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


