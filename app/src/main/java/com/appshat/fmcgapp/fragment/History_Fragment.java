package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;

public class History_Fragment extends Fragment {
    Button previous,lw,lm;
    Context context;
    Resources resources;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_, container, false);
        previous = view.findViewById( R.id.previousday_id );
        lw = view.findViewById(R.id.lastweekBtn_id);
        lm = view.findViewById(R.id.lastmonthBtn_id);

        //language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();

            previous.setText(resources.getString(R.string.previousday));
            lw.setText(resources.getString(R.string.lastweek));
            lm.setText(resources.getString(R.string.lastmonth));


        }else {
            context = Localhelper.setLocale(getActivity(),"en");
            resources = context.getResources();

            previous.setText(resources.getString(R.string.previousday));
            lw.setText(resources.getString(R.string.lastweek));
            lm.setText(resources.getString(R.string.lastmonth));
        }
   return view;
    }
}