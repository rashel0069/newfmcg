package com.appshat.fmcgapp.Room.ENTITY;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "informations")
public class InformationEntity {

    @PrimaryKey
    @NonNull
     @ColumnInfo(name = "usermobile")
    String mobilenumber;
    @NonNull
    @ColumnInfo(name = "shopname")
    String shopname;
    @NonNull
    @ColumnInfo(name = "shopkeepername")
    String shopkeepername;
    @NonNull
    @ColumnInfo(name = "shopaddress")
    String shopaddress;
    @NonNull
    @ColumnInfo(name = "openingamount")
    String openingamount;
    @NonNull
    @ColumnInfo(name = "receivableamount")
    String receivableamount;
    @NonNull
    @ColumnInfo(name = "payableamount")
    String payableamount;

    public InformationEntity(String mobilenumber, @NonNull String shopname, @NonNull String shopkeepername, @NonNull String shopaddress, @NonNull String openingamount, @NonNull String receivableamount, @NonNull String payableamount) {
        this.mobilenumber = mobilenumber;
        this.shopname = shopname;
        this.shopkeepername = shopkeepername;
        this.shopaddress = shopaddress;
        this.openingamount = openingamount;
        this.receivableamount = receivableamount;
        this.payableamount = payableamount;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    @NonNull
    public String getShopname() {
        return shopname;
    }

    public void setShopname(@NonNull String shopname) {
        this.shopname = shopname;
    }

    @NonNull
    public String getShopkeepername() {
        return shopkeepername;
    }

    public void setShopkeepername(@NonNull String shopkeepername) {
        this.shopkeepername = shopkeepername;
    }

    @NonNull
    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(@NonNull String shopaddress) {
        this.shopaddress = shopaddress;
    }

    @NonNull
    public String getOpeningamount() {
        return openingamount;
    }

    public void setOpeningamount(@NonNull String openingamount) {
        this.openingamount = openingamount;
    }

    @NonNull
    public String getReceivableamount() {
        return receivableamount;
    }

    public void setReceivableamount(@NonNull String receivableamount) {
        this.receivableamount = receivableamount;
    }

    @NonNull
    public String getPayableamount() {
        return payableamount;
    }

    public void setPayableamount(@NonNull String payableamount) {
        this.payableamount = payableamount;



    }

    @Override
    public String toString() {
        return "InformationEntity{" +
                "mobilenumber='" + mobilenumber + '\'' +
                ", shopname='" + shopname + '\'' +
                ", shopkeepername='" + shopkeepername + '\'' +
                ", shopaddress='" + shopaddress + '\'' +
                ", openingamount='" + openingamount + '\'' +
                ", receivableamount='" + receivableamount + '\'' +
                ", payableamount='" + payableamount + '\'' +
                '}';
    }
}
