package com.appshat.kherokhata.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.appshat.kherokhata.Room.DAO.UserDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.UserEntity;

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
