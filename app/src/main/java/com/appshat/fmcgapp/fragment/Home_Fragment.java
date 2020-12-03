package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.AdjustDao;
import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.ExpenseDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DAO.NewtransactionDao;
import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Database;

import static java.lang.String.valueOf;

public class Home_Fragment extends Fragment {

    Button cashbtn, transactionbtn, orderbtn, showtransbtn, expensebtn, adjustbtn;
    TextView textDaybalabce,receivableamount,payableamount,cashsales,creditsales,purchase,expense,totalsales;
    AdjustDao adjustDao;
    CashboxDao cashboxDao;
    ExpenseDao expenseDao;
    InformationDao informationDao;
    NewtransactionDao newtransactionDao;
    UserDao userDao;
    Database database;
    int result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        cashbtn = view.findViewById(R.id.cashboxBtn_id);
        transactionbtn = view.findViewById(R.id.newtransactionBtn_id);
        orderbtn = view.findViewById(R.id.orderBtn_id);
        showtransbtn = view.findViewById(R.id.showtransactionBtn_id);
        expensebtn = view.findViewById(R.id.expenseBtn_id);
        adjustbtn = view.findViewById(R.id.adjustBtn_id);
        textDaybalabce = view.findViewById(R.id.textdayend_id);
        receivableamount=view.findViewById(R.id.receivableamountTV_id);
        payableamount=view.findViewById(R.id.payableamountTV_id);
        cashsales=view.findViewById(R.id.cashsalesamountTV_id);
        creditsales=view.findViewById(R.id.creditsalesamountTV_id);
        purchase=view.findViewById(R.id.purchaseamountTV_id);
        expense=view.findViewById(R.id.expenseamountTV_id);
        totalsales=view.findViewById(R.id.totalsalesamountTV_id);



        //Data Receive

        Bundle cashboxbundle = this.getArguments();
        if (cashboxbundle != null) {
            int dend = cashboxbundle.getInt("Dayendbalance");
            int wid = cashboxbundle.getInt("Withdrawalbalance");
            int depo = cashboxbundle.getInt("Depositbalance");

            Toast.makeText(getContext(), ""+dend+" "+wid+" "+depo, Toast.LENGTH_SHORT).show();

            try {
                if (!textDaybalabce.getText().toString().isEmpty()){
                    int a1 = Integer.parseInt(textDaybalabce.getText().toString().trim());
                    result = ((a1+ dend + depo)- wid);
                    Toast.makeText(getContext(), ""+result, Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_LONG).show();
            }


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