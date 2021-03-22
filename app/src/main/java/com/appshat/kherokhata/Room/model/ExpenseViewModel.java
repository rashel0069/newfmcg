package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appshat.kherokhata.Room.DAO.ExpenseDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.ExpenseEntity;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseDao expenseDao;
    private Databaseroom databaseroom;
    private LiveData<List<ExpenseEntity>> mAllExpence;


    public ExpenseViewModel(@NonNull Application application) {
        super( application );

        databaseroom = Databaseroom.getDatabaseroomref( application );
        expenseDao = databaseroom.getExpenseDao();
        mAllExpence = expenseDao.getallExpence();
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
    public LiveData<List<ExpenseEntity>> getmAllExpence(){
        return mAllExpence;
    }
}
