package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;
import com.appshat.fmcgapp.Room.model.CashBoxViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Cashbox_Fragment extends Fragment {
    EditText dayendET, withdrawalET, depositET;
    TextView cashttile,dayendTV,withdrawalTV,depositTV;
    Button cashbtn;
    String dayend, withdrawal, deposit, datetime;
    CashboxDao cashboxDBdao;
    InformationDao informationDbDao;
    Databaseroom cashboxDB;
    CashBoxViewModel cashBoxViewModel;
    int d1,d2,d3;
    Context context;
    Resources resources;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cashbox_fragment, container, false);
        cashbtn = view.findViewById(R.id.cashsaveBtn_id);
        cashttile=view.findViewById(R.id.cashtitleTV_Id);
        dayendTV=view.findViewById(R.id.dc_Id);
        dayendET = view.findViewById(R.id.dayendcashET_id);
        withdrawalTV=view.findViewById(R.id.wd_id);
        withdrawalET = view.findViewById(R.id.withdrawalET_id);
        depositTV=view.findViewById(R.id.ds_id);
        depositET = view.findViewById(R.id.depositET_id);


        //language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            cashttile.setText(resources.getString(R.string.cashtransaction));
            dayendTV.setText(resources.getString(R.string.dayendcash));
            withdrawalTV.setText(resources.getString(R.string.withdrawal));
            depositTV.setText(resources.getString(R.string.depositcash));
            cashbtn.setText(resources.getString(R.string.save));

        } else {
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            cashttile.setText(resources.getString(R.string.cashtransaction));
            dayendTV.setText(resources.getString(R.string.dayendcash));
            withdrawalTV.setText(resources.getString(R.string.withdrawal));
            depositTV.setText(resources.getString(R.string.depositcash));
            cashbtn.setText(resources.getString(R.string.save));
        }

        //database
        cashBoxViewModel = ViewModelProviders.of( getActivity() ).get( CashBoxViewModel.class );
        //Date time
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());


        cashbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayend = dayendET.getText().toString();
                withdrawal = withdrawalET.getText().toString();
                deposit = depositET.getText().toString();
                datetime = currentdate;

                if (!TextUtils.isEmpty(dayendET.getText().toString().trim()) && !TextUtils.isEmpty(depositET.getText().toString().trim()) && !TextUtils.isEmpty(withdrawalET.getText().toString().trim())) {
                    calculation();

                } else if (!TextUtils.isEmpty(dayendET.getText().toString().trim()) && !TextUtils.isEmpty(depositET.getText().toString().trim())){
                    withdrawal = "0";
                    calculation();

                }else if (!TextUtils.isEmpty(dayendET.getText().toString().trim()) && !TextUtils.isEmpty(withdrawalET.getText().toString().trim())){
                    deposit = "0";
                    calculation();
                }else if (!TextUtils.isEmpty(depositET.getText().toString().trim()) && !TextUtils.isEmpty(withdrawalET.getText().toString().trim())){
                    dayend ="0";
                    calculation();
                }else if (!TextUtils.isEmpty(dayendET.getText().toString().trim())){
                    withdrawal = "0";
                    deposit = "0";
                    calculation();
                }else if (!TextUtils.isEmpty(depositET.getText().toString().trim()) ){
                    withdrawal = "0";
                    dayend ="0";
                    calculation();
                }else if (!TextUtils.isEmpty(withdrawalET.getText().toString().trim())){
                    dayend ="0";
                    deposit = "0";
                    calculation();
                }else {
                    Toast.makeText( getContext(), "Please Fill up one field", Toast.LENGTH_SHORT ).show();
                }

            }

        });


        return view;


    }

    private void calculation() {

        //calculation
        d1 = Integer.parseInt(dayend);
        d2 =Integer.parseInt(withdrawal);
        d3 = Integer.parseInt(deposit);

        CashboxEntity cashboxEntity = new CashboxEntity(datetime, dayend, withdrawal, deposit);
        cashBoxViewModel.insertCashbox(cashboxEntity);
        Toast.makeText(getContext(), "Insert Successfully", Toast.LENGTH_SHORT).show();

        Home_Fragment fragment1 = new Home_Fragment();
        FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.framelayout_container_id, fragment1);
        ft1.commit();
    }

}