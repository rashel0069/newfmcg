package com.appshat.kherokhata.Room.ENTITY;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "cashbox")
public class CashboxEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;
    @NonNull
    @ColumnInfo(name = "Date")
    String time;
    @ColumnInfo(name = "DayendCash")
    String dayend;
    @ColumnInfo(name = "WithdrawCash")
    String withdrawl;
    @ColumnInfo(name = "Depositcash")
    String deposit;

    public CashboxEntity(@NonNull String time, String dayend, String withdrawl, String deposit) {
        this.time = time;
        this.dayend = dayend;
        this.withdrawl = withdrawl;
        this.deposit = deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }

    public String getDayend() {
        return dayend;
    }

    public void setDayend(String dayend) {
        this.dayend = dayend;
    }

    public String getWithdrawl() {
        return withdrawl;
    }

    public void setWithdrawl(String withdrawl) {
        this.withdrawl = withdrawl;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "CashboxEntity{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", dayend='" + dayend + '\'' +
                ", withdrawl='" + withdrawl + '\'' +
                ", deposit='" + deposit + '\'' +
                '}';
    }
}
