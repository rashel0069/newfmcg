package com.appshat.fmcgapp.Room.model;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.ExpenseDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.ExpenseEntity;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseDao expenseDao;
    private Databaseroom databaseroom;


    public ExpenseViewModel(@NonNull Application application) {
        super( application );

        databaseroom = Databaseroom.getDatabaseroomref( application );
        expenseDao = databaseroom.getExpenseDao();
    }
    public void insertExpense(ExpenseEntity expenseEntity){
        new ExpenseViewModel.InsertAsyncTask(expenseDao).execute(expenseEntity);
    }
    private class InsertAsyncTask extends AsyncTask<ExpenseEntity, Void,Void>{
        ExpenseDao mExpenseDao;
        public InsertAsyncTask(ExpenseDao mExpenseDao) {
            this.mExpenseDao = mExpenseDao;
        }


        @Override
        protected Void doInBackground(ExpenseEntity... expenseEntities) {
            mExpenseDao.insert( expenseEntities[0] );
            return null;
        }
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
