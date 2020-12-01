package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

@Dao
public interface UserDao {
    @Insert
    Void registerUser(UserEntity userEntity);
}
