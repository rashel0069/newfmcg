package com.appshat.kherokhata.NewUIFragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;


public class About_Fragment extends Fragment {
    TextView firstpart, secondpart;
    Context context;
    Resources resources;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about__, container, false);
        firstpart = view.findViewById(R.id.textViewJustify);
        secondpart = view.findViewById(R.id.textViewJustify2);

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();

            firstpart.setText(resources.getString(R.string.about));
            secondpart.setText(resources.getString(R.string.about2));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            firstpart.setText(resources.getString(R.string.about));
            secondpart.setText(resources.getString(R.string.about2));

        }


        return view;
    }
}

