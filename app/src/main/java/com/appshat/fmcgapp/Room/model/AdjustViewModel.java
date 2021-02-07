package com.appshat.fmcgapp.Room.model;
import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appshat.fmcgapp.Room.DAO.AdjustDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;

import java.util.List;


public class AdjustViewModel extends AndroidViewModel {
    private AdjustDao adjustDao;
    private Databaseroom databaseroom;
    LiveData<List<AdjustEntity>> allAdjust;

    public AdjustViewModel(@NonNull Application application) {
        super( application );
        databaseroom = Databaseroom.getDatabaseroomref( application );
        adjustDao = databaseroom.getduepayandreceive();
        allAdjust = adjustDao.getAllpayreceive();

    }
    public void insertAdjust(AdjustEntity adjustEntity){
        new AdjustViewModel.InsertAsyncTask(adjustDao).execute(adjustEntity);
    }
    private class InsertAsyncTask extends AsyncTask<AdjustEntity, Void,Void>{
        AdjustDao mAdjustDao;

        public InsertAsyncTask(AdjustDao mAdjustDao) {
            this.mAdjustDao = mAdjustDao;
        }

        @Override
        protected Void doInBackground(AdjustEntity... adjustEntities) {
            mAdjustDao.insert( adjustEntities[0] );
            return null;
        }
    }
    public LiveData<List<AdjustEntity>> getAllAdjust(){
        return allAdjust;
    }
}
