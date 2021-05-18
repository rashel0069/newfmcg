package com.appshat.kherokhata.OldAcrivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.CashboxDao;
import com.appshat.kherokhata.Room.DAO.InformationDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.CashboxEntity;
import com.appshat.kherokhata.Room.model.CashBoxViewModel;
import com.appshat.kherokhata.fragment.Home_Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExampleDialog extends AppCompatDialogFragment {

    EditText newOpenAmount;
    String dayend, withdrawal, deposit, datetime;
    CashboxDao cashboxDBdao;
    InformationDao informationDbDao;
    Databaseroom cashboxDB;
    CashBoxViewModel cashBoxViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null, false);
        builder.setView(view).setTitle("Opening Amounts")
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Calendar cal = Calendar.getInstance();
//                SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
//                cal.add(Calendar.DAY_OF_YEAR, -1);
//                datetime = s.format(cal.getTime());
                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                int mon = today.month +1;
                int day = today.monthDay -1;
                datetime = day + "-0"+mon +"-"+today.year;
                //database
                cashBoxViewModel = ViewModelProviders.of(getActivity()).get(CashBoxViewModel.class);
                dayend = newOpenAmount.getText().toString().trim();
                if (!TextUtils.isEmpty(newOpenAmount.getText().toString().trim())) {
                    CashboxEntity cashboxEntity = new CashboxEntity(datetime, dayend, "0", "0");
                    cashBoxViewModel.insertCashbox(cashboxEntity);
                    Home_Fragment home_fragment = new Home_Fragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.framelayout_container_id, home_fragment);
                    transaction.commit();
                    Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newOpenAmount = view.findViewById(R.id.openingamount_new);
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int mon = today.month +1;
        int day = today.monthDay -1;
        datetime = day + "-0"+mon +"-"+today.year;
        //database
        cashBoxViewModel = ViewModelProviders.of(getActivity()).get(CashBoxViewModel.class);
        return builder.create();
    }

}
