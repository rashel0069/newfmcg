package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;



@Dao
public interface CashboxDao {

    @Query( "SELECT * FROM cashbox WHERE Date = :datetime and DayendCash= :dayend and WithdrawCash = :withdrawal and DepositCash = :deposit ")
    CashboxEntity getCashBox(String datetime, String dayend,String withdrawal, String deposit);


    @Insert
    void insert(CashboxEntity cashboxEntity);

    @Update
    void update(CashboxEntity cashboxEntity);

    @Delete
    void delete(CashboxEntity cashboxEntity);


}
