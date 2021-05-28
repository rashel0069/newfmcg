package com.appshat.kherokhata.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;

import java.util.List;

@Dao
public interface NewtransactionDao {

//    @Query( "SELECT * FROM newtransaction WHERE accoounttype = :accounttype and transactiontype = :transactiontype and clientname= :clientname and clientmobile = :clientmobile and clientamount = :clientamount and date = :date ")
//    NewtransactionEntity getNewtransactionEntity(String accounttype, String transactiontype, String clientname, String clientmobile, String clientamount, String date);

    @Insert
    void insert(NewtransactionEntity newtransactionEntity);

    @Update
    void update(NewtransactionEntity newtransactionEntity);

    @Delete
    void delete(NewtransactionEntity newtransactionEntity);

    @Query("SELECT * FROM newtransaction WHERE accoounttype LIKE :accounttype AND transactiontype LIKE :transactiontype AND currentdate LIKE :currentdate")
    List<NewtransactionEntity> getCerditSell(String accounttype, String transactiontype, String currentdate);

    @Query("SELECT * FROM newtransaction WHERE accoounttype LIKE :accounttype AND currentdate LIKE :currentdate")
    List<NewtransactionEntity> getAllSell(String accounttype, String currentdate);

    @Query("SELECT * FROM newtransaction")
    LiveData<List<NewtransactionEntity>> getTodayTrans();

    @Query("SELECT * FROM newtransaction WHERE currentdate LIKE :currentdate")
    LiveData<List<NewtransactionEntity>> getTransDate(String currentdate);

    @Query("SELECT * FROM newtransaction WHERE transactiontype LIKE :transactiontype AND duedate LIKE :currentdate")
    LiveData<List<NewtransactionEntity>> getNotifyData(String transactiontype,String currentdate);

    @Query("SELECT * FROM newtransaction WHERE accoounttype LIKE :accounttype AND transactiontype LIKE :transactiontype")
    LiveData<List<NewtransactionEntity>> getreceivepayData(String accounttype, String transactiontype);

    //get user info past reciveable
    @Query("SELECT * FROM newtransaction WHERE accoounttype LIKE :accounttype AND transactiontype LIKE :transactiontype AND clientmobile LIKE :mobile")
    List<NewtransactionEntity> getCerditSellinfo(String accounttype, String transactiontype, String mobile);


}
