package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class RequestDashboard extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Recipient> recipients;
    private RecyclerViewAdapter recyclerViewAdapter;
    private FirebaseAuth mAuth;
    TextView textViewAcptDetail;
    String userBlood, cancelAccept;
    //Button buttonCancel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_dashboard);
        mAuth = FirebaseAuth.getInstance();

        //buttonCancel = findViewById(R.id.buttonCancel);
        //buttonCancel.setOnClickListener(this);


        recipients = new ArrayList<>();
        initRecyclerView();
        textViewAcptDetail = findViewById(R.id.textViewAcptDetail);

        getUserBloodtype();
        //getRecipients();
        getAcceptDetail();




    }

   private void getAcceptDetail(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference acceptDetailmyRef = database.getReference("Recipient");

        final String showAcceptEmail = mAuth.getCurrentUser().getEmail();

        acceptDetailmyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    Recipient showAcceptRecipient = child.getValue(Recipient.class);

                    if (showAcceptRecipient.getIsOpen()==true&&showAcceptRecipient.getIsAccepted()==true&&showAcceptRecipient.getDonorEmail().equals(showAcceptEmail)){
                        String cancelAccept = showAcceptRecipient.getRecipientID();

                        textViewAcptDetail.setText("Location: " + showAcceptRecipient.location + "\nPatient Name: " + showAcceptRecipient.fname);
                    }

            }

            }
        });

    }

    public void getUserBloodtype() {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usermyRef = database.getReference("User");
        String userDetail = mAuth.getCurrentUser().getEmail();
        String userID, editKey;

        usermyRef.orderByChild("email").equalTo(userDetail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String editKey = dataSnapshot.getKey();

                DatabaseReference usermyRef2 = database.getReference("User").child(editKey);
                usermyRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User userBlood = dataSnapshot.getValue(User.class);
                        String thisUserBlood = userBlood.getBtype();

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference recipientsmyRef = database.getReference("Recipient");

                        // Read from the database
                        // Have to filter "isOpen = true"
                        recipientsmyRef.orderByChild("btype").equalTo(thisUserBlood).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                Recipient recipient = dataSnapshot.getValue(Recipient.class);
                                recipients.add(recipient);
                                recyclerViewAdapter.notifyDataSetChanged();

                /*old code >> for(DataSnapshot child : dataSnapshot.getChildren()){
            Recipient recipient = child.getValue(Recipient.class);
            recipients.add(recipient)*/
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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


    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(recipients, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        /*if (v == buttonCancel){
            Intent intentCancel = new Intent(this,CancelPage.class);
            intentCancel.putExtra("Recipient ID", cancelAccept);
            startActivity(intentCancel);



        }*/
    }
}
