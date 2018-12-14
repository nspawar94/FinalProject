package com.example.nikhil.finalproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class ManualDonationPage extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button buttonShowDate,buttonClear, buttonSubmit;
    String location, donateType;
    Spinner spinnerLocation;
    private FirebaseAuth mAuth;
    TextView textViewShowDay;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_donation_page);
        mAuth = FirebaseAuth.getInstance();

        buttonShowDate = findViewById(R.id.buttonDatePicker);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonClear.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        buttonShowDate.setOnClickListener(this);

        textViewShowDay = findViewById(R.id.textViewShowDate);

        spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(ManualDonationPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.location));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(locationAdapter);
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
            Toast.makeText(this, "You are here already!", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId()==R.id.menuLogOut) {
            Intent intentLogout = new Intent(this, MainActivity.class);
            startActivity(intentLogout);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Donor");
        final DatabaseReference myRef2 = database.getReference("User");


        int checkLocationSelected = spinnerLocation.getSelectedItemPosition();

        if(view == buttonShowDate) {
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(ManualDonationPage.this, ManualDonationPage.this, year, month, day);
            datePickerDialog.show();

        } else if(view == buttonClear){
            Intent intentRefresh = new Intent(this,DonorProfilePage.class);
            startActivity(intentRefresh);

        } else if (view == buttonSubmit){
            if (checkLocationSelected == 0) {
                Toast.makeText(this,"Please choose location",Toast.LENGTH_LONG).show();
            }else if(yearFinal<1){
                Toast.makeText(this,"Please choose donation date",Toast.LENGTH_LONG).show();
            } else {
                String locationSelected = spinnerLocation.getSelectedItem().toString();
                String donorEmail = mAuth.getCurrentUser().getEmail();
                Donor newDonation = new Donor(locationSelected, "General", donorEmail);
                newDonation.setDonateDate(dayFinal,monthFinal,yearFinal);
                myRef.push().setValue(newDonation);

                //update last donate date and new donate date of this user
                myRef2.orderByChild("email").equalTo(donorEmail).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String editKey = dataSnapshot.getKey();

                        //update last donate date
                        myRef2.child(editKey).child("lastDonateDay").setValue(dayFinal);
                        myRef2.child(editKey).child("lastDonateMonth").setValue(monthFinal);
                        myRef2.child(editKey).child("lastDonateYear").setValue(yearFinal);

                        //update new donate date
                        if(monthFinal>9){
                            monthFinal = monthFinal-9;
                            yearFinal = yearFinal+1;
                        }else{
                            monthFinal = monthFinal+3;
                        }

                        myRef2.child(editKey).child("nextDonateDay").setValue(dayFinal);
                        myRef2.child(editKey).child("nextDonateMonth").setValue(monthFinal);
                        myRef2.child(editKey).child("nextDonateYear").setValue(yearFinal);

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
                Intent intentProfile = new Intent(this,DonorProfilePage.class);
                startActivity(intentProfile);


        }

    }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE,dayFinal);
        c.set(Calendar.MONTH,monthFinal);
        c.set(Calendar.YEAR,yearFinal);
        String chosenDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textViewShowDay.setText(chosenDate);

    }
}
