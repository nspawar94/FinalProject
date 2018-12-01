package com.example.nikhil.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThankyouDonorPage extends Activity implements View.OnClickListener   {

    Button buttonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou_donor_page);

        buttonMain = findViewById(R.id.buttonMain);

        buttonMain.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

    }
}
