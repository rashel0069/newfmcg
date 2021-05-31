package com.appshat.kherokhata.Notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.Room.model.TransactionViewModel;
import com.appshat.kherokhata.adapter.NotifyListAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotifyListAdapter notifyListAdapter;
    TransactionViewModel notifyViewModel;
    List<NewtransactionEntity> mTodayNotifyList;
    Context context;
    LinearLayout imageView;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);


        recyclerView = findViewById(R.id.notify_recycle);
        imageView = findViewById(R.id.notify_image);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        notifyViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        showNotifyAll();
        notifyViewModel.getmNotifyTrans().observe(this, this::onLoaded);

    }

    private void showNotifyAll() {

        mTodayNotifyList = new ArrayList<>();
        notifyListAdapter = new NotifyListAdapter(mTodayNotifyList);
        notifyViewModel.getmNotifyTrans().observe(this, new Observer<List<NewtransactionEntity>>() {
            @Override
            public void onChanged(List<NewtransactionEntity> newtransactionEntities) {
                notifyListAdapter.setTrans(newtransactionEntities);

            }
        });

    }

    public void onLoaded(List<NewtransactionEntity> list){
        if (list.size() == 0 ){
            recyclerView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            recyclerView.setAdapter(notifyListAdapter);
        }
    }

}