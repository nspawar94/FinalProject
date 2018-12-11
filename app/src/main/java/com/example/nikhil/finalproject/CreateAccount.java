package com.example.nikhil.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends Activity implements View.OnClickListener{
    Button buttonNext;
    EditText editTextName, editTextLastName, editTextAge, editTextEMail, editTextPassword,editTextConfirmPassword;
    Spinner spinnerGender, spinnerBloodType;
    String [] gender, bloodType;
    String genderSelected, bloodTypeSelected;
    private FirebaseAuth mAuth;
// this is a comment, it is very good


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
        mAuth = FirebaseAuth.getInstance();

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(CreateAccount.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerBloodType = (Spinner) findViewById(R.id.spinnerBloodType);
        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(CreateAccount.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.bloodType));
        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodType.setAdapter(bloodAdapter);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        String email = editTextEMail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        final String fname = editTextName.getText().toString();
        final String lname = editTextLastName.getText().toString();
        final double age = Double.parseDouble(editTextAge.getText().toString());
        genderSelected = spinnerGender.getSelectedItem().toString();
        bloodTypeSelected = spinnerBloodType.getSelectedItem().toString();

        if(v == buttonNext){
            if(password!=confirmPassword){
                Toast.makeText(this,"Please confirm password again",Toast.LENGTH_LONG).show();
            }else{
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User newUser = new User(fname,lname,genderSelected,bloodTypeSelected,age);
                                    myRef.push().setValue(newUser);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intentNext = new Intent(CreateAccount.this, TermsOfUse.class);
                                    startActivity(intentNext);
                                } else {
                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
            }



    }

}
