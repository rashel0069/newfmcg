package com.appshat.kherokhata.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.appshat.kherokhata.OldAcrivity.ExampleDialog;
import com.appshat.kherokhata.R;

public class CashTransactions extends Fragment {
    Button btncash_purch,btnrecive_pay,btnsales_return,btnpurch_return,btnopening_amount,btncash_box,btnexpance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ctv = inflater.inflate(R.layout.fragment_cash_transactions, container, false);
        //button cash purchase
        btncash_purch = ctv.findViewById(R.id.btn_cashpurchase);
        btncash_purch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Purchase");
                bundle.putString("TransType","Cash");
                new_transaction_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button recive and pay
        btnrecive_pay = ctv.findViewById(R.id.btn_receivepay);
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
        //button sales return
        btnsales_return = ctv.findViewById(R.id.btn_salesret);
        btnsales_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(getContext(), "Sales Return", Toast.LENGTH_SHORT).show();
                Adjust_Balance_Fragment adjust_balance_fragment = new Adjust_Balance_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Sales Return");
                bundle.putString("TransType","Cash");
                adjust_balance_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, adjust_balance_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Purchase return
        btnpurch_return = ctv.findViewById(R.id.btn_purchaseret);
        btnpurch_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(getContext(), "Purchase Return", Toast.LENGTH_SHORT).show();
                Adjust_Balance_Fragment adjust_balance_fragment = new Adjust_Balance_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Purchase Return");
                bundle.putString("TransType","Cash");
                adjust_balance_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, adjust_balance_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Opening Amount
        btnopening_amount = ctv.findViewById(R.id.btn_opencash);
        btnopening_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(getContext(), "Opening Amount", Toast.LENGTH_SHORT).show();
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getActivity().getSupportFragmentManager(), "Opening amount dialog");
            }
        });
        //button Cash Box
        btncash_box = ctv.findViewById(R.id.btn_cashbox);
        btncash_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(getContext(), "Cash Box", Toast.LENGTH_SHORT).show();
                Cashbox_Fragment cash_box_fragment = new Cashbox_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, cash_box_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Expance
        btnexpance = ctv.findViewById(R.id.btn_expense);
        btnexpance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Toast.makeText(getContext(), "Expance", Toast.LENGTH_SHORT).show();
                Expense_Fragment expense_fragment = new Expense_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, expense_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        return ctv;
    }
}