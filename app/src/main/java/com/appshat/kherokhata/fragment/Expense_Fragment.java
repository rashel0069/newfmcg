package com.appshat.kherokhata.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.kherokhata.Helper;
import com.appshat.kherokhata.Localhelper;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.ExpenseDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.ExpenseEntity;
import com.appshat.kherokhata.Room.model.ExpenseViewModel;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Expense_Fragment extends Fragment {
    EditText rentET, salaryET, otherET;
    TextView expensetitle, re, se, oe;
    Button expensesaveBtn;
    Context context;
    Resources resources;
    ExpenseDao expenseDBdao;
    Databaseroom expenseDB;
    ExpenseViewModel expenseViewModel;
    String rent, salary, others;
    AdView mLogAdview;
    InterstitialAd interstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_, container, false);

//        //database
//        expenseDB = Room.databaseBuilder( getActivity(), Databaseroom.class,"expense" ).allowMainThreadQueries().build();
//        expenseDBdao = expenseDB.getExpenseDao();
        expenseViewModel = ViewModelProviders.of(getActivity()).get(ExpenseViewModel.class);
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());

        expensetitle = view.findViewById(R.id.expenseTV_id);
        re = view.findViewById(R.id.rextTV_id);
        rentET = view.findViewById(R.id.rentET_id);
        se = view.findViewById(R.id.salaryTV_id);
        salaryET = view.findViewById(R.id.salaryET_id);
        oe = view.findViewById(R.id.otherTV_id);
        otherET = view.findViewById(R.id.othersET_id);
        expensesaveBtn = view.findViewById(R.id.expensesaveBtn_id);
     //   mLogAdview=view.findViewById(R.id.expenseView_id);

        //banner ad
//        MobileAds.initialize( getActivity(),"ca-app-pub-3940256099942544~3347511713" );
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mLogAdview.loadAd( adRequest );

        //InterstitialAd
//        interstitialAd = new InterstitialAd( getActivity() );
//        //testing add key
//        interstitialAd.setAdUnitId( "ca-app-pub-3940256099942544/1033173712" );
//        interstitialAd.loadAd(new AdRequest.Builder().build());
//        interstitialAd.setAdListener( new AdListener(){
//            @Override
//            public void onAdClosed() {
//                interstitialAd.loadAd( new AdRequest.Builder().build() );
//            }
//        } );



        //language setter
        if (!Helper.getBangla()){
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(),"en");
            resources = context.getResources();
            expensetitle.setText(resources.getString(R.string.expensetransaction));
            rentET.setHint(resources.getString(R.string.renthint));
            re.setText(resources.getString(R.string.rent));
            salaryET.setHint(resources.getString(R.string.salaryhint));
            se.setText(resources.getString(R.string.salary));
            otherET.setHint(resources.getString(R.string.otherhint));
            oe.setText(resources.getString(R.string.others));
            expensesaveBtn.setText(resources.getString(R.string.save));
        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(),"bn");
            resources = context.getResources();
            expensetitle.setText(resources.getString(R.string.expensetransaction));
            rentET.setHint(resources.getString(R.string.renthint));
            re.setText(resources.getString(R.string.rent));
            salaryET.setHint(resources.getString(R.string.salaryhint));
            se.setText(resources.getString(R.string.salary));
            otherET.setHint(resources.getString(R.string.otherhint));
            oe.setText(resources.getString(R.string.others));
            expensesaveBtn.setText(resources.getString(R.string.save));
        }
        expensesaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent = rentET.getText().toString();
                salary = salaryET.getText().toString();
                others = otherET.getText().toString();

                if (!TextUtils.isEmpty(rentET.getText()) && !TextUtils.isEmpty(salaryET.getText()) && !TextUtils.isEmpty(otherET.getText())) {
                    expensecalcu();

                } else if (!TextUtils.isEmpty(rentET.getText()) && !TextUtils.isEmpty(salaryET.getText())) {
                    others = "0";
                    expensecalcu();

                } else if (!TextUtils.isEmpty(rentET.getText()) && !TextUtils.isEmpty(otherET.getText())) {
                    salary = "0";
                    expensecalcu();
                } else if (!TextUtils.isEmpty(salaryET.getText()) && !TextUtils.isEmpty(otherET.getText())) {
                    rent = "0";
                    expensecalcu();
                } else if (!TextUtils.isEmpty(rentET.getText())) {
                    others = "0";
                    salary = "0";
                    expensecalcu();
                } else if (!TextUtils.isEmpty(salaryET.getText())) {
                    others = "0";
                    rent = "0";
                    expensecalcu();
                } else if (!TextUtils.isEmpty(otherET.getText())) {
                    rent = "0";
                    salary = "0";
                    expensecalcu();
                } else {
                    Toast.makeText(getContext(), "Please fill up one field", Toast.LENGTH_SHORT).show();
                }

            }

        });

        return view;
    }

    private void expensecalcu() {
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());

        ExpenseEntity expenseEntity = new ExpenseEntity(rent, salary, others, currentdate);
        expenseViewModel.insertExpense(expenseEntity);

        Home_Fragment fragment1 = new Home_Fragment();
        FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.framelayout_container_id, fragment1);
        ft1.commit();

        Toast.makeText(getContext(), "successfully insert", Toast.LENGTH_SHORT).show();

    }
}