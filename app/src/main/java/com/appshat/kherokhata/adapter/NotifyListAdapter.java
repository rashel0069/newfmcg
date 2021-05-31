package com.appshat.kherokhata.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appshat.kherokhata.NewUIActivity.UpdateTransaction;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotifyListAdapter extends RecyclerView.Adapter<NotifyListAdapter.TransViewHolder>{
    List<NewtransactionEntity> mTransactions;
    public NotifyListAdapter(List<NewtransactionEntity> mTransactions) {
        this.mTransactions = mTransactions;
    }

    @NonNull
    @Override
    public TransViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationlist, parent, false);
        TransViewHolder viewHolder = new TransViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransViewHolder holder, int position) {

        if (mTransactions != null) {
            NewtransactionEntity newtransactionEntity = mTransactions.get(position);
            holder.dataName.setText(newtransactionEntity.getClientname());
            holder.dataMobile.setText(newtransactionEntity.getClientmobile());
            holder.dataAcetype.setText(newtransactionEntity.getAccounttype());
            holder.dataAmoyunt.setText(newtransactionEntity.getClientamount());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   Intent intent = new Intent(v.getContext(), new UpdateTransaction().getClass());
                   intent.putExtra("Name",newtransactionEntity.getClientname());
                   intent.putExtra("Phone",newtransactionEntity.getClientmobile());
                   intent.putExtra("Accounttype",newtransactionEntity.getAccounttype());
                   intent.putExtra("Transtype",newtransactionEntity.getTransactiontype());
                   intent.putExtra("Ammount",newtransactionEntity.getClientamount());
                   intent.putExtra("Id",newtransactionEntity.getId());
                   intent.putExtra("DuDate",newtransactionEntity.getDuedate());
                   v.getContext().startActivity(intent);
                   return false;
               }
           });

        }
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();

    }

    public void setTrans(List<NewtransactionEntity> transactions) {
        this.mTransactions = transactions;
        notifyDataSetChanged();
    }
    public class TransViewHolder extends RecyclerView.ViewHolder {
        private TextView dataName;
        private TextView dataMobile;
        private TextView dataAcetype;
        private TextView dataAmoyunt;
        private TextView dataEdit;

        public TransViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.nnamedata_id);
            dataMobile = itemView.findViewById(R.id.nmbldata_id);
            dataAcetype = itemView.findViewById(R.id.nsalesdata_id);
            dataAmoyunt = itemView.findViewById(R.id.namountdata_id);
            dataEdit = itemView.findViewById(R.id.nedit_id);
        }
    }
}
