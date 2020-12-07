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
import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.ExpenseDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DAO.NewtransactionDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;
import com.appshat.fmcgapp.Room.ENTITY.ExpenseEntity;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;
import com.appshat.fmcgapp.Room.model.InformationViewModel;

import java.nio.file.OpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import static java.lang.String.valueOf;

public class Home_Fragment<Date> extends Fragment {

    private static final String TAG = "Activity" ;
    Button cashbtn, transactionbtn, orderbtn, showtransbtn, expensebtn, adjustbtn;
    TextView openingCash,dayendCash,receivablecash, payableCash, cashSell, creditSell, purchaseCash, expenseCash, totalCash;
    String opening,receviable,payable,dayend,withdraw,deposit,sellcash,sellcredit,cashpurches,cashexpence,cashtotal;
    InformationDao informationDbDao;
    NewtransactionDao newtransactionDao;
    ExpenseDao expenseDao;
    CashboxDao cashboxDao;
    Databaseroom databaseroom;
    public static final String MY_PREF_NAME = "myPrefFile";

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


        //creditsells Data
        databaseroom = Databaseroom.getDatabaseroomref( getActivity() );
        newtransactionDao = databaseroom.getnewtransaction();
        expenseDao = databaseroom.getExpenseDao();
        cashboxDao = databaseroom.getCashboxDao();
        LoadDatafromRoom();


        //Data Receive

//        Bundle cashboxbundle = this.getArguments();
//        if (cashboxbundle != null) {
//             dend = cashboxbundle.getInt("Dayendbalance");
//             wid = cashboxbundle.getInt("Withdrawalbalance");
//             depo = cashboxbundle.getInt("Depositbalance");
//
//            Toast.makeText(getContext(), ""+dend+" "+wid+" "+depo, Toast.LENGTH_SHORT).show();
//            result = (result + dend + depo) - wid;
//            dayendCash.setText( String.valueOf( result ) );
//
//
//        }


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

    private void LoadDatafromRoom() {

        try {
            String crSells = new GetCreditSells().execute().get();
            creditSell.setText( crSells );
            receivablecash.setText( crSells );
            String cashEX = new GetExpense().execute().get();
            expenseCash.setText( cashEX );
            String sellTotal = new GetAllSells().execute().get();
            totalCash.setText( sellTotal );
            String cashPurc = new GetCashpurches().execute().get();
            purchaseCash.setText( cashPurc );
            String creditPur = new GetPayable().execute().get();
            payableCash.setText( creditPur );
            String dayend = new Getdayendinfo().execute().get();
            dayendCash.setText( dayend );
            String withdraw = new Getwithdrawinfo().execute().get();
            String deposit = new Getdepositinfo().execute().get();



        }catch (Exception e){
            Toast.makeText( getContext(), "Error:"+e, Toast.LENGTH_SHORT ).show();
        }
    }

    public class GetCreditSells extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell( "Sales","Credit",currentdate );
            Double total_credit = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++){
                total_credit = Double.parseDouble( newtransactionEntities.get( i ).getClientamount() ) + total_credit ;
            }

            return total_credit.toString().trim();
        }
    }

    public class GetCashpurches extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell( "Purchase","Cash",currentdate );
            Double cash_purchaes = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++){
                cash_purchaes = Double.parseDouble( newtransactionEntities.get( i ).getClientamount() ) + cash_purchaes ;
            }

            return cash_purchaes.toString().trim();
        }
    }

    public class GetPayable extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell( "Purchase","Credit",currentdate );
            Double credit_purchaes = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++){
                credit_purchaes = Double.parseDouble( newtransactionEntities.get( i ).getClientamount() ) + credit_purchaes ;
            }

            return credit_purchaes.toString().trim();
        }
    }

    public class GetAllSells extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getAllSell( "Sales",currentdate );
            Double total_sell = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++){
                total_sell = Double.parseDouble( newtransactionEntities.get( i ).getClientamount() ) + total_sell ;
            }

            return total_sell.toString().trim();
        }
    }

    public class GetExpense extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<ExpenseEntity> expense = expenseDao.getExpense( currentdate );
            Double total_expense = 0.0,b,a,c = 0.0;
            for (int i = 0; i < expense.size(); i++){
                a = Double.parseDouble( expense.get( i ).getRent()) ;
                b = Double.parseDouble( expense.get( i ).getSalary());
                c = Double.parseDouble( expense.get( i ).getOthers());
                total_expense = a+b+c+ total_expense;
            }
            return total_expense.toString().trim();
        }
    }

    // dayend withdraw and deposit
    public class Getdayendinfo extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo( currentdate );
            Double dayend = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++){
               dayend = Double.parseDouble( cashboxEntities.get( i ).getDayend() );
            }

            return dayend.toString();
        }
    }

    public class Getwithdrawinfo extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo( currentdate );
            Double withdraw = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++){
               withdraw = Double.parseDouble( cashboxEntities.get( i ).getWithdrawl() ) + withdraw;
            }

            return withdraw.toString();
        }
    }

    public class Getdepositinfo extends AsyncTask<Void,Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo( currentdate );
            Double deposit = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++){
               deposit = Double.parseDouble( cashboxEntities.get( i ).getDeposit() ) + deposit;
            }

            return deposit.toString();
        }
    }



}