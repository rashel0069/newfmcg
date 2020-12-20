package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Kotlin.ui.ChatBot;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.AdjustDao;
import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.ExpenseDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DAO.NewtransactionDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;
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

    private static final String TAG = "Activity";
    Button cashbtn, transactionbtn, orderbtn, showtransbtn, expensebtn, adjustbtn;
    TextView openingCash, dayendCash, receivablecash, payableCash, cashSell, creditSell, purchaseCash, expenseCash, totalCash,
            openingcashTV, dayendcashTV, receivablecashTV, payablecashTV, cashsellTV, creditsellTV, purchasecashTV, expensecashTV, totalcashTV;
    String opening, receviable, payable, dayend, withdraw, deposit, sellcash, sellcredit, cashpurches, cashexpence, cashtotal;
    String prev;
    InformationDao informationDbDao;
    NewtransactionDao newtransactionDao;
    ExpenseDao expenseDao;
    CashboxDao cashboxDao;
    AdjustDao adjustDao;
    Databaseroom databaseroom;
    public static final String MY_PREF_NAME = "myPrefFile";
    Context context;
    Resources resources;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        cal.add(Calendar.DAY_OF_YEAR, -1);
        prev = s.format(cal.getTime());

        adjustbtn = view.findViewById(R.id.adjustBtn_id);
        cashbtn = view.findViewById(R.id.cashboxBtn_id);
        transactionbtn = view.findViewById(R.id.newtransactionBtn_id);
        orderbtn = view.findViewById(R.id.orderBtn_id);
        showtransbtn = view.findViewById(R.id.showtransactionBtn_id);
        expensebtn = view.findViewById(R.id.expenseBtn_id);
        dayendcashTV = view.findViewById(R.id.dayendcashTitle_id);
        dayendCash = view.findViewById(R.id.textdayend_id);
        openingcashTV = view.findViewById(R.id.openingamountTitle_id);
        openingCash = view.findViewById(R.id.openingamountTV_id);
        receivablecashTV = view.findViewById(R.id.receivableTV_id);
        receivablecash = view.findViewById(R.id.receivableamountTV_id);
        payablecashTV = view.findViewById(R.id.payableTitle_id);
        payableCash = view.findViewById(R.id.payableamountTV_id);
        cashsellTV = view.findViewById(R.id.cashsalesTitle_id);
        cashSell = view.findViewById(R.id.cashsalesamountTV_id);
        creditsellTV = view.findViewById(R.id.creditsalesTitle_id);
        creditSell = view.findViewById(R.id.creditsalesamountTV_id);
        purchasecashTV = view.findViewById(R.id.purchaseTitle_id);
        purchaseCash = view.findViewById(R.id.purchaseamountTV_id);
        expensecashTV = view.findViewById(R.id.expenseTitle_id);
        expenseCash = view.findViewById(R.id.expenseamountTV_id);
        totalcashTV = view.findViewById(R.id.totalsalesTitle_id);
        totalCash = view.findViewById(R.id.totalsalesamountTV_id);

//language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            dayendcashTV.setText(resources.getString(R.string.dayendcash));
            openingcashTV.setText(resources.getString(R.string.opening));
            receivablecashTV.setText(resources.getString(R.string.receivable));
            payablecashTV.setText(resources.getString(R.string.payable));
            cashsellTV.setText(resources.getString(R.string.cashsales));
            creditsellTV.setText(resources.getString(R.string.creditsales));
            purchasecashTV.setText(resources.getString(R.string.purchase));
            expensecashTV.setText(resources.getString(R.string.expense));
            totalcashTV.setText(resources.getString(R.string.totalsales));
            adjustbtn.setText(resources.getString(R.string.adjust));
            cashbtn.setText(resources.getString(R.string.cashBox));
            transactionbtn.setText(resources.getString(R.string.newtransaction));
            orderbtn.setText(resources.getString(R.string.order));
            showtransbtn.setText(resources.getString(R.string.showtransaction));
            expensebtn.setText(resources.getString(R.string.expense));


        } else {
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            dayendcashTV.setText(resources.getString(R.string.dayendcash));
            openingcashTV.setText(resources.getString(R.string.opening));
            receivablecashTV.setText(resources.getString(R.string.receivable));
            payablecashTV.setText(resources.getString(R.string.payable));
            cashsellTV.setText(resources.getString(R.string.cashsales));
            creditsellTV.setText(resources.getString(R.string.creditsales));
            purchasecashTV.setText(resources.getString(R.string.purchase));
            expensecashTV.setText(resources.getString(R.string.expense));
            totalcashTV.setText(resources.getString(R.string.totalsales));
            adjustbtn.setText(resources.getString(R.string.adjust));
            cashbtn.setText(resources.getString(R.string.cashBox));
            transactionbtn.setText(resources.getString(R.string.newtransaction));
            orderbtn.setText(resources.getString(R.string.order));
            showtransbtn.setText(resources.getString(R.string.showtransaction));
            expensebtn.setText(resources.getString(R.string.expense));
        }

        //creditsells Data
        databaseroom = Databaseroom.getDatabaseroomref(getActivity());
        newtransactionDao = databaseroom.getnewtransaction();
        expenseDao = databaseroom.getExpenseDao();
        cashboxDao = databaseroom.getCashboxDao();
        adjustDao = databaseroom.getduepayandreceive();
        LoadDatafromRoom();


        //Data Receive


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
                Intent myIntent = new Intent(view.getContext(), ChatBot.class);
                startActivityForResult(myIntent, 0);
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
            creditSell.setText(crSells);
            receivablecash.setText(crSells);
            String cashEX = new GetExpense().execute().get();
            expenseCash.setText(cashEX);
            String sellTotal = new GetAllSells().execute().get();
            totalCash.setText(sellTotal);
            String cashPurc = new GetCashpurches().execute().get();
            purchaseCash.setText(cashPurc);
            String creditPur = new GetPayable().execute().get();
            payableCash.setText(creditPur);
            String dayend = new Getdayendinfo().execute().get();
            dayendCash.setText(dayend);
            opening = new GetOpeningCash().execute().get();
            openingCash.setText(opening);
            String withdraw = new Getwithdrawinfo().execute().get();
            String deposit = new Getdepositinfo().execute().get();
            String salesreturncash = new GetSelesreturnCash().execute().get();
            String salesreturncredit = new GetSelesreturnCredit().execute().get();
            String purchesreturncash = new GetPursesreturnCash().execute().get();
            String purchesreturncredit = new GetSelesreturnCredit().execute().get();
            String pastreceivable = new GetPastReceviable().execute().get();
            String pastpayable = new GetPastPayable().execute().get();

            if (!dayendCash.getText().toString().isEmpty() && Double.valueOf(dayendCash.getText().toString()) != 0.0) {
                Double cashseles = (Double.parseDouble(dayend) - Double.parseDouble(opening) + Double.parseDouble(cashPurc) + Double.parseDouble(withdraw)
                        - Double.parseDouble(purchesreturncash) - Double.parseDouble(pastreceivable) + Double.parseDouble(pastpayable) + Double.parseDouble(salesreturncash));

                cashSell.setText(String.valueOf(cashseles));

                Double tseles = Double.parseDouble(crSells) + cashseles;
                totalCash.setText(String.valueOf(tseles));

            }


        } catch (Exception e) {
            Toast.makeText(getContext(), "Error:" + e, Toast.LENGTH_SHORT).show();
        }
    }

    public class GetCreditSells extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell("Sales", "Credit", currentdate);
            Double total_credit = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++) {
                total_credit = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + total_credit;
            }

            return total_credit.toString().trim();
        }
    }

    public class GetCashpurches extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell("Purchase", "Cash", currentdate);
            Double cash_purchaes = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++) {
                cash_purchaes = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + cash_purchaes;
            }

            return cash_purchaes.toString().trim();
        }
    }

    public class GetPayable extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell("Purchase", "Credit", currentdate);
            Double credit_purchaes = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++) {
                credit_purchaes = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + credit_purchaes;
            }

            return credit_purchaes.toString().trim();
        }
    }

    public class GetAllSells extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getAllSell("Sales", currentdate);
            Double total_sell = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++) {
                total_sell = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + total_sell;
            }

            return total_sell.toString().trim();
        }
    }

    public class GetExpense extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<ExpenseEntity> expense = expenseDao.getExpense(currentdate);
            Double total_expense = 0.0, b, a, c = 0.0;
            for (int i = 0; i < expense.size(); i++) {
                a = Double.parseDouble(expense.get(i).getRent());
                b = Double.parseDouble(expense.get(i).getSalary());
                c = Double.parseDouble(expense.get(i).getOthers());
                total_expense = a + b + c + total_expense;
            }
            return total_expense.toString().trim();
        }
    }

    // dayend withdraw and deposit
    public class Getdayendinfo extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo(currentdate);
            Double dayend = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++) {
                dayend = Double.parseDouble(cashboxEntities.get(i).getDayend());
            }

            return dayend.toString();
        }
    }

    // dayend withdraw and deposit
    public class GetOpeningCash extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo(prev);
            Double open = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++) {
                open = Double.parseDouble(cashboxEntities.get(i).getDayend());
            }

            return open.toString();
        }
    }

    public class Getwithdrawinfo extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfoWith(currentdate);
            Double withdraw = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++) {
                withdraw = Double.parseDouble(cashboxEntities.get(i).getWithdrawl()) + withdraw;
            }

            return withdraw.toString();
        }
    }

    public class Getdepositinfo extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfoDep(currentdate);
            Double deposit = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++) {
                deposit = Double.parseDouble(cashboxEntities.get(i).getDeposit()) + deposit;
            }

            return deposit.toString();
        }
    }

    //seles return
    public class GetSelesreturnCash extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Sales_Return", "Cash", currentdate);
            Double salesreturncash = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                salesreturncash = Double.parseDouble(adjustEntities.get(i).getClientamount()) + salesreturncash;
            }

            return salesreturncash.toString();
        }
    }

    public class GetSelesreturnCredit extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Sales_Return", "Credit", currentdate);
            Double salesreturncredit = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                salesreturncredit = Double.parseDouble(adjustEntities.get(i).getClientamount()) + salesreturncredit;
            }

            return salesreturncredit.toString();
        }
    }

    //purses return
    public class GetPursesreturnCash extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Purchase_Return", "Cash", currentdate);
            Double pureturncash = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                pureturncash = Double.parseDouble(adjustEntities.get(i).getClientamount()) + pureturncash;
            }

            return pureturncash.toString();
        }
    }

    public class GetPurchesreturnCredit extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Purchase_Return", "Credit", currentdate);
            Double pureturncredit = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                pureturncredit = Double.parseDouble(adjustEntities.get(i).getClientamount()) + pureturncredit;
            }

            return pureturncredit.toString();
        }
    }

    public class GetPastReceviable extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Past_Receivable", "Cash", currentdate);
            Double pastreceivable = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                pastreceivable = Double.parseDouble(adjustEntities.get(i).getClientamount()) + pastreceivable;
            }

            return pastreceivable.toString();
        }

    }

    public class GetPastPayable extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Past_Payable", "Cash", currentdate);
            Double pastpayable = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                pastpayable = Double.parseDouble(adjustEntities.get(i).getClientamount()) + pastpayable;
            }

            return pastpayable.toString();
        }

    }
}


