package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;

public class History_Fragment extends Fragment {
    Context context;
    Resources resources;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_, container, false);
        TextView cms = view.findViewById( R.id.comingsoon_id );
        //language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();

            cms.setText(resources.getString(R.string.coming_soon));


        }else {
            context = Localhelper.setLocale(getActivity(),"en");
            resources = context.getResources();
            cms.setText(resources.getString(R.string.coming_soon));

        }
   return view;
    }
}