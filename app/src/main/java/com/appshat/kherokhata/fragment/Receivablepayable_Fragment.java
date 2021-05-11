package com.appshat.kherokhata.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.AdjustDao;
import com.appshat.kherokhata.Room.DAO.NewtransactionDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.AdjustEntity;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.Room.model.AdjustViewModel;
import com.appshat.kherokhata.Room.model.TransactionViewModel;
import com.appshat.kherokhata.adapter.TransactionListAdapter;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Receivablepayable_Fragment extends Fragment {
    static final int PICK_CONTACT = 1;
    Spinner spadjust;
    EditText edtmobile, newCash, newProduct, newAdjust;
    TextView customerName, previousBalns, summPrev, newBalance,previbal2,paidammount,rpTv;
    ImageView contactnumber;
    MaterialButton calculate, saveTrans, searchUser;
    String customerContName, currentdate, accounttype, transtype, cmmobile, cmamount, date;
    NewtransactionDao newtransactionDao;
    LinearLayout l2;
    CardView l1;
    RecyclerView recyclerView;
    TransactionListAdapter transactionListAdapter;
    TransactionViewModel transactionViewModel;
    AdjustViewModel adjustViewModel;
    AdjustDao duepayrecivedao;
    Databaseroom databaseroom;
    Double cpa, result;
    int rpvalue = 0;
    List<NewtransactionEntity> mRecivePay;

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
                    number = number.replace("+88","").replace(" ","").replace("-","");
                    edtmobile.setText(number);
                    int nameindex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = c.getString(nameindex);
                    //clientnameET.setText(name);

                }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.receivable_payable_ui, container, false);
        //Layout
        l1 = v.findViewById(R.id.l1_id);
        l2 = v.findViewById(R.id.l2_id);
        //cstomer name
        customerName = v.findViewById(R.id.viewCustomer_id);
        previousBalns = v.findViewById(R.id.viewpreviousblnc_id);
        summPrev = v.findViewById(R.id.summarybalance_id);
        newBalance = v.findViewById(R.id.newBalance_id);
        previbal2 = v.findViewById(R.id.previousbal_id);
        paidammount = v.findViewById(R.id.paidamount_id);
        rpTv = v.findViewById(R.id.rpTV_id);
        //save change
        saveTrans = v.findViewById(R.id.recivepaybtn_id);
        //recyler view
        recyclerView = v.findViewById(R.id.payrecive_cycle);
        //spinner
        spadjust = v.findViewById(R.id.spinner_id_payrecive);

        //data get
        rpTv.setText(getArguments().getString("Title"));

        //database
        databaseroom = Databaseroom.getDatabaseroomref(getActivity());
        newtransactionDao = databaseroom.getnewtransaction();
        duepayrecivedao = databaseroom.getduepayandreceive();

        String[] cas = getResources().getStringArray(R.array.adjustbalance);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.myarrylistsample, cas);
        spadjust.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        adjustViewModel = ViewModelProviders.of(getActivity()).get(AdjustViewModel.class);
        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        spadjust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rpvalue = position;
                accounttype = parent.getSelectedItem().toString();

                if (rpvalue == 1) {
                    recyclerView.setVisibility(View.VISIBLE);
                    reciveableTrans();
                } else if (rpvalue == 2) {
                    recyclerView.setVisibility(View.VISIBLE);
                    payableTrans();
                } else {
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        saveTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transtype = "PAID-OUT";
                cmmobile = edtmobile.getText().toString().trim();
                cmamount = String.valueOf(cpa);
                date = newBalance.getText().toString();
                if (!TextUtils.isEmpty(edtmobile.getText().toString()) && !TextUtils.isEmpty(customerName.getText().toString())
                        && !TextUtils.isEmpty(previousBalns.getText().toString())) {
                    AdjustEntity duepayrecive = new AdjustEntity(accounttype, transtype, customerContName, cmmobile, cmamount, date, currentdate);
                    adjustViewModel.insertAdjust(duepayrecive);
                }
                Toast.makeText(getContext(), "Update TransactionEntity", Toast.LENGTH_SHORT).show();
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                ft1.remove(Receivablepayable_Fragment.this);
                ft1.commit();
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        //Date time
        currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());


        //edit new trans
        newCash = v.findViewById(R.id.newCash_id);
        newProduct = v.findViewById(R.id.newProduct_id);
        newAdjust = v.findViewById(R.id.newAdjust_id);
        calculate = v.findViewById(R.id.calclate_id);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(newCash.getText().toString().trim()) && !TextUtils.isEmpty(newProduct.getText().toString().trim())
                        && !TextUtils.isEmpty(newAdjust.getText().toString().trim())) {
                    calculatenow();
                } else if (!TextUtils.isEmpty(newCash.getText().toString().trim()) && !TextUtils.isEmpty(newProduct.getText().toString().trim())) {
                    newAdjust.setText("0");
                    calculatenow();
                } else if (!TextUtils.isEmpty(newProduct.getText().toString().trim())
                        && !TextUtils.isEmpty(newAdjust.getText().toString().trim())) {
                    newCash.setText("0");
                    calculatenow();
                } else if (!TextUtils.isEmpty(newCash.getText().toString().trim()) && !TextUtils.isEmpty(newAdjust.getText().toString().trim())) {
                    newProduct.setText("0");
                    calculatenow();
                } else if (!TextUtils.isEmpty(newCash.getText().toString().trim())) {
                    newProduct.setText("0");
                    newAdjust.setText("0");
                    calculatenow();
                } else if (!TextUtils.isEmpty(newProduct.getText().toString().trim())) {
                    newCash.setText("0");
                    newAdjust.setText("0");
                    calculatenow();
                } else if (!TextUtils.isEmpty(newAdjust.getText().toString().trim())) {
                    newCash.setText("0");
                    newProduct.setText("0");
                    calculatenow();
                } else {
                    Toast.makeText(getContext(), "Enter one field", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // get contact number
        contactnumber = v.findViewById(R.id.contact_view_id);
        contactnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://contacts");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
        //Mobile number set
        edtmobile = v.findViewById(R.id.searchmobile_id);
        //search user button
        searchUser = v.findViewById(R.id.searchUser_id);
        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                findUserbymobile();
            }
        });


        return v;
    }

    private void calculatenow() {
        l2.setVisibility(View.VISIBLE);
        cpa = Double.parseDouble(newCash.getText().toString().trim()) +
                Double.parseDouble(newProduct.getText().toString().trim()) + Double.parseDouble(newAdjust.getText().toString().trim());
        result = Double.parseDouble(previousBalns.getText().toString().trim()) - cpa;

        summPrev.setText( newCash.getText().toString().trim()
                + " + " + newProduct.getText().toString().trim()
                + " + " + newAdjust.getText().toString().trim());
        paidammount.setText(String.valueOf(cpa));
        newBalance.setText(String.valueOf(result));
    }

    private void findUserbymobile() {
        if (rpvalue != 0 && !TextUtils.isEmpty(edtmobile.getText().toString().trim()) && edtmobile.length() >= 11) {
            try {
                if (rpvalue == 1) {
                    try {
                        String crsellsin = new GetCreaditSellsUser().execute().get();
                        String crsellpout = new GetCreaditsellPaid().execute().get();
                        Double re = Double.parseDouble(crsellsin) - Double.parseDouble(crsellpout);
                        previousBalns.setText(String.valueOf(re));
                        previbal2.setText(String.valueOf(re));
                        customerName.setText(customerContName);

                        if (!customerName.getText().toString().isEmpty() && re != 0.0){
                            l1.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(getContext(), "User Not Found", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error:" + e, Toast.LENGTH_SHORT).show();
                    }
                } else if (rpvalue == 2) {
                    try {
                        String crbuysin = new GetCreaditbuysUser().execute().get();
                        String crbuypout = new GetCreaditbuyPaid().execute().get();
                        Double re = Double.parseDouble(crbuysin) - Double.parseDouble(crbuypout);
                        previousBalns.setText(String.valueOf(re));
                        customerName.setText(customerContName);

                        if (!customerName.getText().toString().isEmpty() && re != 0.0){
                            l1.setVisibility(View.VISIBLE);
                        }else {
                            Toast.makeText(getContext(), "User Not Found", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error:" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "User not found", Toast.LENGTH_SHORT).show();
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
            }

        } else {
            Toast.makeText(getContext(), "Select TransactionEntity and valid Mobile number", Toast.LENGTH_SHORT).show();
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);
        }
    }

    public class GetCreaditsellPaid extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String mobilenum = edtmobile.getText().toString().trim();
            List<AdjustEntity> adjustEntities = duepayrecivedao.getPaidout("Past Receivable", "PAID-OUT", mobilenum);
            if (adjustEntities != null) {
                Double total_credit = 0.0;
                for (int i = 0; i < adjustEntities.size(); i++) {
                    total_credit = Double.parseDouble(adjustEntities.get(i).getClientamount()) + total_credit;
                    customerContName = adjustEntities.get(i).getClientname();
                }
                return total_credit.toString().trim();
            } else {
                Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }

    public class GetCreaditbuyPaid extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String mobilenum = edtmobile.getText().toString().trim();
            List<AdjustEntity> adjustEntities = duepayrecivedao.getPaidout("Past Payable", "PAID-OUT", mobilenum);
            if (adjustEntities != null) {
                Double total_credit = 0.0;
                for (int i = 0; i < adjustEntities.size(); i++) {
                    total_credit = Double.parseDouble(adjustEntities.get(i).getClientamount()) + total_credit;
                    customerContName = adjustEntities.get(i).getClientname();
                }
                return total_credit.toString().trim();
            } else {
                Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }

    public class GetCreaditSellsUser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String mobilenum = edtmobile.getText().toString().trim();
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSellinfo("Sales", "Credit", mobilenum);
            if (newtransactionEntities != null) {
                Double total_credit = 0.0;
                for (int i = 0; i < newtransactionEntities.size(); i++) {
                    total_credit = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + total_credit;
                    customerContName = newtransactionEntities.get(i).getClientname();
                }
                return total_credit.toString().trim();
            } else {

                Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                return null;
            }

        }
    }

    public class GetCreaditbuysUser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String mobilenum = edtmobile.getText().toString().trim();
            List<NewtransactionEntity> newtransactionEntities = newtransactionDao.getCerditSellinfo("Purchase", "Credit", mobilenum);
            if (newtransactionEntities != null) {
                Double total_credit = 0.0;
                for (int i = 0; i < newtransactionEntities.size(); i++) {
                    total_credit = Double.parseDouble(newtransactionEntities.get(i).getClientamount()) + total_credit;
                    customerContName = newtransactionEntities.get(i).getClientname();
                }
                return total_credit.toString().trim();
            } else {
                return null;
            }
        }
    }

    //get past recivable data
    private void reciveableTrans() {
        mRecivePay = new ArrayList<>();
        transactionListAdapter = new TransactionListAdapter(mRecivePay);
        transactionViewModel.getmRecivable().observe(getViewLifecycleOwner(), new Observer<List<NewtransactionEntity>>() {
            @Override
            public void onChanged(List<NewtransactionEntity> newtransactionEntities) {
                transactionListAdapter.setTrans(newtransactionEntities);
            }
        });
        recyclerView.setAdapter(transactionListAdapter);
    }
    //get past payable data
    private void payableTrans() {
        mRecivePay = new ArrayList<>();
        mRecivePay.clear();
        transactionListAdapter = new TransactionListAdapter(mRecivePay);
        transactionViewModel.getmPaytrans().observe(getViewLifecycleOwner(), new Observer<List<NewtransactionEntity>>() {
            @Override
            public void onChanged(List<NewtransactionEntity> newtransactionEntities) {
                transactionListAdapter.setTrans(newtransactionEntities);
            }
        });
        recyclerView.setAdapter(transactionListAdapter);
    }


}