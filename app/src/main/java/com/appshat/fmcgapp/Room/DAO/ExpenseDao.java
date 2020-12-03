package com.appshat.fmcgapp.Room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.fmcgapp.Room.ENTITY.CashboxEntity;
import com.appshat.fmcgapp.Room.ENTITY.ExpenseEntity;

@Dao
public interface ExpenseDao {

    @Query( "SELECT * FROM expense WHERE rent = :rent and salary= :salary and others = :others")
    ExpenseEntity getExpense(String rent, String salary, String others);


    @Insert
    void insert(ExpenseEntity expenseEntity);

    @Update
    void update(ExpenseEntity expenseEntity);

    @Delete
    void delete(ExpenseEntity expenseEntity);

}
