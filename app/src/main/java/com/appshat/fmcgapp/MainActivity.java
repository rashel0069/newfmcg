package com.appshat.fmcgapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Database;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    ImageButton locationbtn;
    TextView registration;
    TextInputEditText userMobile, userPassword;
    Button loginButton;

    UserDao userDBdao;
    Database userDB;

    String mobile,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        locationbtn = findViewById( R.id.currentlocation_btn );

        userMobile = findViewById( R.id.phoneNumber_id );
        userPassword = findViewById( R.id.password_Id );
        registration = findViewById( R.id.registrationTV_id );

        //database
        userDB = Room.databaseBuilder( this, Database.class,"users" ).allowMainThreadQueries().build();
        userDBdao = userDB.getUserDao();


        //User Registration
        registration.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = userMobile.getText().toString().trim();
                password = userPassword.getText().toString().trim();

                if (mobile != null && password != null){
                    UserEntity userEntity = new UserEntity( mobile,password );
                    userDBdao.insert( userEntity );
                    Toast.makeText( MainActivity.this, "Registraton Done", Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText( MainActivity.this, "Mobile and Password field is empty", Toast.LENGTH_SHORT ).show();
                }

            }
        } );

        //login button
        loginButton = findViewById( R.id.login_btn );
        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mobile = userMobile.getText().toString().trim();
                 password = userPassword.getText().toString().trim();

                UserEntity userEntity = userDBdao.getUserEntity( mobile,password );
                if (userEntity != null){
                    // Fragment pass
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText( MainActivity.this, "Login Successfuly", Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText( MainActivity.this, "Error", Toast.LENGTH_SHORT ).show();
                }

            }
        } );



        locationbtn.setOnClickListener( v -> {
            Intent intent = new Intent(getApplicationContext(),MapActivity.class);
            startActivity( intent );
        } );
    }

}