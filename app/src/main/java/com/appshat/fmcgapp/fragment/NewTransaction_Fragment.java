package com.appshat.fmcgapp.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.R;

import com.appshat.fmcgapp.Room.DAO.NewtransactionDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;
import com.appshat.fmcgapp.Room.model.TransactionViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;


public class NewTransaction_Fragment extends Fragment {
    Spinner accspinner, transspinner;
    EditText cnameET, cmblnumET, camountET;
    TextView timedateTV;
    Button newtransBTN;
    NewtransactionDao newtransactionDBdao;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Calendar cal;
    Databaseroom newtransactionDB;
    TransactionViewModel transactionViewModel;
    String accounttype, transactiontype, clientname, clientmobile,clientamount, duedate;
    String currentdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_transaction_, container, false);
        // Inflate the layout for this fragment

        accspinner = view.findViewById(R.id.newaccountspinner_id);
        transspinner = view.findViewById(R.id.newtransactionspinner_id);
        timedateTV = view.findViewById(R.id.dateTV_id);
        newtransBTN = view.findViewById(R.id.newtranssave_id);
        camountET=view.findViewById(R.id.amountET_id);
        cmblnumET=view.findViewById(R.id.clientmobilenumberET_id);
        cnameET=view.findViewById(R.id.customernameET_id);

        transactionViewModel = ViewModelProviders.of( getActivity() ).get( TransactionViewModel.class );

        //Current date
        currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

//for spinner set position
        accspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accounttype = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "please choose an item", Toast.LENGTH_SHORT).show();
            }
        });

        transspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transactiontype = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getContext(), "Please choose an item", Toast.LENGTH_SHORT).show();
            }
        });

        newtransBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientname = cnameET.getText().toString();
                clientmobile = cmblnumET.getText().toString();
                duedate = timedateTV.getText().toString();
                clientamount=camountET.getText().toString();

                if (accounttype != null && transactiontype != null && clientname != null && clientmobile != null && clientamount != null && duedate != null) {

                    NewtransactionEntity newtransactionEntity = new NewtransactionEntity(accounttype,transactiontype,clientname,clientmobile,clientamount,duedate,currentdate);

                    transactionViewModel.intertTrans(newtransactionEntity);

                    Home_Fragment fragment1 = new Home_Fragment();
                    FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.framelayout_container_id, fragment1);
                    ft1.commit();

                    Toast.makeText(getContext(), "Registraton Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Mobile and Password field is empty", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getContext(), accounttype + " " + transactiontype, Toast.LENGTH_SHORT).show();
            }
        });
        timedateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month;
                Log.d(TAG, "onDateSet: dd/mm/yyyy:  " + "/" + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                String hour = getResources().getString(R.string.alerm_time_hour);
                String minutes = getResources().getString(R.string.alerm_time_second);
                ///set time for alerm notification=============================
                cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.clear();
                cal.set(year,month,day,parseInt(hour),parseInt(minutes));
                timedateTV.setText(date);

            }
        };

        return view;
    }

}