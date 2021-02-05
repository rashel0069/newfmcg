package com.appshat.fmcgapp.Room.ENTITY;

public class TransactionEntity {
    Integer id;
   private String account_type,tran_type,customer_name,customer_number,amount,due_date;

    public TransactionEntity(String account_type, String tran_type, String customer_name, String customer_number, String amount, String due_date) {
        this.account_type = account_type;
        this.tran_type = tran_type;
        this.customer_name = customer_name;
        this.customer_number = customer_number;
        this.amount = amount;
        this.due_date = due_date;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public String getTran_type() {
        return tran_type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public String getAmount() {
        return amount;
    }

    public String getDue_date() {
        return due_date;
    }
}
