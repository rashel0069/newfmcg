package com.appshat.fmcgapp.Room.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.appshat.fmcgapp.Room.DAO.NewtransactionDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;

public class TransactionViewModel extends AndroidViewModel {
    private NewtransactionDao newtransactionDao;
    private Databaseroom databaseroom;

    public TransactionViewModel(@NonNull Application application) {
        super( application );
        databaseroom = Databaseroom.getDatabaseroomref( application );
        newtransactionDao = databaseroom.getnewtransaction();

    }
    public void intertTrans(NewtransactionEntity newtransactionEntity){
        new TransactionViewModel.InsertAsyncTask(newtransactionDao).execute(newtransactionEntity);
    }

    private class InsertAsyncTask extends AsyncTask<NewtransactionEntity, Void,Void>{
        public InsertAsyncTask(NewtransactionDao mNewtransactionDao) {
            this.mNewtransactionDao = mNewtransactionDao;
        }

        NewtransactionDao mNewtransactionDao;

        @Override
        protected Void doInBackground(NewtransactionEntity... newtransactionEntities) {
            mNewtransactionDao.insert( newtransactionEntities[0] );
            return null;
        }
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
