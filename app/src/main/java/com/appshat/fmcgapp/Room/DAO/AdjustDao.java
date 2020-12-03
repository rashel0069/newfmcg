package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.appshat.fmcgapp.Room.ENTITY.AdjustEntity;



@Dao
public interface AdjustDao {

    @Query( "SELECT * FROM duepayandreceive WHERE accoounttype = :accounttype and transactiontype = :transactiontype and clientname= :clientname and clientmobile = :clientmobile and clientamount = :clientamount and date = :date ")
    AdjustEntity geAdjustEntity(String accounttype, String transactiontype, String clientname, String clientmobile, String clientamount, String date);

    @Insert
    void insert(AdjustEntity adjustEntity);

    @Update
    void update(AdjustEntity adjustEntity);

    @Delete
    void delete(AdjustEntity adjustEntity);


}
