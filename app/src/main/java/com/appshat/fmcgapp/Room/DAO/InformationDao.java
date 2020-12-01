package com.appshat.fmcgapp.Room.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

@Dao
public interface InformationDao {


    @Query( "SELECT * FROM informations WHERE usermobile = :usermobile and shopname = :shopname and shopkeepername = :shopkeepername and shopaddress = :shopaddress ")
    InformationEntity getInfoProfile(String usermobile, String shopname,String shopkeepername, String shopaddress);

    @Query("SELECT * FROM informations WHERE openingamount = :opening and receivableamount = :receivable and payableamount = :payable")
    InformationEntity getInfoOpening(String opening, String receivable, String payable);

    @Insert
    void insert(InformationEntity informationEntity);

    @Update

    void update(InformationEntity informationEntity);

    @Delete
    void delete(InformationEntity informationEntity);

}
