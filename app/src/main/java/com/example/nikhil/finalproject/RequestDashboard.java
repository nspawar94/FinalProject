package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestDashboard extends Activity implements View.OnClickListener {


    private ArrayList<Recipient> recipients;
    private RecyclerViewAdapter recyclerViewAdapter;
    private FirebaseAuth mAuth;
    TextView textViewAcptDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_dashboard);
        recipients = new ArrayList<>();
        initRecyclerView();
        getRecipients();
        getAcceptDetail();
        mAuth = FirebaseAuth.getInstance();

        textViewAcptDetail = findViewById(R.id.textViewAcptDetail);



    }

    private void getAcceptDetail(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference acceptDetailmyRef = database.getReference("Recipient");

        String showAcceptEmail = mAuth.getCurrentUser().getEmail();
        Boolean showAcceptisOpen = Boolean.TRUE;
        Boolean showAcceptisAccept = Boolean.TRUE;

        acceptDetailmyRef.orderByChild("donorEmail").equalTo(showAcceptEmail).orderByChild("isOpen").equalTo(showAcceptisOpen).orderByChild("isAccepted").equalTo(showAcceptisAccept).limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String showacceptKey = dataSnapshot.getKey();

                Recipient showacceptRecipient = dataSnapshot.getValue(Recipient.class);
                textViewAcptDetail.setText("Location: " + showacceptRecipient.location + "\nPatient Name: " + showacceptRecipient.fname);
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
    private void getRecipients() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference recipientsmyRef = database.getReference("Recipient");

        // Read from the database
        recipientsmyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               for(DataSnapshot child : dataSnapshot.getChildren()){
                   Recipient recipient = child.getValue(Recipient.class);
                   recipients.add(recipient);
               }
               recyclerViewAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });



    }


    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(recipients, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
