
package com.appshat.kherokhata.OldAcrivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.UserDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.UserEntity;
import com.appshat.kherokhata.Room.model.UserViewModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    TextView registration, regTV, passforgot, loginTV, infoTV, phnTV, passTV, logTV;
    EditText userMobile, userPassword;
    TextView logintv, f1TV, f2TV;
    ImageButton languageImg;
    boolean lang_selected = true;
    Context context;
    Resources resources;
    UserDao userDBdao;
    Databaseroom userDB;
    String mobile, password;
    UserViewModel userViewModel;

    @Override
    protected void onStart() {
        super.onStart();

        Dexter.withContext(this)
                .withPermissions(Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        }).check();


        if (mobile == null && password == null) {
            SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            mobile = preferences.getString("mobilenumber", "No Number Found");
            password = preferences.getString("password", "****");
            if (mobile != null && password != null) {
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

        passforgot = findViewById(R.id.forgotepassword_id);
        phnTV = findViewById(R.id.phone_tv);
        userMobile = findViewById(R.id.phoneNumber_id);
        userPassword = findViewById(R.id.password_Id);
        passTV = findViewById(R.id.password_tv);
        registration = findViewById(R.id.registrationTV_id);
        loginTV = findViewById(R.id.login_btn);
        logTV = findViewById(R.id.loginTV_id);
        infoTV = findViewById(R.id.enter_text);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userDB = Databaseroom.getDatabaseroomref(getApplication());
        userDBdao = userDB.getUserDao();

        if (isPackageAvailable("com.bijoy.keyboard") || isPackageAvailable("ridmik.keyboard")
                || isPackageAvailable("com.jetbox.bnkeyboard") || isPackageAvailable("ridmik.keyboard.classic")) {
            //Do some.
        } else {
            final String appPackageName = "ridmik.keyboard";
            AlertDialog builder = new AlertDialog.Builder(this)
                    .setTitle("বাংলা কীবোর্ড")
                    .setIcon(R.drawable.ic_baseline_error_outline_24)
                    .setMessage("বাংলা কীবোর্ড পাওয়া যায়নি, দয়া করে গুগল প্লে স্টোর থেকে একটি বাংলা কীবোর্ড ডাউনলোড করুন...")
                    .setCancelable(false)
                    .setPositiveButton("Open", null)
                    .show();

            Button openPlay = builder.getButton(AlertDialog.BUTTON_POSITIVE);
            openPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        builder.cancel();
                    } catch (android.content.ActivityNotFoundException e) {
                    }
                }
            });
        }


        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));

            context = Localhelper.setLocale(MainActivity.this, "en");
            resources = context.getResources();
            infoTV.setText(resources.getString(R.string.enter_your_));
            loginTV.setText(resources.getString(R.string.login));
            logTV.setText(resources.getString(R.string.login));
            passTV.setText(resources.getString(R.string.password));
            userMobile.setHint(resources.getString(R.string.mobile));
            phnTV.setText(resources.getString(R.string.phone_number));
            userPassword.setHint(resources.getString(R.string.enterpassword));
            passforgot.setText(resources.getText(R.string.forgot_password));
            registration.setText(resources.getText(R.string.sign_up));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(MainActivity.this, "bn");
            resources = context.getResources();

            infoTV.setText(resources.getString(R.string.enter_your_));
            loginTV.setText(resources.getString(R.string.login));
            logTV.setText(resources.getString(R.string.login));
            userMobile.setHint(resources.getString(R.string.mobile));
            passTV.setText(resources.getString(R.string.password));
            userPassword.setHint(resources.getString(R.string.enterpassword));
            phnTV.setText(resources.getString(R.string.phone_number));
            passforgot.setText(resources.getText(R.string.forgot_password));
            registration.setText(resources.getText(R.string.sign_up));
        }
//        if (Helper.getEnglish()) {
//            Helper.setEnglish(true);
//          Helper.setBangla(false);
//       } else {
//            Helper.setEnglish(false);
//           Helper.setBangla(true);
//      }
//
//
//        Log.e("Bangla", String.valueOf(Helper.getBangla()));
//
//
////// language
//
//        //language setter
//        if (!Helper.getBangla()) {
//            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
//
//            context = Localhelper.setLocale(MainActivity.this, "en");
//            resources = context.getResources();
//
//            infoTV.setText(resources.getString(R.string.enter_your_));
//            loginTV.setText(resources.getString(R.string.login));
//            logTV.setText(resources.getString(R.string.login));
//            passTV.setText(resources.getString(R.string.password));
//            phnTV.setText(resources.getString(R.string.phone_number));
//            passforgot.setText(resources.getText(R.string.forgot_password));
//            registration.setText(resources.getText(R.string.sign_up));
//
//        } else {
//            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
//            context = Localhelper.setLocale(MainActivity.this, "bn");
//            resources = context.getResources();
//            infoTV.setText(resources.getString(R.string.enter_your_));
//            loginTV.setText(resources.getString(R.string.login));
//            logTV.setText(resources.getString(R.string.login));
//            passTV.setText(resources.getString(R.string.password));
//            phnTV.setText(resources.getString(R.string.phone_number));
//            passforgot.setText(resources.getText(R.string.forgot_password));
//            registration.setText(resources.getText(R.string.sign_up));
//
//        }
//
//        languageImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View v) { final String[] language = {"English", "Bangla"};
//              int checkeditem;
//               if (Helper.getBangla()) {
//                  checkeditem = 1;
//               } else {
//                   checkeditem = 0;
//               }
//
//               final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//               builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialog, int which) {
//                       languagetv.setText(language[which]);
//                       if (language[which].equals("English")) {
//                          Helper.setEnglish(true);
//                          Helper.setBangla(false);
//                           context = Localhelper.setLocale(MainActivity.this, "en");
//                      resources = context.getResources();
////                            loginTV.setText(R.string.log_in);
////                            infoTV.setText(R.string.enter_your_);
////                            loginTV.setText(resources.getString(R.string.login));
////                            phnTV.setText(resources.getString((R.string.phone_number)));
////                            passTV.setText(resources.getString(R.string.password));
////                            regTV.setText(resources.getString(R.string.registration2));
////                            registration.setText(resources.getString(R.string.registration));
////                            passforgot.setText(resources.getText(R.string.forgot_password));
//
//                        }
//                       if (language[which].equals("Bangla")) {
//                           Helper.setBangla(true);
//                            Helper.setEnglish(false);
//                          context = Localhelper.setLocale(MainActivity.this, "bn");
//                           resources = context.getResources();
////                            loginTV.setText(R.string.log_in);
////                            infoTV.setText(R.string.enter_your_);
////                            loginTV.setText(resources.getString(R.string.login));
////                            phnTV.setText(resources.getString((R.string.phone_number)));
// //                         passTV.setText(resources.getString(R.string.password));
//     //                    regTV.setText(resources.getString(R.string.registration2));
//         //                  registration.setText(resources.getString(R.string.registration));
//        //                   passforgot.setText(resources.getText(R.string.forgot_password));
//                      }
//                   }
//                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                   public void onClick(DialogInterface dialog, int which) {
//                       dialog.dismiss();
//                   }
//               });
//
//                builder.create().show();
//            }
//        });
//

        //sharedpref
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();


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
                Intent intent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //login button
        logintv = findViewById(R.id.login_btn);
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(userMobile.getText().toString().trim()) && !TextUtils.isEmpty(userPassword.getText().toString().trim())
                        && userMobile.getText().toString().trim().length() == 11) {
                    mobile = userMobile.getText().toString().trim();
                    password = userPassword.getText().toString().trim();
                    editor.putString("mobilenumber", mobile);
                    editor.putString("password", password);
                    editor.apply();
                    UserAuthenTication();
                } else if (!TextUtils.isEmpty(userMobile.getText().toString().trim()) && userMobile.getText().toString().trim().length() == 11) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.isEmpty(userPassword.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter Mobile Number & Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean isPackageAvailable(String name) {
        boolean available = true;

        try {
            getPackageManager().getPackageInfo(name, 0);
        } catch (PackageManager.NameNotFoundException e) {
            available = false;
        }
        return available;
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
            //Toast.makeText(MainActivity.this, "User Not Register", Toast.LENGTH_SHORT).show();
        }
    }

    public class GetToUser extends AsyncTask<UserEntity, Void, UserEntity> {
        private final UserDao userDao;

        public GetToUser(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected UserEntity doInBackground(UserEntity... userEntities) {
            UserEntity userEntity = userDao.getUserEntity(userEntities[0].getMobile(), userEntities[0].getPassword());
            return userEntity;
        }

    }

}