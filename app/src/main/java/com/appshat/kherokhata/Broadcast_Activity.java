package com.appshat.kherokhata;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Broadcast_Activity extends AppCompatActivity {
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_);
        tv1 = findViewById(R.id.broadcast_TV);
        tv2 = findViewById(R.id.titletv_id);
        String message = getIntent().getStringExtra("description");
        tv1.setText(message);

    }
}