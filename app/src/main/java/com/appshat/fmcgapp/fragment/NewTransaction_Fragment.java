package com.appshat.fmcgapp.fragment;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
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

import com.appshat.fmcgapp.AlarmReceiver;
import com.appshat.fmcgapp.Helper;
import com.appshat.fmcgapp.Localhelper;
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
    TextView cnTV,cmTV,amTV,timedateTV;
    Button newtransBTN;
    ImageView phonecontactSelect;
    NewtransactionDao newtransactionDBdao;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Calendar cal;
    Context context;
    Resources resources;
    Databaseroom newtransactionDB;
    TransactionViewModel transactionViewModel;
    String accounttype, transactiontype, clientname, clientmobile, clientamount, duedate, currentdate;
    static final int PICK_CONTACT = 1;
    boolean purchase = false;


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
                    cmblnumET.setText(number);

                    int nameindex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = c.getString(nameindex);
                    cnameET.setText(name);

                }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_transaction_, container, false);
        // Inflate the layout for this fragment

        accspinner = view.findViewById(R.id.newaccountspinner_id);
        transspinner = view.findViewById(R.id.newtransactionspinner_id);
        timedateTV = view.findViewById(R.id.dateTV_id);
        newtransBTN = view.findViewById(R.id.newtranssave_id);
        amTV=view.findViewById(R.id.amountTV_id);
        camountET = view.findViewById(R.id.amountET_id);
        cmTV=view.findViewById(R.id.clientmobilenumberTV_id);
        cmblnumET = view.findViewById(R.id.clientmobilenumberET_id);
        cnTV=view.findViewById(R.id.customernameTV_id);
        cnameET = view.findViewById(R.id.customernameET_id);
        phonecontactSelect = view.findViewById(R.id.phoneContact_id);
        transactionViewModel = ViewModelProviders.of(getActivity()).get(TransactionViewModel.class);

        //spinner
        String[] cas = getResources().getStringArray(R.array.accountType);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
        accspinner.setAdapter(adapter);


        //language setter
        if (Helper.getBangla()) {
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
          timedateTV.setHint(resources.getString(R.string.date));
          cnTV.setText(resources.getString(R.string.customerName));
          cnameET.setHint(resources.getString(R.string.customernamehint));
          cmTV.setText(resources.getString(R.string.hint1));
          cmblnumET.setHint(resources.getString(R.string.customernumberhint));
          amTV.setText(resources.getString(R.string.amounts));
          camountET.setHint(resources.getString(R.string.amounthint));
          newtransBTN.setText(resources.getString(R.string.save));

        } else {
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            timedateTV.setHint(resources.getString(R.string.date));
            cnTV.setText(resources.getString(R.string.customerName));
            cnameET.setHint(resources.getString(R.string.customernamehint));
            amTV.setText(resources.getString(R.string.amounts));
            camountET.setHint(resources.getString(R.string.amounthint));
            cmTV.setText(resources.getString(R.string.hint1));
            cmblnumET.setHint(resources.getString(R.string.customernumberhint));
            newtransBTN.setText(resources.getString(R.string.save));

        }
        //Current date
        currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


//for spinner set position
        accspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accounttype = parent.getSelectedItem().toString();
                if(position == 1 ){
                    String[] cas = getResources().getStringArray(R.array.trans3);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
                    transspinner.setAdapter(adapter);
                    purchase = false;
                }else {
                    String[] cas = getResources().getStringArray(R.array.transactiontype);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
                    transspinner.setAdapter(adapter);
                    purchase = true;
                }
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
                if (purchase == true && position == 1){
                    cmblnumET.setEnabled( false );
                    cnameET.setEnabled( false );
                    phonecontactSelect.setClickable( false );
                    cnameET.setText( "No Need Customer Name" );
                    cmblnumET.setText( "No Need Mobile Name" );
                    timedateTV.setClickable( false );
                    timedateTV.setText( currentdate );

                }else {
                    cmblnumET.setEnabled( true );
                    cnameET.setEnabled( true );
                    phonecontactSelect.setClickable( true );
                    cnameET.setText( "" );
                    cmblnumET.setText( "" );
                    timedateTV.setClickable( true );
                    timedateTV.setText( "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getContext(), "Please choose an item", Toast.LENGTH_SHORT).show();
            }
        });


        newtransBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!accounttype.isEmpty() && !transactiontype.isEmpty() && !TextUtils.isEmpty(cnameET.getText().toString())
                        && !TextUtils.isEmpty(cmblnumET.getText().toString().trim())
                        && !TextUtils.isEmpty(camountET.getText().toString().trim())
                        && !TextUtils.isEmpty(timedateTV.getText().toString())) {

                    clientname = cnameET.getText().toString();
                    clientmobile = cmblnumET.getText().toString().trim();
                    duedate = timedateTV.getText().toString();
                    clientamount = camountET.getText().toString().trim();
                    NewtransactionEntity newtransactionEntity = new NewtransactionEntity(accounttype, transactiontype, clientname, clientmobile, clientamount, duedate, currentdate);

                    transactionViewModel.intertTrans(newtransactionEntity);

                    Home_Fragment fragment1 = new Home_Fragment();
                    FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.framelayout_container_id, fragment1);
                    ft1.commit();
                    Toast.makeText(getContext(), "Insert Sucessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please fill up the required fields", Toast.LENGTH_SHORT).show();
                }

                if (transactiontype.equals("Credit")) {
                    setAlerm(cal);
                }


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
                timedateTV.setText(date);

            }
        };

        phonecontactSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://contacts");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });


        return view;
    }


    private void setAlerm(Calendar cal) {
        AlarmManager alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        intent.putExtra("clientname", clientname);
        intent.putExtra("clientmobilenumber", clientmobile);
        intent.putExtra("clientamount", clientamount);
        intent.putExtra("accounttype", accounttype);
        intent.putExtra("date", duedate);

        getActivity().sendBroadcast(intent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
        // cal.add(Calendar.SECOND, 5);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(getActivity().getApplicationContext(), "successfully set alerm", Toast.LENGTH_LONG).show();

    }

}