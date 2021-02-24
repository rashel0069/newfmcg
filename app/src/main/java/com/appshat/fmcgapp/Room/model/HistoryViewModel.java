package com.appshat.fmcgapp.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appshat.fmcgapp.Room.DAO.HistoryDao;
import com.appshat.fmcgapp.Room.DAO.NewtransactionDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.HistoryEntity;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryDao historyDao;
    private Databaseroom databaseroom;
    private List<HistoryEntity> mHistory;


    public HistoryViewModel(@NonNull Application application) {
        super( application );
        databaseroom = Databaseroom.getDatabaseroomref( application );
        historyDao = databaseroom.getHistory();
    }
    public void updateHistory(HistoryEntity historyEntity){
        new HistoryViewModel.UpdateAsyncTask(historyDao).execute(historyEntity);
    }

    public void insertHistory(HistoryEntity historyEntity) {
        new HistoryViewModel.InsertAsyncTask( historyDao ).execute( historyEntity );
        }

    private class InsertAsyncTask extends AsyncTask<HistoryEntity, Void, Void> {
        HistoryDao mHistoryDao;
        public InsertAsyncTask(HistoryDao mHistoryDao) {
            this.mHistoryDao = mHistoryDao;
        }

        @Override
        protected Void doInBackground(HistoryEntity... historyEntities) {
            mHistoryDao.insert( historyEntities[0] );
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<HistoryEntity,Void,Void> {
        HistoryDao mHistoryDao;
        public UpdateAsyncTask(HistoryDao mHistoryDao) {
           this.mHistoryDao = mHistoryDao;
        }

        @Override
        protected Void doInBackground(HistoryEntity... historyEntities) {
            mHistoryDao.update( historyEntities[0] );
            return null;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
