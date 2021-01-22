package com.appshat.fmcgapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView spImage;
    TextView spText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animator );
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation );
        spImage = findViewById( R.id.splashImage );
        spText = findViewById( R.id.splashText );

        spImage.setAnimation( topAnim );
        spText.setAnimation( bottomAnim );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity( intent );
                finish();
            }
        },4000);
    }
}