package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appshat.kherokhata.Room.DAO.AdjustDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.AdjustEntity;

import java.util.List;


public class AdjustViewModel extends AndroidViewModel {
    LiveData<List<AdjustEntity>> allAdjust;
    private final AdjustDao adjustDao;
    private final Databaseroom databaseroom;

    public AdjustViewModel(@NonNull Application application) {
        super(application);
        databaseroom = Databaseroom.getDatabaseroomref(application);
        adjustDao = databaseroom.getduepayandreceive();
        allAdjust = adjustDao.getAllpayreceive();

    }

    public void insertAdjust(AdjustEntity adjustEntity) {
        new AdjustViewModel.InsertAsyncTask(adjustDao).execute(adjustEntity);
    }

    public LiveData<List<AdjustEntity>> getAllAdjust() {
        return allAdjust;
    }

    private class InsertAsyncTask extends AsyncTask<AdjustEntity, Void, Void> {
        AdjustDao mAdjustDao;

        public InsertAsyncTask(AdjustDao mAdjustDao) {
            this.mAdjustDao = mAdjustDao;
        }

        @Override
        protected Void doInBackground(AdjustEntity... adjustEntities) {
            mAdjustDao.insert(adjustEntities[0]);
            return null;
        }
    }
}
