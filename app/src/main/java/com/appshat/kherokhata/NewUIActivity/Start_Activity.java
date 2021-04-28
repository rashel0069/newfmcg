package com.appshat.kherokhata.NewUIActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.OldAcrivity.MainActivity;
import com.appshat.kherokhata.R;

public class Start_Activity extends AppCompatActivity {
    TextView slTV;
    ImageButton slImg;
    boolean lang_selector = true;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);

        slImg = findViewById(R.id.slimgbtn_id);
        slTV = findViewById(R.id.sl_TV);
        if (Helper.getEnglish()) {
            Helper.setEnglish(true);
            Helper.setBangla(false);
        } else {
            Helper.setEnglish(false);
            Helper.setBangla(true);
        }


        Log.e("Bangla", String.valueOf(Helper.getBangla()));

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(Start_Activity.this, "en");
            resources = context.getResources();
            slTV.setText(resources.getString(R.string.sl));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(Start_Activity.this, "bn");
            resources = context.getResources();
            slTV.setText(resources.getString(R.string.selector));

        }

      slImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] language = {"English", "Bangla"};
                int checkeditem;
                if (Helper.getBangla()) {
                    checkeditem = 1;
                } else {
                    checkeditem = 0;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(Start_Activity.this);
                builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        slTV.setText(language[which]);
                        if (language[which].equals("English")) {
                            Helper.setEnglish(true);
                            Helper.setBangla(false);
                            context = Localhelper.setLocale(Start_Activity.this, "en");
                            resources = context.getResources();
                            slTV.setText(resources.getString(R.string.sl));


                        }

                        if (language[which].equals("Bangla")) {
                            Helper.setBangla(true);
                            Helper.setEnglish(false);
                            context = Localhelper.setLocale(Start_Activity.this, "bn");
                            resources = context.getResources();
                            slTV.setText(resources.getString(R.string.sl));

                        }
                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });



    }
}