package com.appshat.fmcgapp.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.DAO.AdjustDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;
import com.appshat.fmcgapp.Room.model.AdjustViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

public class Adjust_Balance_Fragment extends Fragment {

    Spinner accountspinner, transactionspinner;
    EditText adjustamountET, clientmobileET;
    TextView clientnameTV,cmblTV,duepaydateTV, drpTV, clientnameET, amountTV;
    Button adjustsaveBtn;
    Context context;
    Resources resources;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Calendar cal;
    AdjustDao adjustDBdao;
    Databaseroom adjustDB;
    String currentdate;
    String accounttype, transactiontype, clientname, clientmobile, clientamount, date;
    AdjustViewModel adjustViewModel;
    ImageView phoneContact;
    static final int PICK_CONTACT = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
                    Cursor c = getContext().getContentResolver().query(contactData, projection, null, null, null, null);
                    c.moveToFirst();
                    int numberindex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = c.getString(numberindex);
                    clientmobileET.setText(number);
                    int nameindex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = c.getString(nameindex);
                    clientnameET.setText(name);

                }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adjust__balance_, container, false);

        accountspinner = view.findViewById(R.id.balancetypespinner_id);
        transactionspinner = view.findViewById(R.id.transitiontypespinner_id);
        adjustamountET = view.findViewById(R.id.adjustamountET_id);
        clientnameTV=view.findViewById(R.id.clientnameTV_id);
        clientnameET = view.findViewById(R.id.clientname_id);
        cmblTV=view.findViewById(R.id.clientmblTV_id);
        clientmobileET = view.findViewById(R.id.cmblTV_id);
        duepaydateTV = view.findViewById(R.id.currentdateTV_id);
        drpTV = view.findViewById(R.id.drpTV_id);
        adjustsaveBtn = view.findViewById(R.id.adjustsaveBtn_id);
       // amountTV = view.findViewById(R.id.amountsTV_id);
        phoneContact = view.findViewById(R.id.phoneContact_id2);

        //aspinner
        String[] cas = getResources().getStringArray(R.array.returndata);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
        accountspinner.setAdapter(adapter);

        String[] cas2 = getResources().getStringArray( R.array.trans2 );
        ArrayAdapter adapter1 = new ArrayAdapter( getContext(),R.layout.myarrylistsample,cas2 );
        transactionspinner.setAdapter( adapter1 );

//        //database

        adjustViewModel = ViewModelProviders.of(getActivity()).get(AdjustViewModel.class);
        //Date time
        currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());

        phoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://contacts");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

//language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            drpTV.setText(resources.getString(R.string.adjust));
            clientnameTV.setText(resources.getString(R.string.customerName));
            clientnameET.setHint(resources.getString(R.string.customernamehint));
            cmblTV.setText(resources.getString(R.string.hint1));
            clientmobileET.setHint(resources.getString(R.string.customernumberhint));
         //   amountTV.setText(resources.getString(R.string.amounts));
          //  adjustamountET.setHint(resources.getString(R.string.amounthint));
          //  duepaydateTV.setHint(resources.getString(R.string.date));
            adjustsaveBtn.setText(resources.getString(R.string.save));



        } else {
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            drpTV.setText(resources.getString(R.string.adjust));
            clientnameTV.setText(resources.getString(R.string.customerName));
            clientnameET.setHint(resources.getString(R.string.customernamehint));
            cmblTV.setText(resources.getString(R.string.hint1));
            clientmobileET.setHint(resources.getString(R.string.customernumberhint));
        //    amountTV.setText(resources.getString(R.string.amounts));
         //   adjustamountET.setHint(resources.getString(R.string.amounthint));
         //   duepaydateTV.setHint(resources.getString(R.string.date));
            adjustsaveBtn.setText(resources.getString(R.string.save));
        }
//for spinner set position
        accountspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accounttype = parent.getSelectedItem().toString();

//                if (position == 1 || position == 2) {
//                    String[] cas = getResources().getStringArray(R.array.trans2);
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
//                    transactionspinner.setAdapter(adapter);
//                }

//                else {
//                    String[] cas = getResources().getStringArray(R.array.trans);
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
//                    transactionspinner.setAdapter(adapter);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "please choose an item", Toast.LENGTH_SHORT).show();
            }
        });

        transactionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transactiontype = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getContext(), "Please choose an item", Toast.LENGTH_SHORT).show();
            }
        });


        adjustsaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientname = clientnameET.getText().toString();
                clientmobile = clientmobileET.getText().toString().trim();
                date = duepaydateTV.getText().toString();
                clientamount = adjustamountET.getText().toString();

                if (!accounttype.isEmpty() && !transactiontype.isEmpty() && !TextUtils.isEmpty(clientnameET.getText().toString()) &&
                        !TextUtils.isEmpty(clientmobileET.getText().toString().trim()) && !TextUtils.isEmpty(adjustamountET.getText().toString().trim()) && !TextUtils.isEmpty(duepaydateTV.getText().toString())) {


                    AdjustEntity adjustEntity = new AdjustEntity(accounttype, transactiontype, clientname, clientmobile, clientamount, date, currentdate);
                    adjustViewModel.insertAdjust(adjustEntity);

                    Home_Fragment fragment1 = new Home_Fragment();
                    FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.framelayout_container_id, fragment1);
                    ft1.commit();

                    Toast.makeText(getContext(), "Insert Sucessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please fill up the required fields", Toast.LENGTH_SHORT).show();
                }

            }

        });
        duepaydateTV.setOnClickListener(new View.OnClickListener() {
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
                month = month+1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy:  " + "/" + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                String hour = getResources().getString(R.string.alerm_time_hour);
                String minutes = getResources().getString(R.string.alerm_time_second);
                ///set time for alerm notification=============================
                cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                cal.clear();
                cal.set(year, month, day, parseInt(hour), parseInt(minutes));
                duepaydateTV.setText(date);

            }
        };
        return view;
    }
}