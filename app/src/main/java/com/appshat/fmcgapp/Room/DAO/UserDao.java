package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.fmcgapp.Room.ENTITY.UserEntity;

@Dao
public interface UserDao {

    @Query( "SELECT * FROM users WHERE mobile = :mobile and password = :password")
    UserEntity getUserEntity(String mobile, String password);
    @Insert
    void insert(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

}
