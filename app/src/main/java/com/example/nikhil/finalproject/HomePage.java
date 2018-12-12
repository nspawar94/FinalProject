package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

public class HomePage extends Activity implements View.OnClickListener{
    Button buttonNewRequest, buttonDonationHomepage, buttonCurrentRequest;
    TextView textViewRequestAccept;
    private ArrayList<Recipient> recipients;
    private HomePageRecycler recyclerViewAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recipients = new ArrayList<>();
        initRecyclerView();
        getRecipients();

        buttonNewRequest = findViewById(R.id.buttonNewRequest);
        buttonDonationHomepage = findViewById(R.id.buttonDonationHomePage);
        buttonCurrentRequest = findViewById(R.id.buttonCurrentRequest);

        buttonNewRequest.setOnClickListener(this);
        buttonDonationHomepage.setOnClickListener(this);
        buttonCurrentRequest.setOnClickListener(this);

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
        recyclerViewAdapter = new HomePageRecycler(recipients, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


//menu starts here

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.dropdown, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menuHome){
            Toast.makeText(this, "You are here already!", Toast.LENGTH_SHORT).show();
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
        if(v==buttonNewRequest){

            Intent i = new Intent(this,NewRequestPage.class);
            this.startActivity(i);
        }else if(v==buttonDonationHomepage){
            Intent i = new Intent(this,DonorProfilePage.class);
            this.startActivity(i);
        }else if(v==buttonCurrentRequest){
            Intent i = new Intent(this, RequestDashboard.class);
            this.startActivity(i);
        }
    }
}
