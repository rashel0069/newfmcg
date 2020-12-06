package com.appshat.fmcgapp.Room.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.DB.Databaseroom;

public class CashBoxViewModel extends AndroidViewModel {
    private CashboxDao cashboxDao;
    private Databaseroom databaseroom;
    public CashBoxViewModel(@NonNull Application application) {
        super( application );


    }
}
