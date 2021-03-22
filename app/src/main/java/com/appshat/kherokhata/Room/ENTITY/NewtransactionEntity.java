package com.appshat.kherokhata.Room.ENTITY;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "newtransaction")
public class NewtransactionEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    int id;

    @ColumnInfo(name = "accoounttype")
    public
    String accounttype;

    @ColumnInfo(name = "transactiontype")
    public
    String transactiontype;

    @ColumnInfo(name = "clientname")
    String clientname;

    @ColumnInfo(name = "clientmobile")
    String clientmobile;

    @ColumnInfo(name = "clientamount")
    String clientamount;

    @ColumnInfo(name = "duedate")
    String duedate;

    @ColumnInfo(name = "currentdate")
    String currentdate;

    public NewtransactionEntity(String accounttype, String transactiontype, String clientname, String clientmobile, String clientamount, String duedate, String currentdate) {
        this.accounttype = accounttype;
        this.transactiontype = transactiontype;
        this.clientname = clientname;
        this.clientmobile = clientmobile;
        this.clientamount = clientamount;
        this.duedate = duedate;
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

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientmobile() {
        return clientmobile;
    }

    public void setClientmobile(String clientmobile) {
        this.clientmobile = clientmobile;
    }

    public String getClientamount() {
        return clientamount;
    }

    public void setClientamount(String clientamount) {
        this.clientamount = clientamount;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    @Override
    public String toString() {
        return "NewtransactionEntity{" +
                "id=" + id +
                ", accounttype='" + accounttype + '\'' +
                ", transactiontype='" + transactiontype + '\'' +
                ", clientname='" + clientname + '\'' +
                ", clientmobile='" + clientmobile + '\'' +
                ", clientamount='" + clientamount + '\'' +
                ", duedate='" + duedate + '\'' +
                ", currentdate='" + currentdate + '\'' +
                '}';
    }
}
