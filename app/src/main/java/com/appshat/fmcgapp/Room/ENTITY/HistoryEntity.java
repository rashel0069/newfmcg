package com.appshat.fmcgapp.Room.ENTITY;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class HistoryEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userId")
    String id;
    @ColumnInfo(name = "openingammount")
    String openingammount;
    @ColumnInfo(name = "dayendbalance")
    String dayendbalance;
    @ColumnInfo(name = "expense")
    String expense;
    @ColumnInfo(name = "cashpurchase")
    String cashpurchase;
    @ColumnInfo(name = "creditpurchase")
    String creditpurchase;
    @ColumnInfo(name = "cashsales")
    String cashsales;
    @ColumnInfo(name = "creditsales")
    String creditsales;
    @ColumnInfo(name = "totalsales")
    String totalsales;
    @ColumnInfo(name = "todaydate")
    String todaydate;

    public HistoryEntity( String id, String openingammount, String dayendbalance,
                         String expense, String cashpurchase, String creditpurchase,
                         String cashsales, String creditsales, String totalsales, String todaydate) {
        this.id = id;
        this.openingammount = openingammount;
        this.dayendbalance = dayendbalance;
        this.expense = expense;
        this.cashpurchase = cashpurchase;
        this.creditpurchase = creditpurchase;
        this.cashsales = cashsales;
        this.creditsales = creditsales;
        this.totalsales = totalsales;
        this.todaydate = todaydate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpeningammount() {
        return openingammount;
    }

    public void setOpeningammount(String openingammount) {
        this.openingammount = openingammount;
    }

    public String getDayendbalance() {
        return dayendbalance;
    }

    public void setDayendbalance(String dayendbalance) {
        this.dayendbalance = dayendbalance;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getCashpurchase() {
        return cashpurchase;
    }

    public void setCashpurchase(String cashpurchase) {
        this.cashpurchase = cashpurchase;
    }

    public String getCreditpurchase() {
        return creditpurchase;
    }

    public void setCreditpurchase(String creditpurchase) {
        this.creditpurchase = creditpurchase;
    }

    public String getCashsales() {
        return cashsales;
    }

    public void setCashsales(String cashsales) {
        this.cashsales = cashsales;
    }

    public String getCreditsales() {
        return creditsales;
    }

    public void setCreditsales(String creditsales) {
        this.creditsales = creditsales;
    }

    public String getTotalsales() {
        return totalsales;
    }

    public void setTotalsales(String totalsales) {
        this.totalsales = totalsales;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "id=" + id +
                ", openingammount='" + openingammount + '\'' +
                ", dayendbalance='" + dayendbalance + '\'' +
                ", expense='" + expense + '\'' +
                ", cashpurchase='" + cashpurchase + '\'' +
                ", creditpurchase='" + creditpurchase + '\'' +
                ", cashsales='" + cashsales + '\'' +
                ", creditsales='" + creditsales + '\'' +
                ", totalsales='" + totalsales + '\'' +
                ", todaydate='" + todaydate + '\'' +
                '}';
    }
}

