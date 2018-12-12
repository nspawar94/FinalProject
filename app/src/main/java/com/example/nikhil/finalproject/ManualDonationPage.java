package com.example.nikhil.finalproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManualDonationPage extends Activity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

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

        getMenuInflater().inflate(R.menu.dropdown, menu);
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
       // Date createdDate;

        int checkLocationSelected = spinnerLocation.getSelectedItemPosition();

        if (view == buttonSubmit){
            if (checkLocationSelected == 0) {
                Toast.makeText(this,"Please choose location",Toast.LENGTH_LONG).show();
            }else{

                String locationSelected = spinnerLocation.getSelectedItem().toString();
                String donorEmail = mAuth.getCurrentUser().getEmail();
                Donor newDonation = new Donor(locationSelected, "General", donorEmail);
                myRef.push().setValue(newDonation);

                Intent intentProfile = new Intent(this,DonorProfilePage.class);
                startActivity(intentProfile);
            }



        }else if(view == buttonClear){
            Intent intentRefresh = new Intent(this,DonorProfilePage.class);
            startActivity(intentRefresh);
        }else if(view == buttonShowDate){
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(ManualDonationPage.this,ManualDonationPage.this,year, month,day);
            datePickerDialog.show();
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE,dayFinal);

    }
}
