package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;

import java.util.List;

@Dao
public interface CashboxDao {

    @Insert
    void insert(CashboxEntity cashboxEntity);

    @Update
    void update(CashboxEntity cashboxEntity);

    @Delete
    void delete(CashboxEntity cashboxEntity);

    @Query( "SELECT * FROM cashbox WHERE Date LIKE :today AND DayendCash" )
    List<CashboxEntity> getCashboxinfo(String today);


    @Query( "SELECT * FROM cashbox WHERE Date LIKE :today AND WithdrawCash" )
    List<CashboxEntity> getCashboxinfoWith(String today);


    @Query( "SELECT * FROM cashbox WHERE Date LIKE :today AND Depositcash" )
    List<CashboxEntity> getCashboxinfoDep(String today);


}
