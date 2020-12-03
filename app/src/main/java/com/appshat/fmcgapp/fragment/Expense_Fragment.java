package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.ExpenseDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DB.Database;

public class Expense_Fragment extends Fragment {
    EditText rentET, salaryET, otherET;
    Button expensesaveBtn;

    ExpenseDao expenseDBdao;
    Database expenseDB;

    String rent, salary, others;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_, container, false);


        //database
        expenseDB = Room.databaseBuilder( getActivity(), Database.class,"expense" ).allowMainThreadQueries().build();
        expenseDBdao = expenseDB.getExpenseDao();


        rentET = view.findViewById(R.id.rentET_id);
        salaryET = view.findViewById(R.id.salaryET_id);
        otherET = view.findViewById(R.id.othersET_id);
        expensesaveBtn = view.findViewById(R.id.expenseBtn_id);

        expensesaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent = rentET.getText().toString();
                salary=salaryET.getText().toString();
                others=otherET.getText().toString();

            }
        });
        return view;
    }
}