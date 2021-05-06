package com.appshat.kherokhata.OldAcrivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.appshat.kherokhata.NewUIActivity.Onboarding_Activity;
import com.appshat.kherokhata.R;

public class SplashScreen extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    ImageView spImage;
    TextView spText;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Helper.setBangla(true);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animator);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        spImage = findViewById(R.id.splashImage);
        spText = findViewById(R.id.splashText);

        spImage.setAnimation(topAnim);
        spText.setAnimation(bottomAnim);

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(SplashScreen.this, "en");
            resources = context.getResources();

            spText.setText(resources.getString(R.string.app_name));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(SplashScreen.this, "bn");
            resources = context.getResources();
            spText.setText(resources.getString(R.string.app_name));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Onboarding_Activity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}