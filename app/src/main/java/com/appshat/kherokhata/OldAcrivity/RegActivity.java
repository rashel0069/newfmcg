package com.appshat.kherokhata.OldAcrivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.UserDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.UserEntity;
import com.appshat.kherokhata.Room.model.UserViewModel;

public class RegActivity extends AppCompatActivity {
    EditText reg_Mobile, reg_password, reg_ConfirmPassword;
    TextView reg_Tv1, reg_Tv2, reg_Tv3, reg_Tv4;
    TextView reg_now, registered_user;
    TextView confirmButton, loginPage;
    UserDao userDBdao;
    Databaseroom userDB;
    String mobile, password, confirmpass;
    UserViewModel userViewModel;

    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupui);
        reg_now = findViewById(R.id.cre_text);
        reg_Tv1 = findViewById(R.id.cre_text2);
        reg_Tv2 = findViewById(R.id.phone_tv);
        reg_Mobile = findViewById(R.id.reg_phoneNumber_id);
        reg_Tv3 = findViewById(R.id.password_tv);
        reg_Tv4 = findViewById(R.id.confirm_password_tv);
        reg_password = findViewById(R.id.reg_password_Id);
        reg_ConfirmPassword = findViewById(R.id.reg_confirm_password_Id);
        registered_user = findViewById(R.id.terms);
        confirmButton = findViewById(R.id.reg_button);
        loginPage = findViewById(R.id.reg_login);


        //Database
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userDB = Databaseroom.getDatabaseroomref(getApplication());
        userDBdao = userDB.getUserDao();

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));

            context = Localhelper.setLocale(RegActivity.this, "en");
            resources = context.getResources();
            reg_now.setText(resources.getString(R.string.create_account));
            reg_Tv1.setText(resources.getString(R.string.enter_your_cr));
            reg_Tv2.setText(resources.getString(R.string.phone_number));
            reg_Mobile.setHint(resources.getString(R.string.mobile));
            reg_password.setHint(resources.getString(R.string.password));
            reg_Tv3.setText(resources.getString(R.string.password));
            reg_Tv4.setText(resources.getString(R.string.confirm_password));
            reg_ConfirmPassword.setHint(resources.getString(R.string.confirm_password_again));
            registered_user.setText(resources.getString(R.string.terms));
            confirmButton.setText(resources.getString(R.string.sign_up));
            loginPage.setText(resources.getString(R.string.signin));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(RegActivity.this, "bn");
            resources = context.getResources();
            reg_now.setText(resources.getString(R.string.create_account));
            reg_Tv1.setText(resources.getString(R.string.enter_your_cr));
            reg_Tv2.setText(resources.getString(R.string.phone_number));
            reg_Mobile.setHint(resources.getString(R.string.mobile));
            reg_password.setHint(resources.getString(R.string.password));
            reg_Tv3.setText(resources.getString(R.string.password));
            reg_Tv4.setText(resources.getString(R.string.confirm_password));
            reg_ConfirmPassword.setHint(resources.getString(R.string.confirm_password_again));
            registered_user.setText(resources.getString(R.string.terms));
            confirmButton.setText(resources.getString(R.string.sign_up));
            loginPage.setText(resources.getString(R.string.signin));

        }
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