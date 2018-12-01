package com.example.nikhil.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreventAcceptPage extends Activity implements View.OnClickListener{

    Button buttonNo, buttonYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevent_accept_page);

        buttonNo = findViewById(R.id.buttonNo);
        buttonYes = findViewById(R.id.buttonYes);

        buttonNo.setOnClickListener(this);
        buttonYes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
