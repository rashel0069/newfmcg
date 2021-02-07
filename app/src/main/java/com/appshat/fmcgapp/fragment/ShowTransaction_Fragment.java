package com.appshat.fmcgapp.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;
import com.appshat.fmcgapp.Room.ENTITY.ExpenseEntity;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;
import com.appshat.fmcgapp.Room.model.AdjustViewModel;
import com.appshat.fmcgapp.Room.model.ExpenseViewModel;
import com.appshat.fmcgapp.Room.model.TransactionViewModel;
import com.appshat.fmcgapp.adapter.ExpenceListAdapter;
import com.appshat.fmcgapp.adapter.PayReceiveListAdapter;
import com.appshat.fmcgapp.adapter.TransactionListAdapter;

import java.util.List;


public class ShowTransaction_Fragment extends Fragment {
    EditText searchmbl;
    Button transallBtn,expBtn,recpayBtn, allTrans;
    ImageButton searchBtn,contractBtn;
    RecyclerView recyclerView;
    TransactionListAdapter transactionListAdapter;
    ExpenceListAdapter expenceListAdapter;
    PayReceiveListAdapter payReceiveListAdapter;
    TransactionViewModel transactionViewModel;
    ExpenseViewModel expenseViewModel;
    AdjustViewModel payeceiveViewModel;
    Context context;
    Resources resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_transaction_, container, false);

        searchmbl = view.findViewById(R.id.smblET_id);
        transallBtn = view.findViewById(R.id.transallBtn_id);
        allTrans = view.findViewById(R.id.allBtn_id);
        recyclerView = view.findViewById(R.id.rv_id);
        expBtn = view.findViewById(R.id.expBtn_id);
        recpayBtn = view.findViewById(R.id.payrecBtn_id);


        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setHasFixedSize( true );
        recyclerView.setNestedScrollingEnabled( false );

        transactionViewModel = ViewModelProviders.of( this ).get( TransactionViewModel.class );
        expenseViewModel = ViewModelProviders.of( this ).get( ExpenseViewModel.class );
        payeceiveViewModel = ViewModelProviders.of( this ).get( AdjustViewModel.class );

        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            searchmbl.setHint(resources.getString(R.string.scontacthint));
            transallBtn.setText(resources.getString(R.string.todaytrans));
            expBtn.setText(resources.getString(R.string.expense));
            recpayBtn.setText(resources.getString(R.string.payrec));
            allTrans.setText(resources.getString(R.string.alltrans));

        } else {
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            searchmbl.setHint(resources.getString(R.string.scontacthint));
            transallBtn.setText(resources.getString(R.string.todaytrans));
            expBtn.setText(resources.getString(R.string.expense));
            recpayBtn.setText(resources.getString(R.string.payrec));
            allTrans.setText(resources.getString(R.string.alltrans));
        }

        //show all
        showAll();
        // today transactions
        transallBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionViewModel.getmTodayTrans().observe( getViewLifecycleOwner(), new Observer<List<NewtransactionEntity>>() {
                    @Override
                    public void onChanged(List<NewtransactionEntity> newtransactionEntities) {
                        transactionListAdapter.setTrans( newtransactionEntities );
                    }
                } );
            }
        } );
        expBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expenceListAdapter = new ExpenceListAdapter();
                recyclerView.setAdapter( expenceListAdapter );
                showExpence();
            }
        } );

        recpayBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayrecive();
            }
        } );

        //All transaction
        allTrans.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAll();
            }
        } );

        return view;

    }
    //show pay recive
    private void showPayrecive(){
        payReceiveListAdapter = new PayReceiveListAdapter();
        recyclerView.setAdapter( payReceiveListAdapter );
        payeceiveViewModel.getAllAdjust().observe( getViewLifecycleOwner(), new Observer<List<AdjustEntity>>() {
            @Override
            public void onChanged(List<AdjustEntity> adjustEntities) {
                payReceiveListAdapter.setPayRecv( adjustEntities );
            }
        } );
    }

    private void showAll(){
        transactionListAdapter = new TransactionListAdapter();
        recyclerView.setAdapter( transactionListAdapter );
        transactionViewModel.getmAllTrans().observe( getViewLifecycleOwner(), new Observer<List<NewtransactionEntity>>() {
            @Override
            public void onChanged(List<NewtransactionEntity> newtransactionEntities) {
                transactionListAdapter.setTrans( newtransactionEntities );
            }
        } );
    }

    private void showExpence(){
        expenseViewModel.getmAllExpence().observe( getViewLifecycleOwner(), new Observer<List<ExpenseEntity>>() {
            @Override
            public void onChanged(List<ExpenseEntity> expenseEntities) {
                expenceListAdapter.setExpance( expenseEntities );
            }
        } );
    }
}