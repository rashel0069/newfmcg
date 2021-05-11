package com.appshat.kherokhata.NewUIActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.OldAcrivity.MainActivity;
import com.appshat.kherokhata.OldAcrivity.SplashScreen;
import com.appshat.kherokhata.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class Onboarding_Activity extends AppCompatActivity {


    private MaterialButton buttonOnboardingAction;

    TextView introtv1, introtv2, stv, languagetv;
    ImageButton languageImg;
    boolean lang_selected = true;
    Context context;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_);


        buttonOnboardingAction = findViewById(R.id.buttonOnBoardingAction);
        stv = findViewById(R.id.skipTV_id);
        introtv1 = findViewById(R.id.textTitle);
        introtv2 = findViewById(R.id.textDescription);
        languageImg = findViewById(R.id.languageBtn_Id);
        languagetv = findViewById(R.id.languageTV_id);

        // language setter
        Log.e("ChekingBangla", String.valueOf(Helper.getBangla()));
        Log.e("ChekingEnglish", String.valueOf(Helper.getEnglish()));
        if (Helper.getEnglish()) {
            Helper.setEnglish(true);
            Helper.setBangla(false);
        } else  {
            Helper.setEnglish(false);
            Helper.setBangla(true);

        }


        Log.e("Bangla", String.valueOf(Helper.getBangla()));


        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));

            context = Localhelper.setLocale(Onboarding_Activity.this, "en");
            resources = context.getResources();

            stv.setText(resources.getString(R.string.skip));
            buttonOnboardingAction.setText(resources.getString(R.string.gs));
            introtv1.setText(resources.getString(R.string.text1));
            introtv2.setText(resources.getString(R.string.text2));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(Onboarding_Activity.this, "bn");
            resources = context.getResources();

            stv.setText(resources.getString(R.string.skip));
            buttonOnboardingAction.setText(resources.getString(R.string.gs));
            introtv1.setText(resources.getString(R.string.text1));
            introtv2.setText(resources.getString(R.string.text2));

        }

        languageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] language = {"English", "Bangla"};
                int checkeditem;
                if (Helper.getBangla()) {
                    checkeditem = 1;
                } else {
                    checkeditem = 0;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(Onboarding_Activity.this);
                builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        languagetv.setText(language[which]);
                        if (language[which].equals("English")) {
                            Helper.setEnglish(true);
                            Helper.setBangla(false);
                            context = Localhelper.setLocale(Onboarding_Activity.this, "en");
                            resources = context.getResources();

                            stv.setText(resources.getString(R.string.skip));
                            buttonOnboardingAction.setText(resources.getString(R.string.gs));

                        }
                        if (language[which].equals("Bangla")) {
                            Helper.setBangla(true);
                            Helper.setEnglish(false);
                            context = Localhelper.setLocale(Onboarding_Activity.this, "bn");
                            resources = context.getResources();
                            stv.setText(resources.getString(R.string.skip));
                            buttonOnboardingAction.setText(resources.getString(R.string.gs));
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


        Log.e("Bangla", String.valueOf(Helper.getBangla()));

        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(Onboarding_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }


        stv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onboarding_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }); buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onboarding_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
