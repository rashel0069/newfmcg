package com.appshat.kherokhata.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransViewHolder> implements Filterable{
    List<NewtransactionEntity> mTransactions;
    List<NewtransactionEntity> mFilter;

    public TransactionListAdapter(List<NewtransactionEntity> mTransactions) {
        this.mTransactions = mTransactions;
        this.mFilter = new ArrayList<>(mTransactions);
    }


    @NonNull
    @Override
    public TransViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.showdata_list,parent,false );
        TransViewHolder viewHolder = new TransViewHolder( itemView );
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TransViewHolder holder, int position) {

            if (mTransactions != null ){
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
        }else{
            return 0;
        }
}

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on backgeound
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<NewtransactionEntity> filteredList = new ArrayList<>();
            if (constraint.toString().isEmpty()){
                filteredList.addAll( mTransactions );
            }else{
                for (NewtransactionEntity entity : mTransactions){
                    if (entity.getClientname().toLowerCase().contains( constraint.toString().toLowerCase() )
                    || entity.getClientmobile().contains( constraint.toString() )){
                        filteredList.add( entity );
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //run on a ui
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            try {
                mTransactions.clear();
                mTransactions.addAll((Collection<? extends NewtransactionEntity>) results.values );
                notifyDataSetChanged();
            }catch (Exception e){
            }
        }
    };

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
    public void filterList(List<NewtransactionEntity> filterlist){
        this.mFilter = filterlist;
    }
}
