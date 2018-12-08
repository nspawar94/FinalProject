package com.example.nikhil.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSignIn,buttonSignUp ;
    private FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignUp = findViewById(R.id.buttonSignup);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);

        editTextEmail = findViewById(R.id.editTextEMail);
        editTextPassword = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonSignUp){
            Intent intentSignUp = new Intent(MainActivity.this,CreateAccount.class);
            startActivity(intentSignUp);

    }else if(v == buttonSignIn){
            {

                mAuth.signInWithEmailAndPassword(editTextEmail.getText().toString(), editTextPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Login Successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                    Intent intentSignIn = new Intent(MainActivity.this,HomePage.class);
                                    startActivity(intentSignIn);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Access Denied", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }

        }




}}
