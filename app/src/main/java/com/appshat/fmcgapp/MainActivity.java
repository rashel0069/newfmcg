package com.appshat.fmcgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    ImageButton locationbtn;
    TextInputEditText userMobile, userPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        locationbtn = findViewById( R.id.currentlocation_btn );

        locationbtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(),MapActivity.class);
            startActivity( intent );
        } );
    }

}