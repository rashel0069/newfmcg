package com.appshat.kherokhata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.Room.DAO.UserDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.UserEntity;
import com.appshat.kherokhata.Room.model.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegActivity extends AppCompatActivity {
    TextInputEditText reg_Mobile, reg_password, reg_ConfirmPassword;
    TextInputLayout reg_Tv1, reg_Tv2, reg_Tv3;
    TextView reg_now, registered_user;
    Button confirmButton;
    TextView loginPage;
    UserDao userDBdao;
    Databaseroom userDB;
    String mobile, password, confirmpass;
    UserViewModel userViewModel;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        reg_now = findViewById(R.id.textView4);
        reg_Mobile = findViewById(R.id.reg_phoneNumber_id);
        reg_password = findViewById(R.id.reg_password_Id);
        reg_ConfirmPassword = findViewById(R.id.reg_confirm_password_Id);
        reg_Tv1 = findViewById(R.id.reg_f1);
        reg_Tv2 = findViewById(R.id.reg_f2);
        reg_Tv3 = findViewById(R.id.reg_f3);
        confirmButton = findViewById(R.id.reg_button);
        registered_user = findViewById(R.id.already_reg);
        loginPage = findViewById(R.id.reg_login);

//language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(RegActivity.this, "en");
            resources = context.getResources();
            reg_now.setText(resources.getString(R.string.registration_now));
            confirmButton.setText(resources.getString(R.string.confirm));
            registered_user.setText(resources.getString(R.string.already_to));
            reg_Tv1.setHint(resources.getString(R.string.hint1));
            reg_Tv2.setHint(resources.getString(R.string.hint2));
            reg_Tv3.setHint(resources.getString(R.string.hint3));
            loginPage.setText(resources.getString(R.string.login));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(RegActivity.this, "bn");
            resources = context.getResources();
            reg_now.setText(resources.getString(R.string.registration_now));
            confirmButton.setText(resources.getString(R.string.confirm));
            registered_user.setText(resources.getString(R.string.already_to));
            reg_Tv1.setHint(resources.getString(R.string.hint1));
            reg_Tv2.setHint(resources.getString(R.string.hint2));
            reg_Tv3.setHint(resources.getString(R.string.hint3));
            loginPage.setText(resources.getString(R.string.login));


        }


        //Database
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userDB = Databaseroom.getDatabaseroomref(getApplication());
        userDBdao = userDB.getUserDao();


        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = reg_Mobile.getText().toString().trim();
                password = reg_password.getText().toString().trim();
                confirmpass = reg_ConfirmPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(reg_Mobile.getText().toString().trim()) && !TextUtils.isEmpty(reg_password.getText().toString())
                        && !TextUtils.isEmpty(reg_ConfirmPassword.getText().toString())) {

                    if ((reg_Mobile.getText().toString().contains("016") || reg_Mobile.getText().toString().contains("017")
                            || reg_Mobile.getText().toString().contains("018") || reg_Mobile.getText().toString().contains("015")
                            || reg_Mobile.getText().toString().contains("014") || reg_Mobile.getText().toString().contains("013")
                            || reg_Mobile.getText().toString().contains("019")) && reg_Mobile.getText().toString().length() == 11) {
                        if (reg_password.getText().toString().contains(reg_ConfirmPassword.getText().toString())) {
                            if (mobile != null && password != null) {
                                UserEntity userEntity = new UserEntity(mobile, password);
                                userViewModel.insertUser(userEntity);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegActivity.this, "Mobile and Password field is empty", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password Not Match", Toast.LENGTH_SHORT).show();
                        }
                    } else if (!TextUtils.isEmpty(reg_Mobile.getText().toString().trim())
                            && TextUtils.isEmpty(reg_password.getText().toString())) {
                        reg_password.setError("Enter Password");
                    } else if (TextUtils.isEmpty(reg_Mobile.getText().toString().trim())
                            && !TextUtils.isEmpty(reg_password.getText().toString())) {
                        reg_Mobile.setError("Enter Mobile number");
                    } else {
                        reg_Mobile.setError("Enter Mobile number");
                    }
                } else {
                    Toast.makeText(RegActivity.this, "Mobile Number and Password required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}