package com.appshat.fmcgapp.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.PrimaryKey;

import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

import java.util.List;

public class InformationViewModel extends AndroidViewModel {
    private InformationDao informationDao;
    private Databaseroom databaseroom;

    public InformationViewModel(@NonNull Application application) {
        super( application );

        databaseroom = Databaseroom.getDatabaseroomref( application );
        informationDao = databaseroom.getInformationDao();

    }

    public void insertInfo(InformationEntity informationEntity){
        new InsertAsyncTask(informationDao).execute(informationEntity);
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
