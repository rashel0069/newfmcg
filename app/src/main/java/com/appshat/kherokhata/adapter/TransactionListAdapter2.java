package com.appshat.kherokhata.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.fragment.Receivablepayable_Fragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionListAdapter2 extends RecyclerView.Adapter<TransactionListAdapter2.TransViewHolder> {
    List<NewtransactionEntity> mTransactions;
    GetNumberOnclick getNumberOnclick;



    public TransactionListAdapter2(List<NewtransactionEntity> mTransactions, GetNumberOnclick getNumberOnclick) {
        this.mTransactions = mTransactions;
        this.getNumberOnclick = getNumberOnclick;
    }

    @NonNull
    @Override
    public TransViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.showdata_list2, parent, false);
        TransViewHolder viewHolder = new TransViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransViewHolder holder, int position) {

        if (mTransactions != null) {
            NewtransactionEntity newtransactionEntity = mTransactions.get(position);
            holder.dataName.setText(newtransactionEntity.getClientname());
            holder.dataMobile.setText(newtransactionEntity.getClientmobile());
            holder.dataAcetype.setText(newtransactionEntity.getDuedate());
        }
    }

    @Override
    public int getItemCount() {
        if (mTransactions != null) {

            return mTransactions.size();
        } else {
            return 0;
        }
    }


    public void setTrans(List<NewtransactionEntity> transactions) {
        this.mTransactions = transactions;

        notifyDataSetChanged();
    }

    public class TransViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataName;
        private final TextView dataMobile;
        private TextView dataTranType;
        private final TextView dataAcetype;
        private TextView dataAmoyunt;
        private TextView dataDate;

        public TransViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.namedata_id);
            dataMobile = itemView.findViewById(R.id.mbldata_id);
            dataAcetype = itemView.findViewById(R.id.date_data_Id);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positions = getAdapterPosition();
                    if (positions != RecyclerView.NO_POSITION){
                        getNumberOnclick.onItemClick(mTransactions.get(positions));
                    }
                }
            });
        }
    }
}
