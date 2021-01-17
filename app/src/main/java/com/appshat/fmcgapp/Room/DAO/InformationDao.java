package com.appshat.fmcgapp.Room.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.fmcgapp.Room.ENTITY.InformationEntity;
import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

import java.util.List;

@Dao
public interface InformationDao {




    @Insert
    void insert(InformationEntity informationEntity);

    @Update

    void update(InformationEntity informationEntity);

    @Delete
    void delete(InformationEntity informationEntity);

    @Query( "SELECT * FROM informations" )
    List<InformationEntity>findAllInfo();

}
