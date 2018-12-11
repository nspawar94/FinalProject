package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateAccount extends Activity implements View.OnClickListener {
    Button buttonNext;
    EditText editTextName, editTextLastName, editTextAge, editTextEMail, editTextPassword,editTextConfirmPassword;
    Spinner spinnerGender, spinnerBloodType;
    String [] gender, bloodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEMail = findViewById(R.id.editTextEMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        spinnerGender = findViewById(R.id.spinnerGender);
        gender = new String[]{"male", "female", "other"};

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerBloodType = findViewById(R.id.spinnerBloodType);
        bloodType = new String[]{"A-","A+","B-","B+","AB-","AB+","O-","O+"};
        ArrayAdapter bloodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerBloodType);
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(bloodAdapter);





        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
            if(v == buttonNext){
                Intent intentNext = new Intent(this,TermsOfUse.class);
                startActivity(intentNext);
            }}

    }
