package com.appshat.fmcgapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appshat.fmcgapp.R;
import com.appshat.fmcgapp.Room.ENTITY.ExpenseEntity;

import java.util.List;

public class ExpenceListAdapter extends RecyclerView.Adapter<ExpenceListAdapter.ExpenceViewHolder> {
    List<ExpenseEntity> mExpenseEntities;

    @NonNull
    @Override
    public ExpenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.expense_list,parent,false );
        ExpenceViewHolder viewHolder = new ExpenceViewHolder( itemView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenceViewHolder holder, int position) {

        if (mExpenseEntities != null){
            ExpenseEntity expenseEntity = mExpenseEntities.get( position );
            holder.exdate.setText( expenseEntity.getExpenseDate() );
            holder.exsalary.setText( expenseEntity.getSalary() );
            holder.exrent.setText( expenseEntity.getRent() );
            holder.exother.setText( expenseEntity.getOthers() );
        }
    }

    @Override
    public int getItemCount() {
        if (mExpenseEntities != null){
            return mExpenseEntities.size();
        }else {
            return 0;
        }
    }


    public class ExpenceViewHolder extends RecyclerView.ViewHolder {
        TextView exdate,exsalary,exrent,exother;
        public ExpenceViewHolder(@NonNull View itemView) {
            super( itemView );
            exdate = itemView.findViewById( R.id.ex_data_id );
            exsalary = itemView.findViewById( R.id.ex_salary_id );
            exrent = itemView.findViewById( R.id.ex_rent_id );
            exother = itemView.findViewById( R.id.ex_other_id );
        }
    }
    public void setExpance(List<ExpenseEntity> expances){
        this.mExpenseEntities = expances;
        notifyDataSetChanged();
    }
}
