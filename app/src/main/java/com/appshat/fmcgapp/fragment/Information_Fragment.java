package com.appshat.fmcgapp.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.model.InformationViewModel;

import static android.content.Context.MODE_PRIVATE;

public class Information_Fragment extends Fragment {
    EditText shoppnameEt,ownernameET,addressET,phonenumberET,openingET,receivableET,payableET;
    Button saveBtn;
    String  usermobile,shopname,shopkeepername,shopaddress, opening, receivable,  payable;

    public static final String MY_PREF_NAME = "myPrefFile";

//    InformationDao informationDBdao;
//    Databaseroom informationDB;
    InformationViewModel informationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_information_, container, false);

//        //database
//        informationDB = Room.databaseBuilder( getActivity(), Databaseroom.class,"informations" ).allowMainThreadQueries().build();
//        informationDBdao =
        informationViewModel = ViewModelProviders.of( getActivity()).get( InformationViewModel.class );

        shoppnameEt= view.findViewById(R.id.sname_ET);
        ownernameET= view.findViewById(R.id.sownername_ET);
        addressET= view.findViewById(R.id.saddress_ET);
        phonenumberET= view.findViewById(R.id.smobilenumber_ET);
        openingET= view.findViewById(R.id.openingamount_ET);
        receivableET= view.findViewById(R.id.receivableamount_ET);
        payableET= view.findViewById(R.id.payableamount_ET);

        saveBtn = view.findViewById(R.id.saved_id);


       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               shopname = shoppnameEt.getText().toString();
               shopkeepername = ownernameET.getText().toString();
               shopaddress=addressET.getText().toString();
               usermobile=phonenumberET.getText().toString().trim();
               opening=openingET.getText().toString().trim();
               receivable=receivableET.getText().toString().trim();
               payable=payableET.getText().toString().trim();


               if (shopname != null && shopkeepername != null && shopaddress != null && usermobile != null && opening != null && receivable != null && payable != null  ){
                   InformationEntity informationEntity = new InformationEntity( usermobile,shopname,shopkeepername,shopaddress,opening,receivable,payable );
                   informationViewModel.insertInfo( informationEntity );

                   //save data

                   SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE).edit();
                   editor.putString("opencash", opening);
                   editor.putString("receivablecash", receivable);
                   editor.putString("payablecash", payable);
                   editor.apply();
                   
                   Home_Fragment fragment1 = new Home_Fragment();
                   FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                   ft1.replace(R.id.framelayout_container_id, fragment1);
                   ft1.commit();

                   Toast.makeText( getContext(), "Registraton Done", Toast.LENGTH_SHORT ).show();
               }else {
                   Toast.makeText( getContext(), "Mobile and Password field is empty", Toast.LENGTH_SHORT ).show();
               }



           }
       });

       return  view;

    }


}