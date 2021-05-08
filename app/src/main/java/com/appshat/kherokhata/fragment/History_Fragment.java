package com.appshat.kherokhata.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.OldAcrivity.MainActivity;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.HistoryEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class History_Fragment extends Fragment {
    public static final String DATE_DIALOG_1 = "datePicker1";
    public static final String DATE_DIALOG_2 = "datePicker2";
    static TextView fromDate, toDate;
    LinearLayout pickdate1, pickdate2;
    private static int mYear1, mMonth1, mDay1;
    private static int mYear2, mMonth2, mDay2;
    TextView hstt,preday,shst,chst,rhst,phst,ohst,clhst, prevDate, prevSales, prevCrSales, prevRecive, prevPay, prevCloaseCash, oprenCash,
     preweek,shwst,chwst,rhwst,phwst,ohwst,clwhst, prevwSales, prevwCrSales, prevwRecive, prevwPay, prevwCloaseCash, openwCash,from,to,
    lastmonth,last,nd;

    String previousday;
    Context context;
    Calendar startDate, endDate;
    Resources resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history__nav_, container, false);

        hstt = view.findViewById(R.id.hst_title_id);
        preday=view.findViewById(R.id.pre_day_historyTV_id);
        shst=view.findViewById(R.id.sales_hstTV_id);
        chst=view.findViewById(R.id.cs_hstTV_id);
        rhst=view.findViewById(R.id.rcv_hstTV_id);
        phst=view.findViewById(R.id.pay_hstTV_id);
        ohst=view.findViewById(R.id.op_hstTV_id);
        clhst=view.findViewById(R.id.closing_hstTV_id);
        prevDate = view.findViewById(R.id.prev_day_dateId);
        prevSales = view.findViewById(R.id.pre_day_sale);
        prevCrSales = view.findViewById(R.id.pre_day_creSale);
        prevRecive = view.findViewById(R.id.pre_day_receivable);
        prevPay = view.findViewById(R.id.pre_day_payable);
        prevCloaseCash = view.findViewById(R.id.pre_day_closecash);
        oprenCash = view.findViewById(R.id.pre_day_openingcash);
        fromDate = view.findViewById(R.id.from_date_id);
        toDate = view.findViewById(R.id.to_date_id);
        pickdate1 = view.findViewById(R.id.date_pick_id1);
        pickdate2 = view.findViewById(R.id.date_pick_id2);
        preweek=view.findViewById(R.id.pre_week_historyTV_id);
        from=view.findViewById(R.id.hitory_from_id);
        to=view.findViewById(R.id.hitory_to_id);
        shwst=view.findViewById(R.id.sales_whstTV_id);
        chwst=view.findViewById(R.id.cs_whstTV_id);
        rhwst=view.findViewById(R.id.rc_whsTV_id);
        phwst=view.findViewById(R.id.pa_whstTV_id);
        ohwst=view.findViewById(R.id.op_whstTV_id);
        clwhst=view.findViewById(R.id.cl_whsTV_id);
        prevwSales = view.findViewById(R.id.pre_week_sale);
        prevwCrSales = view.findViewById(R.id.pre_week_creditsale);
        prevwRecive = view.findViewById(R.id.pre_week_ra);
        prevwPay = view.findViewById(R.id.pre_week_pa);
        prevwCloaseCash = view.findViewById(R.id.pre_week_cl);
        openwCash = view.findViewById(R.id.pre_week_op);

        lastmonth=view.findViewById(R.id.pre_month_historyTV_id);
        last=view.findViewById(R.id.last_month_date);
        nd=view.findViewById(R.id.ndt_hsTV_id);


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        cal.add(Calendar.DAY_OF_YEAR, -1);
        previousday = s.format(cal.getTime());
        prevDate.setText(previousday);
        cal.clear();
        startDate = Calendar.getInstance();


        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();

            hstt.setText(resources.getString(R.string.history));
            preday.setText(resources.getString(R.string.previous_day_history));
            shst.setText(resources.getString(R.string.sales));
            chst.setText(resources.getString(R.string.creditsales));
            rhst.setText(resources.getString(R.string.ra));
            phst.setText(resources.getString(R.string.pa));
            ohst.setText(resources.getString(R.string.opening));
            clhst.setText(resources.getString(R.string.closingcash));
            prevSales.setText(resources.getString(R.string.amounthint));
            prevCrSales.setText(resources.getString(R.string.amounthint));
            prevRecive.setText(resources.getString(R.string.amounthint));
            prevPay.setText(resources.getString(R.string.amounthint));
            prevCloaseCash.setText(resources.getString(R.string.amounthint));
            oprenCash.setText(resources.getString(R.string.amounthint));
            preweek.setText(resources.getString(R.string.previous_week_history));
            from.setText(resources.getString(R.string.from));
            to.setText(resources.getString(R.string.to));
            shwst.setText(resources.getString(R.string.sales));;
            chwst.setText(resources.getString(R.string.creditsales));
            rhwst.setText(resources.getString(R.string.ra));
            phwst.setText(resources.getString(R.string.pa));
            ohwst.setText(resources.getString(R.string.opening));
            clwhst.setText(resources.getString(R.string.closingcash));
            prevwSales.setText(resources.getString(R.string.amounthint));
            prevwCrSales.setText(resources.getString(R.string.amounthint));
            prevwRecive.setText(resources.getString(R.string.amounthint));
            prevwPay.setText(resources.getString(R.string.amounthint));
            prevwCloaseCash.setText(resources.getString(R.string.amounthint));
            openwCash.setText(resources.getString(R.string.amounthint));
            lastmonth.setText(resources.getString(R.string.last_month_history));
            last.setText(resources.getString(R.string.last_month));
            nd.setText(resources.getString(R.string.nodata));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();

            hstt.setText(resources.getString(R.string.history));
            preday.setText(resources.getString(R.string.previous_day_history));
            shst.setText(resources.getString(R.string.sales));
            chst.setText(resources.getString(R.string.creditsales));
            rhst.setText(resources.getString(R.string.ra));
            phst.setText(resources.getString(R.string.pa));
            ohst.setText(resources.getString(R.string.opening));
            clhst.setText(resources.getString(R.string.closingcash));
            prevSales.setText(resources.getString(R.string.amounthint));
            prevCrSales.setText(resources.getString(R.string.amounthint));
            prevRecive.setText(resources.getString(R.string.amounthint));
            prevPay.setText(resources.getString(R.string.amounthint));
            prevCloaseCash.setText(resources.getString(R.string.amounthint));
            oprenCash.setText(resources.getString(R.string.amounthint));
            preweek.setText(resources.getString(R.string.previous_week_history));
            from.setText(resources.getString(R.string.from));
            to.setText(resources.getString(R.string.to));
            shwst.setText(resources.getString(R.string.sales));;
            chwst.setText(resources.getString(R.string.creditsales));
            rhwst.setText(resources.getString(R.string.ra));
            phwst.setText(resources.getString(R.string.pa));
            ohwst.setText(resources.getString(R.string.opening));
            clwhst.setText(resources.getString(R.string.closingcash));
            prevwSales.setText(resources.getString(R.string.amounthint));
            prevwCrSales.setText(resources.getString(R.string.amounthint));
            prevwRecive.setText(resources.getString(R.string.amounthint));
            prevwPay.setText(resources.getString(R.string.amounthint));
            prevwCloaseCash.setText(resources.getString(R.string.amounthint));
            openwCash.setText(resources.getString(R.string.amounthint));
            lastmonth.setText(resources.getString(R.string.last_month_history));
            last.setText(resources.getString(R.string.last_month));
            nd.setText(resources.getString(R.string.nodata));

        }

        pickdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment1 = new DatePickerFragment1();
                dialogFragment1.show(getActivity().getSupportFragmentManager(), DATE_DIALOG_1);
            }
        });

        pickdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment2 = new DatePickerFragment2();
                dialogFragment2.show(getActivity().getSupportFragmentManager(), DATE_DIALOG_2);
            }
        });


        //previous day history
        PreviousDayHistory();

        return view;
    }

    private void PreviousDayHistory() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HistoryEntity mhistory = Databaseroom.getDatabaseroomref(getContext()).getHistory().findbyId(previousday);
                    prevSales.setText(mhistory.getTotalsales().toString());
                    prevCrSales.setText(mhistory.getCreditsales().toString());
                    prevRecive.setText(mhistory.getCreditsales().toString());
                    prevPay.setText(mhistory.getCreditpurchase().toString());
                    prevCloaseCash.setText(mhistory.getDayendbalance().toString());
                    oprenCash.setText(mhistory.getOpeningammount().toString());
                } catch (Exception e) {

                }
            }
        }).start();
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
            date1.getDatePicker().setMaxDate(System.currentTimeMillis());
            return date1;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // get selected date
            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.YEAR, year);
            ca.set(Calendar.MONTH, month);
            ca.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            String date = s.format(ca.getTime());
            // show selected date to date button
            fromDate.setText(date);
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
            date2.getDatePicker().setMaxDate(System.currentTimeMillis());
            return date2;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // get selected date
            Calendar cb = Calendar.getInstance();
            cb.set(Calendar.YEAR, year);
            cb.set(Calendar.MONTH, month);
            cb.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            String date = s.format(cb.getTime());
            // show selected date to date button
            toDate.setText(date);
            cb.clear();
        }
    }
}