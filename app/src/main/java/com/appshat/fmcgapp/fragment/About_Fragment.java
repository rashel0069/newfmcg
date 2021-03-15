package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.MainActivity;
import com.appshat.fmcgapp.R;


public class About_Fragment extends Fragment {

    TextView firstpartabout,secondpartabout;
    Context context;
    Resources resources;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_, container, false);
        firstpartabout = view.findViewById(R.id.textViewJustify);
        secondpartabout = view.findViewById(R.id.textViewJustify2);

        //language setter
        if (!Helper.getBangla()){
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(),"en");
            resources = context.getResources();

           firstpartabout.setText(resources.getString(R.string.about));
           secondpartabout.setText(resources.getString(R.string.about2));


        }else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(),"bn");
            resources = context.getResources();
            firstpartabout.setText(resources.getString(R.string.about));
            secondpartabout.setText(resources.getString(R.string.about2));

        }


        return view;
    }
}