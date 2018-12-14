package com.example.nikhil.finalproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NewRequestPage extends AppCompatActivity implements View.OnClickListener {
    EditText editTextfname ,editTextlname, editTextcomment, editTextAge;
    double age;
    String bloodTypeSelected,locationSelected;
    Button buttonSubmit;
    Spinner spinnerBloodType,spinnerLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request_page);

        editTextfname=findViewById(R.id.editfname);
        editTextlname=findViewById(R.id.editlname);
        editTextcomment=findViewById(R.id.editstory);
        editTextAge=findViewById(R.id.editage);

        spinnerBloodType = (Spinner) findViewById(R.id.spinnerBloodType);
        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<String>(NewRequestPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bloodType));
        bloodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodType.setAdapter(bloodTypeAdapter);

        spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(NewRequestPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.location));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(locationAdapter);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);



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
            Toast.makeText(this, "You are here already!", Toast.LENGTH_SHORT).show();
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

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Recipient");

        String fname = editTextfname.getText().toString();
        String lname = editTextlname.getText().toString();
        String story = editTextcomment.getText().toString();
        age = Double.parseDouble(editTextAge.getText().toString());

        int checkBloodSelected,checkLocationSelected;

        if (v == buttonSubmit){
            checkBloodSelected = spinnerBloodType.getSelectedItemPosition();
            checkLocationSelected = spinnerLocation.getSelectedItemPosition();
            if(checkBloodSelected ==0){
                Toast.makeText(this,"Please select blood type",Toast.LENGTH_LONG).show();

            }else if(checkLocationSelected ==0){
                Toast.makeText(this,"Please select hospital location",Toast.LENGTH_LONG).show();
            }else{
                bloodTypeSelected = spinnerBloodType.getSelectedItem().toString();
                locationSelected = spinnerLocation.getSelectedItem().toString();

                Calendar c = Calendar.getInstance();
                int day, month, year;
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DATE);

                // Create a location in the database for the new request
                DatabaseReference newRef = myRef.push();
                // Create the new request object
                Recipient newRequest = new Recipient(fname,lname,bloodTypeSelected,
                        locationSelected,story,age,true,false,"",newRef.getKey());
                newRequest.setExpiredDate(day,month,year);
                // Write the new request to the database
                newRef.setValue(newRequest);

                Intent intentRequestConfirmation = new Intent(this,RequestConfirmPage.class);
                startActivity(intentRequestConfirmation);
            }


        }

    }
}
