package com.appshat.kherokhata.Room.ENTITY;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "duepayandreceive")
public class AdjustEntity {
    @ColumnInfo(name = "accoounttype")
    public
    String accounttype;
    @ColumnInfo(name = "transactiontype")
    public
    String transactiontype;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    @NonNull
    @ColumnInfo(name = "clientname")
    String clientname;
    @NonNull
    @ColumnInfo(name = "clientmobile")
    String clientmobile;

    @NonNull
    @ColumnInfo(name = "clientamount")
    String clientamount;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "currentdate")
    String currentdate;

    public AdjustEntity(String accounttype, String transactiontype, @NonNull String clientname, @NonNull String clientmobile, @NonNull String clientamount, String date, String currentdate) {
        this.accounttype = accounttype;
        this.transactiontype = transactiontype;
        this.clientname = clientname;
        this.clientmobile = clientmobile;
        this.clientamount = clientamount;
        this.date = date;
        this.currentdate = currentdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    @NonNull
    public String getClientname() {
        return clientname;
    }

    public void setClientname(@NonNull String clientname) {
        this.clientname = clientname;
    }

    @NonNull
    public String getClientmobile() {
        return clientmobile;
    }

    public void setClientmobile(@NonNull String clientmobile) {
        this.clientmobile = clientmobile;
    }

    @NonNull
    public String getClientamount() {
        return clientamount;
    }

    public void setClientamount(@NonNull String clientamount) {
        this.clientamount = clientamount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    @Override
    public String toString() {
        return "AdjustEntity{" +
                "id=" + id +
                ", accounttype='" + accounttype + '\'' +
                ", transactiontype='" + transactiontype + '\'' +
                ", clientname='" + clientname + '\'' +
                ", clientmobile='" + clientmobile + '\'' +
                ", clientamount='" + clientamount + '\'' +
                ", date='" + date + '\'' +
                ", currentdate='" + currentdate + '\'' +
                '}';
    }
}
