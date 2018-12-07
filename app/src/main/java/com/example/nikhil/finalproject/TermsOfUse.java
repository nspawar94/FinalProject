package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TermsOfUse extends Activity implements View.OnClickListener{

    Button buttonDisagree, buttonAgree;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);

        buttonDisagree = findViewById(R.id.buttonDisagree);
        buttonAgree = findViewById(R.id.buttonAgree);

        buttonDisagree.setOnClickListener(this);
        buttonAgree.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v == buttonAgree) {
            Intent intentDashboard = new Intent(this, RequestDashboard.class);
            startActivity(intentDashboard);

        } else if(v == buttonDisagree) {
            Intent intentMain = new Intent (this,MainActivity.class);
            startActivity(intentMain);
        }

    }
}
