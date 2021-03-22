package com.appshat.kherokhata.Room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.kherokhata.Room.ENTITY.HistoryEntity;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insert(HistoryEntity historyEntity);

    @Update
    void update(HistoryEntity historyEntity);

    @Query( "SELECT * FROM history WHERE todaydate LIKE :date" )
    List<HistoryEntity> getallHistory(String date);

    @Query( "SELECT * FROM history WHERE userId LIKE :userId" )
    HistoryEntity findbyId(String userId);
}
