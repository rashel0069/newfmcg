package com.appshat.kherokhata.OldAcrivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.appshat.kherokhata.NewUIFragment.Menu_Nav_Fragment;
import com.appshat.kherokhata.Notifications.AlarmBroadcast;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.fragment.History_Fragment;
import com.appshat.kherokhata.fragment.Home_Fragment;
import com.appshat.kherokhata.fragment.Information_Fragment;
import com.appshat.kherokhata.fragment.Profile_Fragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    public static final String MY_PREF_NAME = "myPrefFile";
    BottomNavigationView bottomNav;
    FloatingActionButton fab;
    BottomAppBar bottomApp;
    Boolean val = false;
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
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
                        case R.id.placeholder:
                            break;
                        case R.id.profile:
                            selectedFragment = new Profile_Fragment();
                            break;

                        case R.id.menu:
                            selectedFragment = new Menu_Nav_Fragment();
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
        fab = findViewById(R.id.fab);
        bottomApp = findViewById(R.id.bottomAppBar);

        SharedPreferences vals = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        val = vals.getBoolean("visibility", false);
        loaddefaultfragment();

        //bottom nav
        bottomNav = findViewById(R.id.b_navigation);
        bottomNav.getMenu().getItem(2).setOnMenuItemClickListener(MenuItem::isEnabled);
        if (val) {
            fab.setVisibility(View.VISIBLE);
            bottomApp.setVisibility(View.VISIBLE);
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

    public void setNotifications(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setNotifications();
    }
}
