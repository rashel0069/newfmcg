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

public class First_Intro_Activity extends AppCompatActivity {
    TextView tv1,skip1tv;
    ImageView pgb1,iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__intro);
        tv1 = findViewById(R.id.sptext1_TV);
        skip1tv = findViewById(R.id.skip1_id);
        pgb1 = findViewById(R.id.pb1_id);
        iv1 = findViewById(R.id.nextiv1_id);

        //substring color
        SpannableString ss = new SpannableString(getString(R.string.text1));

        ForegroundColorSpan fcsWhite = new ForegroundColorSpan(Color.WHITE);
        ForegroundColorSpan fcsBlack= new ForegroundColorSpan(Color.BLACK);

        ss.setSpan(fcsWhite,18,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsBlack,30,47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv1.setText(ss);

        //skip

        skip1tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Second_Intro_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Second_Intro_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}