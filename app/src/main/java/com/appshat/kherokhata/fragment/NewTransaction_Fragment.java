package com.appshat.kherokhata.fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.OldAcrivity.AlarmReceiver;
import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.NewtransactionDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.Room.ENTITY.TransactionEntity;
import com.appshat.kherokhata.Room.model.TransactionViewModel;
import com.appshat.kherokhata.adapter.JsonPlaceHolderApi;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

public class NewTransaction_Fragment extends Fragment {
    static final int PICK_CONTACT = 1;
    Spinner accspinner, transspinner;
    EditText cnameET, cmblnumET, camountET;
    TextView cnTV, cmTV, amTV, timedateTV, saveNewContact, accTv, transTv, adtv;
    ;
    MaterialButton newtransBTN;
    ConstraintLayout l1, l2, datepick;
    ImageView phonecontactSelect;
    NewtransactionDao newtransactionDBdao;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Calendar cal;
    Context context;
    Resources resources;
    Databaseroom newtransactionDB;
    TransactionViewModel transactionViewModel;
    String accounttype, transactiontype, clientname, clientmobile, clientamount, duedate, currentdate;
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
                    String number = c.getString(numberindex).trim();
                    number = number.replace(" ", "").replace("+88", "").replace("-", "");
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

        View view = inflater.inflate(R.layout.add_new_transaction, container, false);
        // Inflate the layout for this fragment

        l1 = view.findViewById(R.id.layout_1);
        l2 = view.findViewById(R.id.linearLayout_2);
        accTv = view.findViewById(R.id.newaccounttv_id);
        transTv = view.findViewById(R.id.newtransactiontv_id);
        timedateTV = view.findViewById(R.id.dateTV_id);
        newtransBTN = view.findViewById(R.id.newtranssave_id);
        amTV = view.findViewById(R.id.amountTV_id);
        datepick = view.findViewById(R.id.pickdate_id);
        camountET = view.findViewById(R.id.amountET_id);
        cmTV = view.findViewById(R.id.clientmobilenumberTV_id);
        cmblnumET = view.findViewById(R.id.clientmobilenumberET_id);
        cnTV = view.findViewById(R.id.customernameTV_id);
        cnameET = view.findViewById(R.id.customernameET_id);
        saveNewContact = view.findViewById(R.id.saveContact_id);
        phonecontactSelect = view.findViewById(R.id.phoneContact_id);
        adtv = view.findViewById(R.id.adtTV_tittelbar);

        transactionViewModel = ViewModelProviders.of(getActivity()).get(TransactionViewModel.class);

        //spinner
//        String[] cas = getResources().getStringArray(R.array.accountType);
//        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
//        accspinner.setAdapter(adapter);

        //api call

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://103.239.253.160:3666/digi/fmcg/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //
        cmblnumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(cnameET.getText().toString()) && cmblnumET.getText().length() == 11) {
                    saveNewContact.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //click save number
        saveNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(cmblnumET.getText().toString().trim())
                        && !TextUtils.isEmpty(cnameET.getText().toString())) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, cnameET.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, cmblnumET.getText().toString());
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(context, "Give Phone Number and Name please", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();


            accTv.setText(resources.getString(R.string.sat));
            transTv.setText(resources.getString(R.string.stt));
            cnTV.setText(resources.getString(R.string.customerName));
            cnameET.setHint(resources.getString(R.string.customernamehint));
            cmTV.setText(resources.getString(R.string.hint1));
            cmblnumET.setHint(resources.getString(R.string.customernumberhint));
            amTV.setText(resources.getString(R.string.amounts));
            camountET.setHint(resources.getString(R.string.amounthint));
            newtransBTN.setText(resources.getString(R.string.save));
           adtv.setText(resources.getString(R.string.adt));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();

            accTv.setText(resources.getString(R.string.sat));
            transTv.setText(resources.getString(R.string.stt));
            cnTV.setText(resources.getString(R.string.customerName));
            cnameET.setHint(resources.getString(R.string.customernamehint));
            amTV.setText(resources.getString(R.string.amounts));
            camountET.setHint(resources.getString(R.string.amounthint));
            cmTV.setText(resources.getString(R.string.hint1));
            cmblnumET.setHint(resources.getString(R.string.customernumberhint));
            newtransBTN.setText(resources.getString(R.string.save));
            adtv.setText(resources.getString(R.string.adt));

        }
        //Current date
        currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        datepick.setOnClickListener(new View.OnClickListener() {
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
                month = month + 1;
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
        //get data from bundel
        accounttype = getArguments().getString("AccountType");
        transactiontype = getArguments().getString("TransType");
        accTv.setText(accounttype);
        transTv.setText(transactiontype);
        if (accounttype.matches("Purchase") && transactiontype.matches("Cash")) {
            cmblnumET.setEnabled(false);
            cnameET.setEnabled(false);
            phonecontactSelect.setClickable(false);
            cnameET.setText("Purchase by Cash");
            cmblnumET.setText("No Need Mobile Name");
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
            datepick.setVisibility(View.GONE);
            cnTV.setVisibility(View.GONE);
            cmTV.setVisibility(View.GONE);
            timedateTV.setClickable(false);
            timedateTV.setText(currentdate);
        }
        //for spinner set position
//        accspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                accounttype = parent.getSelectedItem().toString();
//                if (position == 1) {
//                    String[] cas = getResources().getStringArray(R.array.trans3);
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
//                    transspinner.setAdapter(adapter);
//                    purchase = false;
//                } else {
//                    String[] cas = getResources().getStringArray(R.array.transactiontype);
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
//                    transspinner.setAdapter(adapter);
//                    purchase = true;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(getContext(), "please choose an item", Toast.LENGTH_SHORT).show();
//            }
//        });

//        transspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                transactiontype = parent.getSelectedItem().toString();
//                if (purchase == true && position == 1) {
//                    cmblnumET.setEnabled(false);
//                    cnameET.setEnabled(false);
//                    phonecontactSelect.setClickable(false);
//                    cnameET.setText("No Need Customer Name");
//                    cmblnumET.setText("No Need Mobile Name");
//                    l1.setVisibility(View.GONE);
//                    l2.setVisibility(View.GONE);
//                    timedateTV.setClickable(false);
//                    timedateTV.setText(currentdate);
//
//                } else {
//                    cmblnumET.setEnabled(true);
//                    cnameET.setEnabled(true);
//                    phonecontactSelect.setClickable(true);
//                    cnameET.setText("");
//                    cmblnumET.setText("");
//                    l1.setVisibility(View.VISIBLE);
//                    l2.setVisibility(View.VISIBLE);
//                    timedateTV.setClickable(true);
//                    timedateTV.setText("");
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//                Toast.makeText(getContext(), "Please choose an item", Toast.LENGTH_SHORT).show();
//            }
//        });
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
                    TransactionEntity transactionEntity = new TransactionEntity(accounttype, transactiontype, clientname, clientmobile,
                            clientamount, duedate);
                    NewtransactionEntity newtransactionEntity = new NewtransactionEntity(accounttype,
                            transactiontype, clientname, clientmobile,
                            clientamount, duedate, currentdate);

                    transactionViewModel.intertTrans(newtransactionEntity);

                    Call<TransactionEntity> call = jsonPlaceHolderApi.createPost(transactionEntity);
                    call.enqueue(new Callback<TransactionEntity>() {
                        @Override
                        public void onResponse(Call<TransactionEntity> call, Response<TransactionEntity> response) {
                            if (!response.isSuccessful()) {
                                return;
                            }
                            //Toast.makeText( getContext(), "HTTP OK", Toast.LENGTH_LONG ).show();
                        }

                        @Override
                        public void onFailure(Call<TransactionEntity> call, Throwable t) {
                            //Toast.makeText( getContext(), "HTTP Error", Toast.LENGTH_SHORT ).show();
                        }
                    });


                    Home_Fragment fragment1 = new Home_Fragment();
                    FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.framelayout_container_id, fragment1);
                    ft1.commit();
                    Toast.makeText(getContext(), "Insert Sucessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please fill up the required fields", Toast.LENGTH_SHORT).show();
                }

                if (transactiontype.equals("Credit") && !timedateTV.getText().toString().isEmpty()) {
                    setAlerm(cal);
                }


            }

        });

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
        //Toast.makeText(getActivity().getApplicationContext(), "successfully set alerm", Toast.LENGTH_LONG).show();

    }

}