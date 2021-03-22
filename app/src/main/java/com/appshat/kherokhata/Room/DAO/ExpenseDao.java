package com.appshat.kherokhata.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.kherokhata.Room.ENTITY.ExpenseEntity;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(ExpenseEntity expenseEntity);

    @Update
    void update(ExpenseEntity expenseEntity);

    @Delete
    void delete(ExpenseEntity expenseEntity);

    @Query( "SELECT * FROM expense WHERE currentdate LIKE :expenseDate" )
    List<ExpenseEntity> getExpense(String expenseDate);

    @Query( "SELECT * FROM expense" )
    LiveData<List<ExpenseEntity>> getallExpence();

}
