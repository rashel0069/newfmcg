package com.appshat.fmcgapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.appshat.fmcgapp.fragment.About_Fragment;
import com.appshat.fmcgapp.fragment.History_Fragment;
import com.appshat.fmcgapp.fragment.Home_Fragment;
import com.appshat.fmcgapp.fragment.Information_Fragment;
import com.appshat.fmcgapp.fragment.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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


    //bottom nav
    BottomNavigationView bottomNav = findViewById( R.id.b_navigation );
  bottomNav.setOnNavigationItemSelectedListener( navListener );

    getSupportFragmentManager().beginTransaction().replace( R.id.framelayout_container_id,
                new Home_Fragment() ).commit();

}

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            selectedFragment = new Home_Fragment();
                            break;
                        case R.id.history:
                            selectedFragment = new History_Fragment();
                            break;
                        case R.id.profile:
                            selectedFragment = new Profile_Fragment();
                            break;
                        case R.id.about:
                            selectedFragment = new About_Fragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace( R.id.framelayout_container_id,
                            selectedFragment ).commit();

                    return true;
                }

            };

}
