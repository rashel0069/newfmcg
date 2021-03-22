package com.appshat.kherokhata.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.HistoryEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class History_Fragment extends Fragment {
    TextView prevDate,prevSales,prevCrSales,prevRecive,prevPay,prevCloaseCash,oprenCash;
    String previousday;
    Context context;
    Calendar startDate,endDate;
    Resources resources;
    public static final String DATE_DIALOG_1 = "datePicker1";
    static TextView fromDate,toDate;
    private static int mYear1,mMonth1,mDay1;
    public static final String DATE_DIALOG_2 = "datePicker2";
    private static int mYear2,mMonth2, mDay2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_, container, false);
        prevDate = view.findViewById( R.id.prev_day_dateId );
        prevSales = view.findViewById( R.id.pre_day_sale );
        prevCrSales = view.findViewById( R.id.pre_day_creSale );
        prevRecive = view.findViewById( R.id.pre_day_receivable );
        prevPay = view.findViewById( R.id.pre_day_payable );
        prevCloaseCash = view.findViewById( R.id.pre_day_closecash );
        oprenCash = view.findViewById( R.id.pre_day_openingcash );
        fromDate = view.findViewById( R.id.from_date_id );
        toDate = view.findViewById( R.id.to_date_id );

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        cal.add(Calendar.DAY_OF_YEAR, -1);
        previousday = s.format(cal.getTime());
        prevDate.setText( previousday );
        cal.clear();
        startDate = Calendar.getInstance();

        fromDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment1 = new DatePickerFragment1();
                dialogFragment1.show( getActivity().getSupportFragmentManager(), DATE_DIALOG_1 );
            }
        } );

        toDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment2 = new DatePickerFragment2();
                dialogFragment2.show( getActivity().getSupportFragmentManager(), DATE_DIALOG_2 );
            }
        } );


        //previous day history
        PreviousDayHistory();

        return view;
    }
    public static class DatePickerFragment1 extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Date Time NOW
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it

            DatePickerDialog date1 = new DatePickerDialog(getActivity(), this, year, month, day);
            date1.getDatePicker().setMaxDate( System.currentTimeMillis() );
            return date1;
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // get selected date
            Calendar ca = Calendar.getInstance();
            ca.set( Calendar.YEAR,year );
            ca.set( Calendar.MONTH,month );
            ca.set( Calendar.DAY_OF_MONTH,day );
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            String date = s.format( ca.getTime() );
            // show selected date to date button
            fromDate.setText( date );
            ca.clear();
        }
    }
    public static class DatePickerFragment2 extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            //Date Time NOW
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog date2 = new DatePickerDialog(getActivity(), this, year, month, day);
            date2.getDatePicker().setMaxDate( System.currentTimeMillis() );
            return date2;
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // get selected date
            Calendar cb = Calendar.getInstance();
            cb.set( Calendar.YEAR,year);
            cb.set( Calendar.MONTH,month );
            cb.set( Calendar.DAY_OF_MONTH,day );
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            String date = s.format( cb.getTime() );
            // show selected date to date button
            toDate.setText( date );
            cb.clear();
        }
    }

    private void PreviousDayHistory() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    HistoryEntity mhistory = Databaseroom.getDatabaseroomref( getContext() ).getHistory().findbyId( previousday );
                    prevSales.setText( mhistory.getTotalsales().toString() );
                    prevCrSales.setText( mhistory.getCreditsales().toString() );
                    prevRecive.setText( mhistory.getCreditsales().toString() );
                    prevPay.setText( mhistory.getCreditpurchase().toString() );
                    prevCloaseCash.setText( mhistory.getDayendbalance().toString() );
                    oprenCash.setText( mhistory.getOpeningammount().toString() );
                }catch (Exception e){

                }
            }
        } ).start();
    }
}