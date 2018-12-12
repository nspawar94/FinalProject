package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
//hh
    @Override
    public void onClick(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        if(v == buttonAgree) {
            Intent intentDashboard = new Intent(this, HomePage.class);
            startActivity(intentDashboard);

        } else if(v == buttonDisagree) {
            //user.delete();
            Intent intentMain = new Intent (this,MainActivity.class);
            startActivity(intentMain);
        }

    }
}
