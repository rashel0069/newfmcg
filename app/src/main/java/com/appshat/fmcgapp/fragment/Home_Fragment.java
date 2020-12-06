package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.model.InformationViewModel;

import java.nio.file.OpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import static java.lang.String.valueOf;

public class Home_Fragment<Date> extends Fragment {

    private static final String TAG = "Activity" ;
    Button cashbtn, transactionbtn, orderbtn, showtransbtn, expensebtn, adjustbtn;
    TextView openingCash,dayendCash,receivablecash, payableCash, cashSell, creditSell, purchaseCash, expenseCash, totalCash;
    String opening,receviable,payable,dayend,sellcash,sellcredit,cashpurches,cashexpence,cashtotal;
    InformationDao informationDbDao;
    Databaseroom infoDatabase;
    public static final String MY_PREF_NAME = "myPrefFile";

    InformationViewModel informationViewModel;
    int result=0,dend=0,wid=0,depo=0,i=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        adjustbtn = view.findViewById( R.id.adjustBtn_id );
        cashbtn = view.findViewById(R.id.cashboxBtn_id);
        transactionbtn = view.findViewById(R.id.newtransactionBtn_id);
        orderbtn = view.findViewById(R.id.orderBtn_id);
        showtransbtn = view.findViewById(R.id.showtransactionBtn_id);
        expensebtn = view.findViewById(R.id.expenseBtn_id);
        dayendCash = view.findViewById( R.id.textdayend_id );
        openingCash = view.findViewById( R.id.openingamountTV_id );
        receivablecash = view.findViewById( R.id.receivableamountTV_id );
        payableCash = view.findViewById( R.id.payableamountTV_id );
        cashSell = view.findViewById( R.id.cashsalesamountTV_id );
        creditSell = view.findViewById( R.id.creditsalesamountTV_id );
        purchaseCash = view.findViewById( R.id.purchaseamountTV_id );
        expenseCash = view.findViewById( R.id.expenseamountTV_id );
        totalCash = view.findViewById( R.id.totalsalesamountTV_id );

//        SharedPreferences prefs = getActivity().getSharedPreferences( MY_PREF_NAME, Context.MODE_PRIVATE );
//        openingCash.setText( prefs.getString( "opencash","0" ) );
//        receivablecash.setText( prefs.getString( "receivablecash","0"));
//        payableCash.setText( prefs.getString( "payablecash","0"));
//        dayendCash.setText( prefs.getString( "opencash","0" ) );
        
//        informationViewModel = ViewModelProviders.of(this).get( InformationViewModel.class );
//        informationViewModel.getAllinformation().observe( this, new Observer<List<InformationEntity>>() {
//            @Override
//            public void onChanged(List<InformationEntity> informationEntities) {
//
//                Log.d( TAG, "onChanged: " + informationEntities.toString());
//
//            }
//        } );




        //Data Receive

        Bundle cashboxbundle = this.getArguments();
        if (cashboxbundle != null) {
             dend = cashboxbundle.getInt("Dayendbalance");
             wid = cashboxbundle.getInt("Withdrawalbalance");
             depo = cashboxbundle.getInt("Depositbalance");

            Toast.makeText(getContext(), ""+dend+" "+wid+" "+depo, Toast.LENGTH_SHORT).show();
            result = (result + dend + depo) - wid;
            dayendCash.setText( String.valueOf( result ) );


        }


        cashbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cashbox_Fragment cash_box_fragment = new Cashbox_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, cash_box_fragment);
                transaction.addToBackStack("null");
                transaction.commit();

            }
        });

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "connect to chatbot", Toast.LENGTH_SHORT).show();
            }
        });

        transactionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        showtransbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTransaction_Fragment show_transaction_fragment = new ShowTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, show_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        expensebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expense_Fragment expense_fragment = new Expense_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, expense_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        adjustbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adjust_Balance_Fragment adjust_balance_fragment = new Adjust_Balance_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, adjust_balance_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        return view;
    }


}