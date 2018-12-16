package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.Calendar;

public class CloseRequestPage extends AppCompatActivity implements View.OnClickListener{

    Button buttonBackToRequest, buttonConfirmClose;
    String recipientInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_request_page);

        buttonBackToRequest = findViewById(R.id.buttonBackToRequest);
        buttonConfirmClose = findViewById(R.id.buttonConfirmClose);

        buttonBackToRequest.setOnClickListener(this);
        buttonConfirmClose.setOnClickListener(this);
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefR = database.getReference("Recipient").child(recipientInfo);

        if (v == buttonConfirmClose){
            //update open status to close, and if someone has donated, update that person's information and create new donation
            myRefR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Recipient thisRecipient = dataSnapshot.getValue(Recipient.class);
                    thisRecipient.setIsOpen(false);
                    myRefR.setValue(thisRecipient);

                    if(thisRecipient.getIsAccepted()==true){
                        final DatabaseReference myRefD = database.getReference("Donor");
                        //create new donation related to this request
                        DatabaseReference newRef = myRefD.push();
                        Donor urgentDonor = new Donor(thisRecipient.getLocation(),"urgent",thisRecipient.getDonorEmail(),myRefD.getKey());

                        final int year,month,day;
                        day = thisRecipient.getAcceptDay();
                        month = thisRecipient.getAcceptMonth();
                        year = thisRecipient.getAcceptYear();

                        urgentDonor.setDonateDate(day,month,year);
                        newRef.setValue(urgentDonor);

                        //update user's next available donation date
                        String findUser = thisRecipient.getDonorEmail();
                        //using email as the way to link from new donation to this user
                        final DatabaseReference myRefU = database.getReference("User").child(findUser);

                        User updateUser = dataSnapshot.getValue(User.class);
                        updateUser.setLastDonate(day,month,year);
                        updateUser.setNextDonate(day,month,year);
                        myRefU.setValue(updateUser);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Toast.makeText(this,"Your request has been closed",Toast.LENGTH_LONG).show();
            Intent intentHomePage = new Intent(this,HomePage.class);
            startActivity(intentHomePage);
        } else if (v == buttonBackToRequest){
            Intent intentCloseRequest = new Intent(this,RequestStatusPage.class);
            //intentCloseRequest.putExtra("Recipient ID", recipientInfo);
            startActivity(intentCloseRequest);
        }

    }
}
