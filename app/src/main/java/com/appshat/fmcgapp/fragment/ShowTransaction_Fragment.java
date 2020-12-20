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

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;
import com.appshat.fmcgapp.Room.model.TransactionViewModel;
import com.appshat.fmcgapp.adapter.TransactionListAdapter;

import java.util.List;


public class ShowTransaction_Fragment extends Fragment {
    EditText searchmbl;
    Button searchBtn, transallBtn,expBtn,recpayBtn;
    RecyclerView recyclerView;
    TransactionListAdapter transactionListAdapter;
    TransactionViewModel transactionViewModel;
    Context context;
    Resources resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_transaction_, container, false);

        searchmbl = view.findViewById(R.id.smblET_id);
        searchBtn = view.findViewById(R.id.searchBtn_id);
        transallBtn = view.findViewById(R.id.transallBtn_id);
        recyclerView = view.findViewById(R.id.rv_id);
        expBtn = view.findViewById(R.id.expBtn_id);
        recpayBtn = view.findViewById(R.id.payrecBtn_id);
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setHasFixedSize( true );
        recyclerView.setNestedScrollingEnabled( false );
        transactionListAdapter = new TransactionListAdapter();
        recyclerView.setAdapter( transactionListAdapter );

        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            searchmbl.setHint(resources.getString(R.string.scontacthint));
            searchBtn.setText(resources.getString(R.string.search));
            transallBtn.setText(resources.getString(R.string.todaytrans));
            expBtn.setText(resources.getString(R.string.expense));
            recpayBtn.setText(resources.getString(R.string.payrec));

        } else {
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            searchmbl.setHint(resources.getString(R.string.scontacthint));
            searchBtn.setText(resources.getString(R.string.search));
            transallBtn.setText(resources.getString(R.string.todaytrans));
            expBtn.setText(resources.getString(R.string.expense));
            recpayBtn.setText(resources.getString(R.string.payrec));
        }

        transactionViewModel = ViewModelProviders.of( this ).get( TransactionViewModel.class );
        transactionViewModel.getmAllTrans().observe( getViewLifecycleOwner(), new Observer<List<NewtransactionEntity>>() {
            @Override
            public void onChanged(List<NewtransactionEntity> newtransactionEntities) {
                transactionListAdapter.setTrans( newtransactionEntities );
            }
        } );

        return view;

    }
}