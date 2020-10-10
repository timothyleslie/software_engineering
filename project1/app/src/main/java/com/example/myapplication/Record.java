package com.example.myapplication;

public class Record {
    public int income;
    public int date, time;
    public float amount;
    public String record_class, account, business, user, remarks;
    public Record(int income, float amount, int date, int time, String record_class, String account, String business, String user, String remarks){
        this.income = income;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.record_class = record_class;
        this.account = account;
        this.business = business;
        this.user = user;
        this.remarks = remarks;
    }
}
