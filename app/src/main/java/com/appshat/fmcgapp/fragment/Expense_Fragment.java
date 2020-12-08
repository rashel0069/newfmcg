package com.appshat.fmcgapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.text.TextUtils;
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
import com.appshat.fmcgapp.Room.model.ExpenseViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Expense_Fragment extends Fragment {
    EditText rentET, salaryET, otherET;
    Button expensesaveBtn;

    ExpenseDao expenseDBdao;
    Databaseroom expenseDB;
    ExpenseViewModel expenseViewModel;
    String rent, salary, others;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_, container, false);


//        //database
//        expenseDB = Room.databaseBuilder( getActivity(), Databaseroom.class,"expense" ).allowMainThreadQueries().build();
//        expenseDBdao = expenseDB.getExpenseDao();
        expenseViewModel = ViewModelProviders.of( getActivity() ).get( ExpenseViewModel.class );
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());

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

                if (!TextUtils.isEmpty(rentET.getText()) && !TextUtils.isEmpty(salaryET.getText()) && !TextUtils.isEmpty(otherET.getText())) {
                    expensecalcu();

                } else if (!TextUtils.isEmpty(rentET.getText()) && !TextUtils.isEmpty(salaryET.getText())){
                    others = "0";
                   expensecalcu();

                }else if (!TextUtils.isEmpty(rentET.getText()) && !TextUtils.isEmpty(otherET.getText())){
                    salary = "0";
                    expensecalcu();
                }else if (!TextUtils.isEmpty(salaryET.getText()) && !TextUtils.isEmpty(otherET.getText())){
                    rent ="0";
                    expensecalcu();
                }else if (!TextUtils.isEmpty(rentET.getText())){
                    others = "0";
                    salary = "0";
                    expensecalcu();
                }else if (!TextUtils.isEmpty(salaryET.getText()) ){
                    others = "0";
                    rent ="0";
                    expensecalcu();
                }else if (!TextUtils.isEmpty(otherET.getText())){
                    rent ="0";
                    salary = "0";
                    expensecalcu();
                }else {
                    Toast.makeText( getContext(), "Please Fill up one field", Toast.LENGTH_SHORT ).show();
                }

            }

        });

        return view;
    }

    private void expensecalcu(){
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());

        ExpenseEntity expenseEntity = new ExpenseEntity(rent, salary, others,currentdate);
        expenseViewModel.insertExpense(expenseEntity);

        Home_Fragment fragment1 = new Home_Fragment();
        FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.framelayout_container_id, fragment1);
        ft1.commit();

        Toast.makeText( getContext(), "successfully insert", Toast.LENGTH_SHORT ).show();

    }
}