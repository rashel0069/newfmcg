package com.appshat.fmcgapp.Room.ENTITY;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense")
public class ExpenseEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id;

    @ColumnInfo(name = "rent")
    String rent;

    @ColumnInfo(name = "salary")
    String salary;

    @ColumnInfo(name = "others")
    String others;

    @ColumnInfo(name = "currentdate")
    String expenseDate;

    public ExpenseEntity(String rent, String salary, String others, String expenseDate) {
        this.rent = rent;
        this.salary = salary;
        this.others = others;
        this.expenseDate = expenseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "id=" + id +
                ", rent='" + rent + '\'' +
                ", salary='" + salary + '\'' +
                ", others='" + others + '\'' +
                ", expenseDate='" + expenseDate + '\'' +
                '}';
    }
}
