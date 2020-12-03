package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Database;
import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Cashbox_Fragment extends Fragment {
    EditText dayendET,withdrawalET,depositET;
    Button cashbtn;
    String dayend,withdrawal,deposit,datetime;
    CashboxDao cashboxDBdao;
    Database cashboxDB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cashbox_fragment, container, false);

        cashbtn = view.findViewById(R.id.cashsaveBtn_id);
        dayendET = view.findViewById(R.id.dayendcashET_id);
        withdrawalET = view.findViewById(R.id.withdrawalET_id);
        depositET=view.findViewById(R.id.depositET_id);

        //database
        cashboxDB = Room.databaseBuilder( getActivity(), Database.class,"cashbox" ).allowMainThreadQueries().build();
        cashboxDBdao = cashboxDB.getCashboxDao();
        //Date time
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());


        cashbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayend = dayendET.getText().toString();
                withdrawal = withdrawalET.getText().toString();
                deposit = depositET.getText().toString();
                datetime = date;

                if (dayend != null && withdrawal != null && deposit != null){
                    CashboxEntity cashboxEntity = new CashboxEntity(datetime,dayend,withdrawal,deposit);
                    cashboxDBdao.insert(cashboxEntity);
                    Toast.makeText(getContext(), "Insert Successfully", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Error please fill up the fields ", Toast.LENGTH_SHORT).show();
                }

            }

        });



        return view;


    }
}