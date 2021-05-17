package com.appshat.kherokhata.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.AdjustDao;
import com.appshat.kherokhata.Room.DAO.CashboxDao;
import com.appshat.kherokhata.Room.DAO.ExpenseDao;
import com.appshat.kherokhata.Room.DAO.HistoryDao;
import com.appshat.kherokhata.Room.DAO.InformationDao;
import com.appshat.kherokhata.Room.DAO.NewtransactionDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.AdjustEntity;
import com.appshat.kherokhata.Room.ENTITY.CashboxEntity;
import com.appshat.kherokhata.Room.ENTITY.ExpenseEntity;
import com.appshat.kherokhata.Room.ENTITY.HistoryEntity;
import com.appshat.kherokhata.Room.ENTITY.InformationEntity;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.Room.model.HistoryViewModel;
import com.appshat.kherokhata.Room.model.InformationViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Home_Fragment<Date> extends Fragment {

    public static final String MY_PREF_NAME = "myPrefFile";
    private static final String TAG = "Activity";
    LinearLayout orderbtn, showtransbtn, cashTrn, creditTrn;
    TextView openingCash, dayendCash, receivablecash, payableCash, cashSell, creditSell, purchaseCash, expenseCash, totalCash,
            openingcashTV, dayendcashTV, shopName, receivablecashTV, cashbook, creditbook, orderbook, transbook, payablecashTV, cashsellTV, creditsellTV, purchasecashTV, expensecashTV, totalcashTV;
    String prev, openAmount, opening, shname;
    InformationDao informationDbDao;
    NewtransactionDao newtransactionDao;
    HistoryDao historyDao;
    String currentdate;
    InformationViewModel informationViewModel;
    HistoryViewModel historyViewModel;
    ExpenseDao expenseDao;
    CashboxDao cashboxDao;
    AdjustDao adjustDao;
    Databaseroom databaseroom;
    Context context;
    Resources resources;
    boolean connect = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment_ui, container, false);
        //current date
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int mon = today.month +1;
        currentdate = today.monthDay +"-0"+String.valueOf(mon)+"-"+today.year;

        historyViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        cashTrn = view.findViewById(R.id.cashtrnBtn_id);
        creditTrn = view.findViewById(R.id.cretrnBtn_id);
        orderbtn = view.findViewById(R.id.orderBtn_id);
        showtransbtn = view.findViewById(R.id.showtransactionBtn_id);
        dayendcashTV = view.findViewById(R.id.dayendcashTitle_id);
        dayendCash = view.findViewById(R.id.textdayend_id);
        openingcashTV = view.findViewById(R.id.openingamountTitle_id);
        openingCash = view.findViewById(R.id.openingamountTV_id);
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
        cashbook = view.findViewById(R.id.csbook_tv);
        creditbook = view.findViewById(R.id.crbook_tv);
        orderbook = view.findViewById(R.id.orderbook_tv);
        transbook = view.findViewById(R.id.transbook_tv);
        shopName = view.findViewById(R.id.shop_name_id);

//language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            dayendcashTV.setText(resources.getString(R.string.dayendcash));
            openingcashTV.setText(resources.getString(R.string.opening));
            payablecashTV.setText(resources.getString(R.string.payable));
            cashsellTV.setText(resources.getString(R.string.cashsales));
            creditsellTV.setText(resources.getString(R.string.creditsales));
            purchasecashTV.setText(resources.getString(R.string.purchase));
            expensecashTV.setText(resources.getString(R.string.expense));
            totalcashTV.setText(resources.getString(R.string.totalsales));
            orderbook.setText(resources.getString(R.string.order));
            transbook.setText(resources.getString(R.string.showtransaction));
            cashbook.setText(resources.getString(R.string.cash_transaction));
            creditbook.setText(resources.getString(R.string.credit_transaction));


        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            dayendcashTV.setText(resources.getString(R.string.dayendcash));
            openingcashTV.setText(resources.getString(R.string.opening));
            payablecashTV.setText(resources.getString(R.string.payable));
            cashsellTV.setText(resources.getString(R.string.cashsales));
            creditsellTV.setText(resources.getString(R.string.creditsales));
            purchasecashTV.setText(resources.getString(R.string.purchase));
            expensecashTV.setText(resources.getString(R.string.expense));
            totalcashTV.setText(resources.getString(R.string.totalsales));
            orderbook.setText(resources.getString(R.string.order));
            transbook.setText(resources.getString(R.string.showtransaction));
            cashbook.setText(resources.getString(R.string.cash_transaction));
            creditbook.setText(resources.getString(R.string.credit_transaction));
        }

        //creditsells Data
        databaseroom = Databaseroom.getDatabaseroomref(getActivity());
        newtransactionDao = databaseroom.getnewtransaction();
        historyDao = databaseroom.getHistory();
        expenseDao = databaseroom.getExpenseDao();
        cashboxDao = databaseroom.getCashboxDao();
        adjustDao = databaseroom.getduepayandreceive();
        informationDbDao = databaseroom.getInformationDao();
        informationViewModel = ViewModelProviders.of(getActivity()).get(InformationViewModel.class);
        //historyViewModel = ViewModelProviders.of( getActivity()).get( HistoryViewModel.class );

        LoadDatafromRoom();

        //Data Receive
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent myIntent = new Intent(view.getContext(), ChatBot.class);
//                startActivityForResult(myIntent, 0);

                NetworkConnect();
                if (connect == true) {
//                    Intent browserInt = new Intent(Intent.ACTION_VIEW, Uri.parse("http://103.108.140.234:3000/s/fmcg"));
//                    startActivity(browserInt);

                    BoatFragment boatFragment = new BoatFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.framelayout_container_id, boatFragment);
                    transaction.addToBackStack("null");
                    transaction.commit();

                } else {
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
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
        creditTrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditTransactions creditTransactions = new CreditTransactions();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, creditTransactions);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        cashTrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CashTransactions cashTransactions = new CashTransactions();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, cashTransactions);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        NetworkConnect();

        return view;
    }

    private void NetworkConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        connect = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }


    private void LoadDatafromRoom() {

        try {
            //others data load
            String crSells = new GetCreditSells().execute().get();
            creditSell.setText(crSells);
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
            String opencash = new GetOpeningCash().execute().get();
            openingCash.setText(opencash);
            String withdraw = new Getwithdrawinfo().execute().get();
            String deposit = new Getdepositinfo().execute().get();
            String salesreturncash = new GetSelesreturnCash().execute().get();
            String salesreturncredit = new GetSelesreturnCredit().execute().get();
            String purchesreturncash = new GetPursesreturnCash().execute().get();
            String purchesreturncredit = new GetSelesreturnCredit().execute().get();
            String pastreceivable = new GetPastReceviable().execute().get();
            String pastpayable = new GetPastPayable().execute().get();
            String sh = new GetShopNmae().execute().get();
            shopName.setText(shname);

            if (Double.parseDouble(dayend) != 0.0
                    && Double.parseDouble(opencash) != 0.0) {

                Double cashsales = Double.parseDouble(dayend) - Double.parseDouble(opencash) + Double.parseDouble(cashPurc) +
                        Double.parseDouble(withdraw) - Double.parseDouble(deposit) - Double.parseDouble(purchesreturncash) -
                        Double.parseDouble(pastreceivable) + Double.parseDouble(pastpayable) + Double.parseDouble(salesreturncash)
                        + Double.parseDouble(cashEX);
                if (cashsales >= 0) {
                    cashSell.setText(String.valueOf(cashsales));
                    totalCash.setText(String.valueOf(Double.parseDouble(crSells) + cashsales));
                } else {
                    cashSell.setText("0.0");
                }

            }

            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            String st = new GetUid().execute().get();
            String opc, dac, exc, puc, pac, css, crs, toc;
            opc = openingCash.getText().toString().trim();
            dac = dayendCash.getText().toString().trim();
            exc = expenseCash.getText().toString().trim();
            puc = purchaseCash.getText().toString().trim();
            pac = payableCash.getText().toString().trim();
            css = cashSell.getText().toString().trim();
            crs = creditSell.getText().toString().trim();
            toc = totalCash.getText().toString().trim();

            if (st.matches(currentdate)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HistoryEntity historyEntity = Databaseroom.getDatabaseroomref(getContext()).getHistory().findbyId(currentdate);
                        try {
                            if (historyEntity.getId().matches(currentdate)) {
                                historyEntity.setOpeningammount(opc);
                                historyEntity.setDayendbalance(dac);
                                historyEntity.setExpense(exc);
                                historyEntity.setCashpurchase(puc);
                                historyEntity.setCreditpurchase(pac);
                                historyEntity.setCreditpurchase(pac);
                                historyEntity.setCashsales(css);
                                historyEntity.setCreditsales(crs);
                                historyEntity.setTotalsales(toc);
                                Databaseroom.getDatabaseroomref(getContext()).getHistory().update(historyEntity);
                            }
                        } catch (Exception e) {

                        }
                    }
                }).start();
            } else {
                HistoryEntity historyEntity = new HistoryEntity(currentdate, opc, dac, exc, puc, pac, "0.0", crs, toc, currentdate);
                historyViewModel.insertHistory(historyEntity);
            }
        } catch (Exception e) {
        }
    }


    public class GetUid extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<HistoryEntity> historyEntities = historyDao.getallHistory(currentdate);
            String date = "";
            for (int i = 0; i < historyEntities.size(); i++) {
                date = historyEntities.get(i).getTodaydate();
            }

            return date;
        }
    }

    public class GetCashpurches extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell("Purchase", "Cash", currentdate);
            Double cash_purchaes = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++) {
                cash_purchaes = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + cash_purchaes;
            }

            return cash_purchaes.toString().trim();
        }
    }

    //get Shop name
    public class GetShopNmae extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            List<InformationEntity> informationEntities = informationDbDao.findAllInfo();
            try {
                if (informationEntities != null) {
                    for (int i = 0; i < informationEntities.size(); i++) {
                        shname = informationEntities.get(i).getShopname();
                    }
                }

            } catch (Exception e) {

            }
            return null;
        }
    }

    //dayend
    public class GetInformation extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            List<InformationEntity> informationEntities = informationDbDao.findAllInfo();
            Double openamount = 0.0;
            if (informationEntities != null) {
                openamount = Double.parseDouble(informationEntities.get(0).getOpeningamount());
                return openamount.toString().trim();
            }
            return null;
        }
    }

    public class GetPayable extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo(currentdate);
            Double dayend = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++) {
                dayend = Double.parseDouble(cashboxEntities.get(i).getDayend());
            }

            return dayend.toString();
        }
    }

    public class GetCreditSells extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSell("Sales", "Credit", currentdate);
            Double total_credit = 0.0;
            for (int i = 0; i < newtransactionEntities.size(); i++) {
                total_credit = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + total_credit;
            }

            return total_credit.toString().trim();
        }
    }

    public class GetOpeningCash extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
//            cal.add(Calendar.DAY_OF_YEAR, -1);
//            prev = s.format(cal.getTime());

            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();
            int mon = today.month +1;
            int day = today.monthDay -1;
            prev = day + "-"+mon +"-"+today.year;


            List<CashboxEntity> cashboxEntities = cashboxDao.getCashboxinfo(prev);
            Double cashop = 0.0;
            for (int i = 0; i < cashboxEntities.size(); i++) {
                cashop = Double.parseDouble(cashboxEntities.get(i).getDayend());

            }

            return cashop.toString();
        }
    }

    public class Getwithdrawinfo extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Sales Return", "Credit", currentdate);
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Purchase Return", "Cash", currentdate);
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Purchase Return", "Credit", currentdate);
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Past Receivable", "PAID-OUT", currentdate);
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
            //String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
            List<AdjustEntity> adjustEntities = adjustDao.getAdjust("Past Payable", "PAID-OUT", currentdate);
            Double pastpayable = 0.0;
            for (int i = 0; i < adjustEntities.size(); i++) {
                pastpayable = Double.parseDouble(adjustEntities.get(i).getClientamount()) + pastpayable;
            }

            return pastpayable.toString();
        }

    }


}





