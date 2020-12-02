package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DB.Database;

public class Adjust_Balance_Fragment extends Fragment {

    Spinner balancespinner,typespinner;
    EditText adjustamountET;
    Button adjustsaveBtn;


    String accountStatus, accountType, transactionStatus, transactionType, adjustamount, duedatepaydate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adjust__balance_, container, false);

        balancespinner = view.findViewById(R.id.balancetypespinner_id);
        typespinner = view.findViewById(R.id.transitiontypespinner_id);
        adjustamountET = view.findViewById(R.id.adjustamountET_id);
        adjustsaveBtn = view.findViewById(R.id.adjustsaveBtn_id);

       

        return view;
    }
}