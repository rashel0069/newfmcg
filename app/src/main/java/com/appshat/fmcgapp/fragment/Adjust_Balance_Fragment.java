package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.AdjustDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Database;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;

public class Adjust_Balance_Fragment extends Fragment {

    Spinner accountspinner, transactionspinner;
    EditText adjustamountET, clientnameET, clientmobileET;
    TextView duepaydateTV;
    Button adjustsaveBtn;

    AdjustDao adjustDBdao;
    Database adjustDB;
    String accounttype, transactiontype, clientname, clientmobile, clientamount, date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adjust__balance_, container, false);

        accountspinner = view.findViewById(R.id.balancetypespinner_id);
        transactionspinner = view.findViewById(R.id.transitiontypespinner_id);
        adjustamountET = view.findViewById(R.id.adjustamountET_id);
        clientnameET = view.findViewById(R.id.clientname_id);
        clientmobileET = view.findViewById(R.id.cmblTV_id);
        duepaydateTV = view.findViewById(R.id.currentdateTV_id);
        adjustsaveBtn = view.findViewById(R.id.adjustsaveBtn_id);

        //database
        adjustDB = Room.databaseBuilder(getActivity(), Database.class, "duepayandreceive").allowMainThreadQueries().build();
        adjustDBdao = adjustDB.getduepayandreceive();


//        accounttype = String.valueOf(accountspinner.getSelectedItemPosition());
//        transactiontype = transactionspinner.getSelectedItem().toString();

        accountspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accounttype = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "please choose an item", Toast.LENGTH_SHORT).show();
            }
        });

        transactionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transactiontype = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getContext(), "Please choose an item", Toast.LENGTH_SHORT).show();
            }
        });
        clientname = clientnameET.getText().toString();
        clientmobile = clientmobileET.getText().toString();
        date = duepaydateTV.getText().toString();

        adjustsaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accounttype != null && transactiontype != null && clientname != null && clientmobile != null && date != null) {


                    AdjustEntity adjustEntity = new AdjustEntity(accounttype, transactiontype, clientname, clientmobile, clientamount, date);
                    adjustDBdao.insert(adjustEntity);

                    Home_Fragment fragment1 = new Home_Fragment();
                    FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.framelayout_container_id, fragment1);
                    ft1.commit();

                    Toast.makeText(getContext(), "Registraton Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Mobile and Password field is empty", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getContext(), accounttype + " " + transactiontype, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}