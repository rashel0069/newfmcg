package com.appshat.kherokhata.Room.ENTITY;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "informations")
public class InformationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nid")
    String nidnumber;
    @NonNull
    @ColumnInfo(name = "shopkeepername")
    String shopkeepername;
    @NonNull
    @ColumnInfo(name = "shopname")
    String shopname;
    @NonNull
    @ColumnInfo(name = "shopcategory")
    String shopcategory;
    @NonNull
    @ColumnInfo(name = "shopaddress")
    String shopaddress;
    @NonNull
    @ColumnInfo(name = "tradelicense")
    String tradelicense;
    @NonNull
    @ColumnInfo(name = "openingamount")
    String openingamount;
    @NonNull
    @ColumnInfo(name = "receivableamount")
    String receivableamount;
    @NonNull
    @ColumnInfo(name = "payableamount")
    String payableamount;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] imageurl;

    public InformationEntity(@NonNull String nidnumber, @NonNull String shopkeepername, @NonNull String shopname,
                             @NonNull String shopcategory, @NonNull String shopaddress, @NonNull String tradelicense,
                             @NonNull String openingamount, @NonNull String receivableamount, @NonNull String payableamount,
                             byte[] imageurl) {
        this.nidnumber = nidnumber;
        this.shopkeepername = shopkeepername;
        this.shopname = shopname;
        this.shopcategory = shopcategory;
        this.shopaddress = shopaddress;
        this.tradelicense = tradelicense;
        this.openingamount = openingamount;
        this.receivableamount = receivableamount;
        this.payableamount = payableamount;
        this.imageurl = imageurl;
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
    public String getNidnumber() {
        return nidnumber;
    }

    public void setNidnumber(@NonNull String nidnumber) {
        this.nidnumber = nidnumber;
    }

    @NonNull
    public String getShopcategory() {
        return shopcategory;
    }

    public void setShopcategory(@NonNull String shopcategory) {
        this.shopcategory = shopcategory;
    }

    @NonNull
    public String getTradelicense() {
        return tradelicense;
    }

    public void setTradelicense(@NonNull String tradelicense) {
        this.tradelicense = tradelicense;
    }

    @NonNull
    public String getPayableamount() {
        return payableamount;
    }

    public void setPayableamount(@NonNull String payableamount) {
        this.payableamount = payableamount;
    }

    public byte[] getImageurl() {
        return imageurl;
    }

    public void setImageurl(byte[] imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "InformationEntity{" +
                "nidnumber='" + nidnumber + '\'' +
                ", shopkeepername='" + shopkeepername + '\'' +
                ", shopname='" + shopname + '\'' +
                ", shopcategory='" + shopcategory + '\'' +
                ", shopaddress='" + shopaddress + '\'' +
                ", tradelicense='" + tradelicense + '\'' +
                ", openingamount='" + openingamount + '\'' +
                ", receivableamount='" + receivableamount + '\'' +
                ", payableamount='" + payableamount + '\'' +
                ", imageurl=" + Arrays.toString(imageurl) +
                '}';
    }
}
