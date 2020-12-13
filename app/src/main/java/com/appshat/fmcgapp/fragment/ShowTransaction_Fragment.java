package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.appshat.fmcgapp.R;


public class ShowTransaction_Fragment extends Fragment {
    EditText searchmbl;
    Button searchBtn, showallBtn;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_transaction_, container, false);

        searchmbl = view.findViewById(R.id.smblET_id);
        searchBtn = view.findViewById(R.id.searchBtn_id);
        showallBtn = view.findViewById(R.id.showallBtn_id);
        recyclerView = view.findViewById(R.id.showallBtn_id);

        return view;

    }
}