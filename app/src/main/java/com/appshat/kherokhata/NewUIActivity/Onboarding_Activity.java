package com.appshat.kherokhata.NewUIActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.OldAcrivity.MainActivity;
import com.appshat.kherokhata.OldAcrivity.SplashScreen;
import com.appshat.kherokhata.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class Onboarding_Activity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private MaterialButton buttonOnboardingAction;

    TextView introtv1, introtv2, stv, languagetv;
    ImageButton languageImg;
    boolean lang_selected = true;
    Context context;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_);
        layoutOnboardingIndicator = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnBoardingAction);
        stv = findViewById(R.id.skipTV_id);
        introtv1 = findViewById(R.id.textTitle);
        introtv2 = findViewById(R.id.textDescription);
        languageImg = findViewById(R.id.languageBtn_Id);
        languagetv = findViewById(R.id.languageTV_id);

        setOnboardingItem();

        if (Helper.getEnglish()) {
            Helper.setEnglish(true);
            Helper.setBangla(false);
        } else  {
            Helper.setEnglish(false);
            Helper.setBangla(true);

        }


        Log.e("Bangla", String.valueOf(Helper.getBangla()));


//// language

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));

            context = Localhelper.setLocale(Onboarding_Activity.this, "en");
            resources = context.getResources();

            stv.setText(resources.getString(R.string.skip));
            buttonOnboardingAction.setText(resources.getString(R.string.next));
            buttonOnboardingAction.setText(resources.getString(R.string.gs));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(Onboarding_Activity.this, "bn");
            resources = context.getResources();

            stv.setText(resources.getString(R.string.skip));
            buttonOnboardingAction.setText(resources.getString(R.string.next));
            buttonOnboardingAction.setText(resources.getString(R.string.gs));

        }

        languageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] language = {"English", "Bangla"};
                int checkeditem;
                if (Helper.getBangla()) {
                    checkeditem = 1;
                } else {
                    checkeditem = 0;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(Onboarding_Activity.this);
                builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        languagetv.setText(language[which]);
                        if (language[which].equals("English")) {
                            Helper.setEnglish(true);
                            Helper.setBangla(false);
                            context = Localhelper.setLocale(Onboarding_Activity.this, "en");
                            resources = context.getResources();

                            stv.setText(resources.getString(R.string.skip));
                            buttonOnboardingAction.setText(resources.getString(R.string.next));
                            buttonOnboardingAction.setText(resources.getString(R.string.gs));

                        }
                        if (language[which].equals("Bangla")) {
                            Helper.setBangla(true);
                            Helper.setEnglish(false);
                            context = Localhelper.setLocale(Onboarding_Activity.this, "bn");
                            resources = context.getResources();
                            stv.setText(resources.getString(R.string.skip));
                            buttonOnboardingAction.setText(resources.getString(R.string.next));
                            buttonOnboardingAction.setText(resources.getString(R.string.gs));
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


     Log.e("Bangla", String.valueOf(Helper.getBangla()));

        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")) {
            Intent intent = new Intent(Onboarding_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();
        }

        //viewpager
        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);
        setOnboadingIndicator();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }

        });
        stv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        });
    }


    private void setOnboadingIndicator() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(10, 0, 10, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setText(getString(R.string.gs));
        } else {
            buttonOnboardingAction.setText(getString(R.string.next));
        }

    }

    private void setOnboardingItem() {
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        OnBoardingItem itemintro = new OnBoardingItem();
        itemintro.setTitle(getString(R.string.text1));
        itemintro.setDescription(getString(R.string.text2));
        itemintro.setImage(R.drawable.img1);

        OnBoardingItem itemtitle = new OnBoardingItem();
        itemtitle.setTitle(getString(R.string.text3));
        itemtitle.setDescription(getString(R.string.text4));
        itemtitle.setImage(R.drawable.img2);

        OnBoardingItem itemdesc = new OnBoardingItem();
        itemdesc.setTitle(getString(R.string.text5));
        itemdesc.setDescription(getString(R.string.text6));
        itemdesc.setImage(R.drawable.img3);

        onBoardingItems.add(itemintro);
        onBoardingItems.add(itemtitle);
        onBoardingItems.add(itemdesc);


        onboardingAdapter = new OnboardingAdapter(onBoardingItems);


    }


}
