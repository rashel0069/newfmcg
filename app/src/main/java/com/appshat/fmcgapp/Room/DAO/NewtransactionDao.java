package com.appshat.fmcgapp.Room.DAO;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.appshat.fmcgapp.Room.ENTITY.NewtransactionEntity;

@Dao
public interface NewtransactionDao {

    @Query( "SELECT * FROM newtransaction WHERE accoounttype = :accounttype and transactiontype = :transactiontype and clientname= :clientname and clientmobile = :clientmobile and clientamount = :clientamount and date = :date ")
    NewtransactionEntity getNewtransactionEntity(String accounttype, String transactiontype, String clientname, String clientmobile, String clientamount, String date);

    @Insert
    void insert(NewtransactionEntity newtransactionEntity);

    @Update
    void update(NewtransactionEntity newtransactionEntity);

    @Delete
    void delete(NewtransactionEntity newtransactionEntity);




}
