package com.appshat.kherokhata.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.ENTITY.AdjustEntity;

import java.util.List;

public class PayReceiveListAdapter extends RecyclerView.Adapter<PayReceiveListAdapter.PayReciveViewHolder> {
    List<AdjustEntity> mPayRecive;

    @NonNull
    @Override
    public PayReciveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.showdata_list, parent, false);
        PayReciveViewHolder viewHolder = new PayReciveViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PayReciveViewHolder holder, int position) {
        if (mPayRecive != null) {
            AdjustEntity adjustEntity = mPayRecive.get(position);
            holder.dataName.setText(adjustEntity.getClientname());
            holder.dataMobile.setText(adjustEntity.getClientmobile());
            holder.dataAcetype.setText(adjustEntity.getAccounttype());
            holder.dataTranType.setText(adjustEntity.getTransactiontype());
            holder.dataAmoyunt.setText(adjustEntity.getClientamount());
            holder.dataDate.setText(adjustEntity.getCurrentdate());
        }
    }

    @Override
    public int getItemCount() {
        if (mPayRecive != null) {
            return mPayRecive.size();
        } else {
            return 0;
        }
    }

    public void setPayRecv(List<AdjustEntity> adjustEntities) {
        this.mPayRecive = adjustEntities;
        notifyDataSetChanged();
    }

    public class PayReciveViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataName;
        private final TextView dataMobile;
        private final TextView dataTranType;
        private final TextView dataAcetype;
        private final TextView dataAmoyunt;
        private final TextView dataDate;

        public PayReciveViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.namedata_id);
            dataMobile = itemView.findViewById(R.id.mbldata_id);
            dataAcetype = itemView.findViewById(R.id.salesdata_id);
            dataTranType = itemView.findViewById(R.id.cashdata_id);
            dataAmoyunt = itemView.findViewById(R.id.amountdata_id);
            dataDate = itemView.findViewById(R.id.date_data_Id);

        }
    }

}
