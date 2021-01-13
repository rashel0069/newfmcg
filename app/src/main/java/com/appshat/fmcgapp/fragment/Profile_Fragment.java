package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.MainActivity;
import com.appshat.fmcgapp.R;

public class Profile_Fragment extends Fragment {
    CardView languageselector;
    TextView editTV,websiteTV,fbTV,langTV,logoutTV;
    boolean lang_selected = true;
    Context context;
    Resources resources;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences( MY_PREFS_NAME, Context.MODE_PRIVATE );

        languageselector = view.findViewById(R.id.cardView3);
        editTV = view.findViewById(R.id.editprofile_id);
        websiteTV = view.findViewById(R.id.website_id);
        fbTV = view.findViewById(R.id.fb_id);
        langTV = view.findViewById(R.id.lang_id);
        logoutTV = view.findViewById(R.id.logout_id);


        // edit profile
        CardView edit = view.findViewById( R.id.cardView2 );
        edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "Edit Profile", Toast.LENGTH_SHORT ).show();
            }
        } );
        //website
        CardView web = view.findViewById( R.id.cardView4 );
        web.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserInt = new Intent(Intent.ACTION_VIEW, Uri.parse("http://digitalistic.net/"));
                startActivity( browserInt );
                Toast.makeText( context, "Open WebSite", Toast.LENGTH_SHORT ).show();
            }
        } );
        //facebook
        CardView fb = view.findViewById( R.id.cardView5 );
        fb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserInt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/digitalistic7"));
                startActivity( browserInt );
                Toast.makeText( context, "Open Facebook", Toast.LENGTH_SHORT ).show();
            }
        } );
        //logout
        CardView logout = view.findViewById( R.id.logout_card_id );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().clear().clear().commit();
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity( intent );
                getActivity().finish();
            }
        } );



        // using login swtiching the language

        //language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();

            langTV.setText(resources.getString(R.string.selector));
            editTV.setText(resources.getString(R.string.edit));
            websiteTV.setText(resources.getString(R.string.website));
            fbTV.setText(resources.getString(R.string.facebook));
            logoutTV.setText(resources.getString(R.string.logout));

        }else {
            context = Localhelper.setLocale(getActivity(),"en");
            resources = context.getResources();
            langTV.setText(resources.getString(R.string.selector));
            editTV.setText(resources.getString(R.string.edit));
            websiteTV.setText(resources.getString(R.string.website));
            fbTV.setText(resources.getString(R.string.facebook));
            logoutTV.setText(resources.getString(R.string.logout));

        }
// from in this fragment switching the language
        languageselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[]language ={"English","Bangla"};
                int checkeditem;
                if (Helper.getBangla()){
                    checkeditem = 1;
                }else {
                    checkeditem =0;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        langTV.setText(language[which]);
                        if (language[which].equals("English")){
                            Helper.setBangla(false);
                            context = Localhelper.setLocale(getActivity(),"en");
                            resources = context.getResources();
                            langTV.setText(resources.getString(R.string.selector));
                            editTV.setText(resources.getString(R.string.edit));
                            websiteTV.setText(resources.getString(R.string.website));
                            fbTV.setText(resources.getString(R.string.facebook));
                            logoutTV.setText(resources.getString(R.string.logout));

                        }

                        if (language[which].equals("Bangla")){
                            Helper.setBangla(true);
                            context = Localhelper.setLocale(getActivity(),"bn");
                            resources = context.getResources();
                            langTV.setText(resources.getString(R.string.selector));
                            editTV.setText(resources.getString(R.string.edit));
                            websiteTV.setText(resources.getString(R.string.website));
                            fbTV.setText(resources.getString(R.string.facebook));
                            logoutTV.setText(resources.getString(R.string.logout));

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


        return view;
    }
}