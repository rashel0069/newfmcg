package com.appshat.kherokhata.NewUIActivity;

import androidx.appcompat.app.AppCompatActivity;

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

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;

public class First_Splash_Screen extends AppCompatActivity {
    Animation toplogoAnim, bottomlogoAnim;
    ImageView spLogo;
    TextView spName;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__splash__screen);

        Helper.setBangla(true);
        toplogoAnim = AnimationUtils.loadAnimation(this, R.anim.top_animator);
        bottomlogoAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        spLogo= findViewById(R.id.logo_id);
        spName = findViewById(R.id.logotext_TV);

        spLogo.setAnimation(toplogoAnim);
        spName.setAnimation(bottomlogoAnim);

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(First_Splash_Screen.this, "en");
            resources = context.getResources();

            spName.setText(resources.getString(R.string.app_name));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(First_Splash_Screen.this, "bn");
            resources = context.getResources();
            spName.setText(resources.getString(R.string.app_name));
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), First_Intro_Activity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
