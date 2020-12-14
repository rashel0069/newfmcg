package com.appshat.fmcgapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Transaction;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransViewHolder> {
    List<NewtransactionEntity> mTransactions;

    @NonNull
    @Override
    public TransViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.showdata_list,parent,false );
        TransViewHolder viewHolder = new TransViewHolder( itemView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransViewHolder holder, int position) {

            if (mTransactions != null){
                NewtransactionEntity newtransactionEntity = mTransactions.get( position );
                holder.dataName.setText( newtransactionEntity.getClientname() );
                holder.dataMobile.setText( newtransactionEntity.getClientmobile() );
                holder.dataAcetype.setText( newtransactionEntity.getAccounttype() );
                holder.dataTranType.setText( newtransactionEntity.getTransactiontype() );
                holder.dataAmoyunt.setText( newtransactionEntity.getClientamount() );
                holder.dataDate.setText( newtransactionEntity.getDuedate() );
            }


    }

    @Override
    public int getItemCount() {
        if (mTransactions != null){

            return mTransactions.size();
        }else {
            return 0;
        }

}

    public class TransViewHolder extends RecyclerView.ViewHolder{
        private TextView dataName,dataMobile,dataTranType,dataAcetype,dataAmoyunt,dataDate;

        public TransViewHolder(@NonNull View itemView) {
            super( itemView );
            dataName = itemView.findViewById( R.id.namedata_id );
            dataMobile = itemView.findViewById( R.id.mbldata_id );
            dataAcetype = itemView.findViewById( R.id.salesdata_id );
            dataTranType = itemView.findViewById( R.id.cashdata_id );
            dataAmoyunt = itemView.findViewById( R.id.amountdata_id );
            dataDate = itemView.findViewById( R.id.date_data_Id );
        }
    }
    public void setTrans(List<NewtransactionEntity> transactions){
        this.mTransactions = transactions;
        notifyDataSetChanged();
    }
}
