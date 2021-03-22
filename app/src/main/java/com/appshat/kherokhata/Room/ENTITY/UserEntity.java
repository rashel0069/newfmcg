package com.appshat.kherokhata.Room.ENTITY;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class UserEntity  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    int id;


    @NonNull
    @ColumnInfo(name = "mobile")
    String mobile;

    @NonNull
    @ColumnInfo(name = "password")
    String password;

    public UserEntity(@NonNull String mobile, @NonNull String password) {
        this.mobile = mobile;
        this.password = password;
    }
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getMobile() {
        return mobile;
    }

    public void setMobile(@NonNull String mobile) {
        this.mobile = mobile;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
