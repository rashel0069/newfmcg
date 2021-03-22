package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.appshat.kherokhata.Room.DAO.InformationDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.InformationEntity;

public class InformationViewModel extends AndroidViewModel {
    private InformationDao informationDao;
    private Databaseroom databaseroom;

    public InformationViewModel(@NonNull Application application) {
        super( application );

        databaseroom = Databaseroom.getDatabaseroomref( application );
        informationDao = databaseroom.getInformationDao();

    }

    public void insertInfo(InformationEntity informationEntity){
        new InformationViewModel.InsertAsyncTask( informationDao ).execute( informationEntity );
    }

   private class InsertAsyncTask extends AsyncTask<InformationEntity, Void,Void> {

        InformationDao mInformationDao;

        public InsertAsyncTask(InformationDao mInformationDao) {
            this.mInformationDao = mInformationDao;
        }

        @Override
        protected Void doInBackground(InformationEntity... informationEntities) {
            mInformationDao.insert( informationEntities[0] );
            return null;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
