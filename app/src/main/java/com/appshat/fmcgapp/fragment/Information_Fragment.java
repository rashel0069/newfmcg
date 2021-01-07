package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.model.InformationViewModel;

import static android.content.Context.MODE_PRIVATE;

public class Information_Fragment extends Fragment {
    TextView shoppnameTV, ownernameTV, addressTV, phonenumberTV, openingTV, receivableTV, payableTV;
    EditText shoppnameEt, ownernameET, addressET, phonenumberET, openingET, receivableET, payableET;
    Button saveBtn;
    String usermobile, shopname, shopkeepername, shopaddress, opening, receivable, payable;
    Context context;
    Resources resources;
    public static final String MY_PREF_NAME = "myPrefFile";
    InformationViewModel informationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_information_, container, false);

//        //database
        informationViewModel = ViewModelProviders.of(getActivity()).get(InformationViewModel.class);
        shoppnameTV = view.findViewById(R.id.sname_TV);
        shoppnameEt = view.findViewById(R.id.sname_ET);
        ownernameTV = view.findViewById(R.id.sownername_TV);
        ownernameET = view.findViewById(R.id.sownername_ET);
        addressTV = view.findViewById(R.id.saddress_TV);
        addressET = view.findViewById(R.id.saddress_ET);
        phonenumberTV = view.findViewById(R.id.smobilenumber_TV);
        phonenumberET = view.findViewById(R.id.smobilenumber_ET);
        openingTV = view.findViewById(R.id.openingamount_TV);
        openingET = view.findViewById(R.id.openingamount_ET);
        receivableTV = view.findViewById(R.id.recamnt_TV);
        receivableET = view.findViewById(R.id.receivableamount_ET);
        payableTV = view.findViewById(R.id.payamnt_TV);
        payableET = view.findViewById(R.id.payableamount_ET);
        saveBtn = view.findViewById(R.id.saved_id);

//language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            shoppnameTV.setText(resources.getString(R.string.shopname));
            shoppnameEt.setHint(resources.getString(R.string.shopnamehint));
            ownernameTV.setText(resources.getString(R.string.sownername));
            ownernameET.setHint(resources.getString(R.string.shopnamehint));
            addressTV.setText(resources.getString(R.string.saddress));
            addressET.setHint(resources.getString(R.string.saddresshint));
            phonenumberTV.setText(resources.getString(R.string.hint1));
            phonenumberET.setHint(resources.getString(R.string.hint1));
            payableET.setHint(resources.getString(R.string.hint1));
            openingTV.setText(resources.getString(R.string.opening));
            openingET.setHint(resources.getString(R.string.openinghint));
            receivableTV.setText(resources.getString(R.string.ra));
            receivableET.setHint(resources.getString(R.string.rahint));
            payableTV.setText(resources.getString(R.string.pa));
            payableET.setHint(resources.getString(R.string.pahint));
            saveBtn.setText(resources.getString(R.string.save));

        }else {
            context = Localhelper.setLocale(getActivity(),"en");
            resources = context.getResources();
            shoppnameTV.setText(resources.getString(R.string.shopname));
            shoppnameEt.setHint(resources.getString(R.string.shopnamehint));
            ownernameTV.setText(resources.getString(R.string.sownername));
            ownernameET.setHint(resources.getString(R.string.shopnamehint));
            addressTV.setText(resources.getString(R.string.saddress));
            addressET.setHint(resources.getString(R.string.saddresshint));
            phonenumberTV.setText(resources.getString(R.string.hint1));
            phonenumberET.setHint(resources.getString(R.string.hint1));
            payableET.setHint(resources.getString(R.string.hint1));
            openingTV.setText(resources.getString(R.string.opening));
            openingET.setHint(resources.getString(R.string.openinghint));
            receivableTV.setText(resources.getString(R.string.ra));
            receivableET.setHint(resources.getString(R.string.rahint));
            payableTV.setText(resources.getString(R.string.pa));
            payableET.setHint(resources.getString(R.string.pahint));
            saveBtn.setText(resources.getString(R.string.save));
        }
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (!TextUtils.isEmpty(shoppnameEt.getText().toString()) && !TextUtils.isEmpty(ownernameET.getText().toString()) &&
                            !TextUtils.isEmpty(addressET.getText().toString()) && !TextUtils.isEmpty(phonenumberET.getText().toString().trim()) &&
                            !TextUtils.isEmpty(openingET.getText().toString().trim()) && !TextUtils.isEmpty(receivableET.getText().toString().trim())
                            && !TextUtils.isEmpty(payableET.getText().toString().trim())) {

                        shopname = shoppnameEt.getText().toString();
                        shopkeepername = ownernameET.getText().toString();
                        shopaddress = addressET.getText().toString();
                        usermobile = phonenumberET.getText().toString().trim();
                        opening = openingET.getText().toString().trim();
                        receivable = receivableET.getText().toString().trim();
                        payable = payableET.getText().toString().trim();

                        InformationEntity informationEntity = new InformationEntity(usermobile, shopname, shopkeepername, shopaddress, opening, receivable, payable);
                        informationViewModel.insertInfo(informationEntity);

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

                        Toast.makeText(getContext(), "Thank you for your kind informations", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Please fill up all fields", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            return view;

        }


    }
