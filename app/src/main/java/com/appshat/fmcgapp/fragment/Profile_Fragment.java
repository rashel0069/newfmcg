package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.MainActivity;
import com.appshat.fmcgapp.R;

public class Profile_Fragment extends Fragment {
    ImageView languageselector;
    TextView editTV,websiteTV,fbTV,langTV,logoutTV;
    boolean lang_selected = true;
    Context context;
    Resources resources;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        languageselector = view.findViewById(R.id.langimg_id);
        editTV = view.findViewById(R.id.editprofile_id);
        websiteTV = view.findViewById(R.id.website_id);
        fbTV = view.findViewById(R.id.fb_id);
        langTV = view.findViewById(R.id.lang_id);
        logoutTV = view.findViewById(R.id.logout_id);



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