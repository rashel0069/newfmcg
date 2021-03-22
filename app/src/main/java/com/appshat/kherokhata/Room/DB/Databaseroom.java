package com.appshat.kherokhata.Room.DB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.appshat.kherokhata.Room.DAO.AdjustDao;
import com.appshat.kherokhata.Room.DAO.CashboxDao;
import com.appshat.kherokhata.Room.DAO.ExpenseDao;
import com.appshat.kherokhata.Room.DAO.HistoryDao;
import com.appshat.kherokhata.Room.DAO.InformationDao;
import com.appshat.kherokhata.Room.DAO.NewtransactionDao;
import com.appshat.kherokhata.Room.DAO.UserDao;
import com.appshat.kherokhata.Room.ENTITY.AdjustEntity;
import com.appshat.kherokhata.Room.ENTITY.CashboxEntity;
import com.appshat.kherokhata.Room.ENTITY.ExpenseEntity;
import com.appshat.kherokhata.Room.ENTITY.HistoryEntity;
import com.appshat.kherokhata.Room.ENTITY.InformationEntity;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.Room.ENTITY.UserEntity;

@Database(entities = {UserEntity.class, InformationEntity.class, CashboxEntity.class, ExpenseEntity.class,
        AdjustEntity.class, NewtransactionEntity.class, HistoryEntity.class}, version = 8)
public abstract class Databaseroom extends RoomDatabase {

    private static volatile Databaseroom databaseroomref;

    public static Databaseroom getDatabaseroomref(Context context) {
        if (databaseroomref == null) {
            synchronized (Databaseroom.class) {
                if (databaseroomref == null) {
                    databaseroomref = Room.databaseBuilder(context.getApplicationContext(), Databaseroom.class, "Fmvg_Database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseroomref;
    }

    public abstract UserDao getUserDao();

    public abstract InformationDao getInformationDao();

    public abstract CashboxDao getCashboxDao();

    public abstract ExpenseDao getExpenseDao();

    public abstract AdjustDao getduepayandreceive();

    public abstract NewtransactionDao getnewtransaction();

    public abstract HistoryDao getHistory();

}



