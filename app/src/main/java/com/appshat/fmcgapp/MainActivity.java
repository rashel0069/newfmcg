package com.appshat.fmcgapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;
import com.appshat.fmcgapp.Room.model.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView registration,welcome,languagetv,regTV,passforgot;
    TextInputEditText userMobile, userPassword;
    TextInputLayout f1TV,f2TV;
    Button loginButton;
    ImageButton languageImg;
    boolean lang_selected = true;
    Context context;
    Resources resources;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    UserDao userDBdao;
    Databaseroom userDB;
    String mobile, password;
    UserViewModel userViewModel;

    @Override
    protected void onStart() {
        super.onStart();

        Dexter.withContext( this )
                .withPermissions( Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener( new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        } ).check();

        if (mobile == null && password == null) {
            SharedPreferences preferences = getSharedPreferences( MY_PREFS_NAME, MODE_PRIVATE );
            mobile = preferences.getString( "mobilenumber", "No Number Found" );
            password = preferences.getString( "password", "****" );
            if (mobile!=null && password!=null){
                UserAuthenTication();
            }

        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(api = Build.VERSION_CODES.O)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passforgot = findViewById( R.id.forgotepassword_id );
        userMobile = findViewById(R.id.phoneNumber_id);
        userPassword = findViewById(R.id.password_Id);
        f1TV=findViewById(R.id.f1);
        f2TV=findViewById(R.id.f2);
        registration = findViewById(R.id.registrationTV_id);
        welcome = findViewById(R.id.welcomeTV_id);
        languagetv=findViewById(R.id.languageTV_id);
        languageImg =findViewById(R.id.languageBtn_Id);
        regTV=findViewById(R.id.rtextTV_id);
        loginButton=findViewById(R.id.login_btn);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userDB = Databaseroom.getDatabaseroomref(getApplication());
        userDBdao = userDB.getUserDao();


        if (Helper.getEnglish()){
            Helper.setEnglish(true);
            Helper.setBangla(false);
        }else {
            Helper.setEnglish(false);
            Helper.setBangla(true);
        }


        Log.e("Bangla", String.valueOf(Helper.getBangla()));

        //language setter
        if (!Helper.getBangla()){
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(MainActivity.this,"en");
            resources = context.getResources();
            languagetv.setText(resources.getString(R.string.selector));
            welcome.setText(resources.getString(R.string.intro));
            loginButton.setText(resources.getString(R.string.login));
            regTV.setText(resources.getString(R.string.registration2));
            f1TV.setHint(resources.getString(R.string.hint1));
            f2TV.setHint(resources.getString(R.string.hint2));
            registration.setText(resources.getString(R.string.registration));
            passforgot.setText( resources.getText( R.string.forgot_password ) );

        }else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(MainActivity.this,"bn");
            resources = context.getResources();
            languagetv.setText(resources.getString(R.string.selector));
            welcome.setText(resources.getString(R.string.intro));
            loginButton.setText(resources.getString(R.string.login));
            regTV.setText(resources.getString(R.string.registration2));
            registration.setText(resources.getString(R.string.registration));
            f1TV.setHint(resources.getString(R.string.hint1));
            f2TV.setHint(resources.getString(R.string.hint2));
            passforgot.setText( resources.getText( R.string.forgot_password ) );
        }

       languageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[]language ={"English","Bangla"};
                int checkeditem;
                if (Helper.getBangla()){
                    checkeditem = 1;
                }else {
                    checkeditem =0;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        languagetv.setText(language[which]);
                        if (language[which].equals("English")){
                            Helper.setEnglish(true);
                            Helper.setBangla(false);
                            context = Localhelper.setLocale(MainActivity.this,"en");
                            resources = context.getResources();
                            languagetv.setText(resources.getString(R.string.selector));
                            welcome.setText(resources.getString(R.string.intro));
                            loginButton.setText(resources.getString(R.string.login));
                            regTV.setText(resources.getString(R.string.registration2));
                            registration.setText(resources.getString(R.string.registration));
                            passforgot.setText( resources.getText( R.string.forgot_password ) );

                        }

                        if (language[which].equals("Bangla")){
                            Helper.setBangla(true);
                            Helper.setEnglish(false);
                            context = Localhelper.setLocale(MainActivity.this,"bn");
                            resources = context.getResources();
                            languagetv.setText(resources.getString(R.string.selector));
                            welcome.setText(resources.getString(R.string.intro));
                            loginButton.setText(resources.getString(R.string.login));
                            regTV.setText(resources.getString(R.string.registration2));
                            registration.setText(resources.getString(R.string.registration));
                            passforgot.setText( resources.getText( R.string.forgot_password ) );
                        }
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });


        //sharedpref
        SharedPreferences.Editor editor = getSharedPreferences( MY_PREFS_NAME,MODE_PRIVATE ).edit();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);

        }


        //User Registration
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //login button
        loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (!TextUtils.isEmpty(userMobile.getText().toString().trim() ) && !TextUtils.isEmpty( userPassword.getText().toString().trim() )
                       && userMobile.getText().toString().trim().length() == 11){
                   mobile = userMobile.getText().toString().trim();
                   password = userPassword.getText().toString().trim();
                   editor.putString( "mobilenumber",mobile );
                   editor.putString( "password",password );
                   editor.apply();
                   UserAuthenTication();
               }else if (!TextUtils.isEmpty(userMobile.getText().toString().trim() ) && userMobile.getText().toString().trim().length() == 11){
                   userPassword.setError( "Enter Password" );
               }else if (!TextUtils.isEmpty( userPassword.getText().toString().trim() )){
                   userMobile.setError( "Enter Mobile Number" );
               }else{
                   userMobile.setError( "Enter Mobile Number" );
                   userPassword.setError( "Enter Password");
               }

            }
        });

    }

    private void UserAuthenTication() {
        UserEntity userEntity = new UserEntity("Mobile", "Password");
        UserEntity userData = new UserEntity(mobile, password);

        try {
            userEntity = new GetToUser(userDBdao).execute(userData).get();
        } catch (Exception e) {
            //Toast.makeText(MainActivity.this, "" + e, Toast.LENGTH_SHORT).show();
        }


        if (userEntity != null) {
            // Fragment pass
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(MainActivity.this, "User Not Register", Toast.LENGTH_SHORT).show();
        }
    }

    public class GetToUser extends AsyncTask<UserEntity, Void, UserEntity> {
        private final UserDao userDao;

        public GetToUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected UserEntity doInBackground(UserEntity... userEntities) {
            UserEntity userEntity = userDao.getUserEntity(userEntities[0].getMobile(),userEntities[0].getPassword());
            return userEntity;
        }

    }

}