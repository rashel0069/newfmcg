package com.appshat.fmcgapp.Room.DB;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.appshat.fmcgapp.Room.DAO.CashboxDao;
import com.appshat.fmcgapp.Room.DAO.InformationDao;
import com.appshat.fmcgapp.Room.DAO.UserDao;
import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;
import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

@androidx.room.Database( entities = {UserEntity.class, InformationEntity.class, CashboxEntity.class}, version = 3)
public abstract class Database extends RoomDatabase {

    public abstract UserDao getUserDao();

    public abstract InformationDao getInformationDao();

    public abstract CashboxDao getCashbox();

}



