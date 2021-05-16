package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appshat.kherokhata.Room.DAO.NewtransactionDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TransactionViewModel extends AndroidViewModel {
    private final NewtransactionDao newtransactionDao;
    private final Databaseroom databaseroom;
    private final LiveData<List<NewtransactionEntity>> mAllTrans;
    private final LiveData<List<NewtransactionEntity>> mtodayTrans;
    private final LiveData<List<NewtransactionEntity>> mRecivetrans;
    private final LiveData<List<NewtransactionEntity>> mPaytrans;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
        databaseroom = Databaseroom.getDatabaseroomref(application);
        newtransactionDao = databaseroom.getnewtransaction();
        mAllTrans = newtransactionDao.getTodayTrans();
        mtodayTrans = newtransactionDao.getTransDate(currentdate);
        mRecivetrans = newtransactionDao.getreceivepayData("Sales", "Credit");
        mPaytrans = newtransactionDao.getreceivepayData("Purchase", "Credit");

    }

    public void intertTrans(NewtransactionEntity newtransactionEntity) {
        new TransactionViewModel.InsertAsyncTask(newtransactionDao).execute(newtransactionEntity);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<NewtransactionEntity>> getmAllTrans() {
        return mAllTrans;
    }

    public LiveData<List<NewtransactionEntity>> getmTodayTrans() {
        return mtodayTrans;
    }

    public LiveData<List<NewtransactionEntity>> getmRecivable() {
        return mRecivetrans;
    }

    public LiveData<List<NewtransactionEntity>> getmPaytrans() {
        return mPaytrans;
    }

    private class InsertAsyncTask extends AsyncTask<NewtransactionEntity, Void, Void> {
        NewtransactionDao mNewtransactionDao;

        public InsertAsyncTask(NewtransactionDao mNewtransactionDao) {
            this.mNewtransactionDao = mNewtransactionDao;
        }

        @Override
        protected Void doInBackground(NewtransactionEntity... newtransactionEntities) {
            mNewtransactionDao.insert(newtransactionEntities[0]);
            return null;
        }
    }
}
