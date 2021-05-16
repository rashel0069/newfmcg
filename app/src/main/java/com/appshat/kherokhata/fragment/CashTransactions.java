package com.appshat.kherokhata.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.appshat.kherokhata.OldAcrivity.ExampleDialog;
import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;

public class CashTransactions extends Fragment {
    Button btncash_purch, btnsales_return, btnpurch_return, btnopening_amount, btncash_box, btnexpance, dayendbtn;
    Context context;
    Resources resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ctv = inflater.inflate(R.layout.fragment_cash_transactions, container, false);

        btncash_purch = ctv.findViewById(R.id.btn_cashpurchase);
        btnsales_return = ctv.findViewById(R.id.btn_salesret);
        btnpurch_return = ctv.findViewById(R.id.btn_purchaseret);
        btnopening_amount = ctv.findViewById(R.id.btn_opencash);
        btncash_box = ctv.findViewById(R.id.btn_cashbox);
        btnexpance = ctv.findViewById(R.id.btn_expense);
        dayendbtn = ctv.findViewById(R.id.btn_dayendcash);


        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));

            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            btncash_purch.setText(resources.getString(R.string.cash_purchase));
            btnsales_return.setText(resources.getString(R.string.sales_returns));
            btnpurch_return.setText(resources.getString(R.string.purchase_returns));
            btnopening_amount.setText(resources.getString(R.string.opening));
            btncash_box.setText(resources.getString(R.string.withdrawal_deposit));
            btnexpance.setText(resources.getString(R.string.expense));
            dayendbtn.setText(resources.getString(R.string.dayendcash));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            btncash_purch.setText(resources.getString(R.string.cash_purchase));
            btnsales_return.setText(resources.getString(R.string.sales_returns));
            btnpurch_return.setText(resources.getString(R.string.purchase_returns));
            btnopening_amount.setText(resources.getString(R.string.opening));
            btncash_box.setText(resources.getString(R.string.withdrawal_deposit));
            btnexpance.setText(resources.getString(R.string.expense));
            dayendbtn.setText(resources.getString(R.string.dayendcash));
        }
        //button cash purchase

        btncash_purch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                NewTransaction_Fragment new_transaction_fragment = new NewTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Purchase");
                bundle.putString("TransType", "Cash");
                bundle.putString("TransType", "Cash");
                bundle.putString("Title", btncash_purch.getText().toString());
                new_transaction_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, new_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button sales return

        btnsales_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Adjust_Balance_Fragment adjust_balance_fragment = new Adjust_Balance_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Sales Return");
                bundle.putString("TransType", "Cash");
                bundle.putString("Title", btnsales_return.getText().toString());
                adjust_balance_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, adjust_balance_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Purchase return

        btnpurch_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Adjust_Balance_Fragment adjust_balance_fragment = new Adjust_Balance_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("AccountType", "Purchase Return");
                bundle.putString("TransType", "Cash");
                bundle.putString("Title", btnpurch_return.getText().toString());
                adjust_balance_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, adjust_balance_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Opening Amount

        btnopening_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getActivity().getSupportFragmentManager(), "Opening amount dialog");
            }
        });
        //button Cash Box

        btncash_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Cashbox_Fragment cash_box_fragment = new Cashbox_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("SelectButton", "deposit");
                bundle.putString("Title", btncash_box.getText().toString());
                cash_box_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, cash_box_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //button Expance

        btnexpance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Expense_Fragment expense_fragment = new Expense_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, expense_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        //dayend

        dayendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashbox_Fragment cash_box_fragment = new Cashbox_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("SelectButton", "dayend");
                bundle.putString("Title", dayendbtn.getText().toString());
                cash_box_fragment.setArguments(bundle);
                transaction.replace(R.id.framelayout_container_id, cash_box_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        return ctv;
    }
}