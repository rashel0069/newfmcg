package com.appshat.fmcgapp.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserDao userDao;
    private Databaseroom databaseroom;
    private UserEntity mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super( application );
        databaseroom = Databaseroom.getDatabaseroomref( application );
        userDao = databaseroom.getUserDao();


    }
    public void insertUser(UserEntity userEntity){
        new UserViewModel.InsertAsyncTask(userDao).execute(userEntity);
    }

    private class InsertAsyncTask extends AsyncTask<UserEntity, Void,Void>{

        UserDao mUserDao;
        public InsertAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            mUserDao.insert( userEntities[0] );
            return null;
        }
    }

}
