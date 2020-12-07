package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;

import java.util.List;


@Dao
public interface AdjustDao {

    @Insert
    void insert(AdjustEntity adjustEntity);

    @Update
    void update(AdjustEntity adjustEntity);

    @Delete
    void delete(AdjustEntity adjustEntity);

    @Query( "SELECT * FROM duepayandreceive WHERE accoounttype LIKE :accounttype AND transactiontype LIKE :transactiontype AND currentdate LIKE :currentdate" )
    List<AdjustEntity> getAdjust(String accounttype,String transactiontype, String currentdate);


}
