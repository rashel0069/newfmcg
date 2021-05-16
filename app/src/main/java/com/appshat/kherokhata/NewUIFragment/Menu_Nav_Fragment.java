package com.appshat.kherokhata.NewUIFragment;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.AdjustDao;
import com.appshat.kherokhata.Room.DAO.CashboxDao;
import com.appshat.kherokhata.Room.DAO.ExpenseDao;
import com.appshat.kherokhata.Room.DAO.HistoryDao;
import com.appshat.kherokhata.Room.DAO.InformationDao;
import com.appshat.kherokhata.Room.DAO.NewtransactionDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.model.HistoryViewModel;
import com.appshat.kherokhata.Room.model.InformationViewModel;
import com.appshat.kherokhata.fragment.BoatFragment;
import com.appshat.kherokhata.fragment.CashTransactions;
import com.appshat.kherokhata.fragment.CreditTransactions;
import com.appshat.kherokhata.fragment.ShowTransaction_Fragment;

public class Menu_Nav_Fragment extends Fragment {

    ImageView cm, cbm, orm, transm;
    TextView cmTV, cbmTV, ormTV, transmTV;
    LinearLayout ordermbtn, showtransmbtn, cashTrnm, creditTrnm;
    boolean connect = false;
    InformationDao informationDbDao;
    NewtransactionDao newtransactionDao;
    HistoryDao historyDao;
    InformationViewModel informationViewModel;
    HistoryViewModel historyViewModel;
    ExpenseDao expenseDao;
    CashboxDao cashboxDao;
    AdjustDao adjustDao;
    Databaseroom databaseroom;
    Context context;
    Resources resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu__nav_, container, false);

        historyViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);

        ordermbtn = view.findViewById(R.id.order_id);
        showtransmbtn = view.findViewById(R.id.stmenu_id);
        cashTrnm = view.findViewById(R.id.cashmenu_id);
        creditTrnm = view.findViewById(R.id.ct_id);
        cmTV = view.findViewById(R.id.ctmenu_TV_id);
        cbmTV = view.findViewById(R.id.credit_menuTV);
        ormTV = view.findViewById(R.id.orm_menuTV);
        transmTV = view.findViewById(R.id.stb_menuTV);


//language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            cmTV.setText(resources.getString(R.string.cash_transaction));
            cbmTV.setText(resources.getString(R.string.credit_transaction));
            ormTV.setText(resources.getString(R.string.order));
            transmTV.setText(resources.getString(R.string.showtransaction));

        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            cmTV.setText(resources.getString(R.string.cash_transaction));
            cbmTV.setText(resources.getString(R.string.credit_transaction));
            ormTV.setText(resources.getString(R.string.order));
            transmTV.setText(resources.getString(R.string.showtransaction));

        }
        //Data Receive
        ordermbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent myIntent = new Intent(view.getContext(), ChatBot.class);
//                startActivityForResult(myIntent, 0);

                NetworkConnect();
                if (connect == true) {
//                    Intent browserInt = new Intent(Intent.ACTION_VIEW, Uri.parse("http://103.108.140.234:3000/s/fmcg"));
//                    startActivity(browserInt);

                    BoatFragment boatFragment = new BoatFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.framelayout_container_id, boatFragment);
                    transaction.addToBackStack("null");
                    transaction.commit();

                } else {
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        showtransmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTransaction_Fragment show_transaction_fragment = new ShowTransaction_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, show_transaction_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        creditTrnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditTransactions creditTransactions = new CreditTransactions();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, creditTransactions);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        cashTrnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CashTransactions cashTransactions = new CashTransactions();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, cashTransactions);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });

        NetworkConnect();

        return view;
    }

    private void NetworkConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        connect = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

}

