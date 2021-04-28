package com.appshat.kherokhata.NewUIActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appshat.kherokhata.R;

public class Second_Intro_Activity extends AppCompatActivity {
    TextView stv2,skip2tv;
    ImageView pgb2,iv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__intro_);

        stv2 = findViewById(R.id.sptext3_TV);
        skip2tv = findViewById(R.id.skip2_id);
        pgb2 = findViewById(R.id.pb2_id);
        iv2 = findViewById(R.id.nextiv2_id);
        //substring color
        SpannableString ss = new SpannableString(getString(R.string.text3));

        ForegroundColorSpan fcsWhite = new ForegroundColorSpan(Color.BLACK);
        ForegroundColorSpan fcsBlack= new ForegroundColorSpan(Color.WHITE);

        ss.setSpan(fcsWhite,0,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsBlack,12,35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        stv2.setText(ss);

        //skip

        skip2tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Start_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Start_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}