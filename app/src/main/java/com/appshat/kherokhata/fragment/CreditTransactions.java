package com.appshat.kherokhata.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appshat.kherokhata.R;

public class CreditTransactions extends Fragment {
    Button btncredit_purch,btncredit_sales,btnrecive_pay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View crtv =  inflater.inflate(R.layout.fragment_credit_transactions, container, false);
        //button Credit Sales
        btncredit_sales = crtv.findViewById(R.id.btn_creditsales);
        btncredit_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Sales");
                bundle.putString("TransType","Credit");
                new_transaction_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Credit purches
        btncredit_purch = crtv.findViewById(R.id.btn_creditpurch);
        btncredit_purch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Purchase");
                bundle.putString("TransType","Credit");
                new_transaction_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button recive and pay
        btnrecive_pay = crtv.findViewById(R.id.btn_receivepay);
        btnrecive_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Receivablepayable_Fragment receive_balance_fragment = new Receivablepayable_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, receive_balance_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        return crtv;
    }
}