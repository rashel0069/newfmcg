package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.ExpenseDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.ExpenseEntity;

public class Expense_Fragment extends Fragment {
    EditText rentET, salaryET, otherET;
    Button expensesaveBtn;

    ExpenseDao expenseDBdao;
    Databaseroom expenseDB;

    String rent, salary, others;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_, container, false);


        //database
        expenseDB = Room.databaseBuilder( getActivity(), Databaseroom.class,"expense" ).allowMainThreadQueries().build();
        expenseDBdao = expenseDB.getExpenseDao();


        rentET = view.findViewById(R.id.rentET_id);
        salaryET = view.findViewById(R.id.salaryET_id);
        otherET = view.findViewById(R.id.othersET_id);
        expensesaveBtn = view.findViewById(R.id.expensesaveBtn_id);

        expensesaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rent = rentET.getText().toString();
                salary = salaryET.getText().toString();
                others = otherET.getText().toString();
                if (rent != null && salary != null && others != null) {
                    ExpenseEntity expenseEntity = new ExpenseEntity(rent, salary, others);
                    expenseDBdao.insert(expenseEntity);

                    Home_Fragment fragment1 = new Home_Fragment();
                    FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.framelayout_container_id, fragment1);
                    ft1.commit();

                    Toast.makeText( getContext(), "successfully insert", Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText( getContext(), "please fill up the fields", Toast.LENGTH_SHORT ).show();
                }

            }

        });

        return view;
    }
}