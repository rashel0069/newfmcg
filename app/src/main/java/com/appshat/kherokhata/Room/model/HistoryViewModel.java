package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.appshat.kherokhata.Room.DAO.HistoryDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.HistoryEntity;

import java.util.List;

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
