package com.appshat.fmcgapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton locationbtn;
    TextView registration;
    TextInputEditText userMobile, userPassword;
    Button loginButton;

    UserDao userDBdao;
    Databaseroom userDB;

    String mobile, password;
    @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationbtn = findViewById(R.id.currentlocation_btn);

        userMobile = findViewById(R.id.phoneNumber_id);
        userPassword = findViewById(R.id.password_Id);
        registration = findViewById(R.id.registrationTV_id);

        //database
        userDB = Room.databaseBuilder(this, Databaseroom.class, "users").allowMainThreadQueries().build();
        userDBdao = userDB.getUserDao();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
            mNotificationManager.createNotificationChannel(mChannel);

        }


        //User Registration
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = userMobile.getText().toString().trim();
                password = userPassword.getText().toString().trim();

                if (mobile != null && password != null) {
                    UserEntity userEntity = new UserEntity(mobile, password);
                    userDBdao.insert(userEntity);
                    Toast.makeText(MainActivity.this, "Registraton Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Mobile and Password field is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //login button
        loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = userMobile.getText().toString().trim();
                password = userPassword.getText().toString().trim();

                UserEntity userEntity = userDBdao.getUserEntity(mobile, password);
                if (userEntity != null) {
                    // Fragment pass
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, "Login Successfuly", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        locationbtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();

    }
}