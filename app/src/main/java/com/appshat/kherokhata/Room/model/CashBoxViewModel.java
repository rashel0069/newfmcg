package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.appshat.kherokhata.Room.DAO.CashboxDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.CashboxEntity;

public class CashBoxViewModel extends AndroidViewModel {
    private final CashboxDao cashboxDao;
    private final Databaseroom databaseroom;

    public CashBoxViewModel(@NonNull Application application) {
        super(application);
        databaseroom = Databaseroom.getDatabaseroomref(application);
        cashboxDao = databaseroom.getCashboxDao();
    }

    public void updateOpening(CashboxEntity cashboxEntity) {
        new CashBoxViewModel.UpdateAsyncTask(cashboxDao).execute(cashboxEntity);
    }

    public void insertCashbox(CashboxEntity cashboxEntity) {
        new CashBoxViewModel.InsertAsyncTask(cashboxDao).execute(cashboxEntity);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private class UpdateAsyncTask extends AsyncTask<CashboxEntity, Void, Void> {
        CashboxDao mCashboxDao;

        UpdateAsyncTask(CashboxDao cashboxDao) {
            this.mCashboxDao = cashboxDao;
        }

        @Override
        protected Void doInBackground(CashboxEntity... cashboxEntities) {
            mCashboxDao.update(cashboxEntities[0]);
            return null;
        }
    }

    private class InsertAsyncTask extends AsyncTask<CashboxEntity, Void, Void> {
        CashboxDao mCashboxDao;

        public InsertAsyncTask(CashboxDao mCashboxDao) {
            this.mCashboxDao = mCashboxDao;
        }

        @Override
        protected Void doInBackground(CashboxEntity... cashboxEntities) {
            mCashboxDao.insert(cashboxEntities[0]);
            return null;
        }
    }
}
