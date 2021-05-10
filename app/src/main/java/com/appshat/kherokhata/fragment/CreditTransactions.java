package com.appshat.kherokhata.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;

public class CreditTransactions extends Fragment {
    Button btncredit_purch,btncredit_sales,btnrecive_pay;
    Context context;
    Resources resources;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View crtv =  inflater.inflate(R.layout.fragment_credit_transactions, container, false);

        btncredit_sales = crtv.findViewById(R.id.btn_creditsales);
        btncredit_purch = crtv.findViewById(R.id.btn_creditpurch);
        btnrecive_pay = crtv.findViewById(R.id.btn_receivepay);

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();

          btncredit_purch.setText(resources.getString(R.string.credit_purchase));
          btncredit_sales.setText(resources.getString(R.string.credit_sales));
          btnrecive_pay.setText(resources.getString(R.string.rp));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();

            btncredit_purch.setText(resources.getString(R.string.credit_purchase));
            btncredit_sales.setText(resources.getString(R.string.credit_sales));
            btnrecive_pay.setText(resources.getString(R.string.rp));


        }

        //button Credit Sales

        btncredit_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Sales");
                bundle.putString("TransType","Credit");
                bundle.putString("Title",btncredit_sales.getText().toString());;
                new_transaction_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Credit purches

        btncredit_purch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Purchase");
                bundle.putString("TransType","Credit");
                bundle.putString("Title",btncredit_purch.getText().toString());;
                new_transaction_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button recive and pay

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