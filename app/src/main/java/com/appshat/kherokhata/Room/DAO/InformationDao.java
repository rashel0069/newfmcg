package com.appshat.kherokhata.Room.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.kherokhata.Room.ENTITY.InformationEntity;

import java.util.List;

@Dao
public interface InformationDao {

    @Insert
    void insert(InformationEntity informationEntity);

    @Update
    void update(InformationEntity informationEntity);

    @Delete
    void delete(InformationEntity informationEntity);

    @Query("SELECT * FROM informations")
    List<InformationEntity> findAllInfo();


}
