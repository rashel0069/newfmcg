package com.appshat.fmcgapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.appshat.fmcgapp.fragment.Home_Fragment;
import com.appshat.fmcgapp.fragment.Information_Fragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loaddefaultfragment();

    }

    private void loaddefaultfragment() {
        SharedPreferences prefs = getSharedPreferences("first_fragment", MODE_PRIVATE);
        Boolean firststart = prefs.getBoolean("firststart", false);//"No name defined" is the default value.

        if (firststart){
            Home_Fragment fragment1 = new Home_Fragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.framelayout_container_id, fragment1);
            ft1.commit();
        }
        else {
            Information_Fragment fragment = new Information_Fragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.framelayout_container_id, fragment);
            ft1.commit();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean( "firststart", true );
            editor.apply();

        }

    }
}