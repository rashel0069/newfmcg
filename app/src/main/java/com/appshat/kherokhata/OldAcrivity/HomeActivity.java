package com.appshat.kherokhata.OldAcrivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.fragment.About_Fragment;
import com.appshat.kherokhata.fragment.History_Fragment;
import com.appshat.kherokhata.fragment.Home_Fragment;
import com.appshat.kherokhata.fragment.Information_Fragment;
import com.appshat.kherokhata.fragment.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    public static final String MY_PREF_NAME = "myPrefFile";
    BottomNavigationView bottomNav;
    Boolean val = false;
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_container_id,
                            selectedFragment).commit();

                    return true;
                }

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences vals = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        val = vals.getBoolean("visibility", false);
        loaddefaultfragment();

        //bottom nav
        bottomNav = findViewById(R.id.b_navigation);
        if (val) {
            bottomNav.setVisibility(View.VISIBLE);
            bottomNav.setOnNavigationItemSelectedListener(navListener);
        } else {
            Toast.makeText(this, "Information Save First", Toast.LENGTH_SHORT).show();
        }

    }

    private void loaddefaultfragment() {
        SharedPreferences prefs = getSharedPreferences("first_fragment", MODE_PRIVATE);
        Boolean firststart = prefs.getBoolean("firststart", false);//"No name defined" is the default value.

        if (firststart) {
            Home_Fragment fragment1 = new Home_Fragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.framelayout_container_id, fragment1);
            val = true;
            ft1.commit();
        } else {
            Information_Fragment fragment = new Information_Fragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.framelayout_container_id, fragment);
            ft1.commit();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firststart", true);
            editor.apply();
        }


    }

}
