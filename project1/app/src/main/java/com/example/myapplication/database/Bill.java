package com.example.myapplication.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Bill {
    @PrimaryKey(autoGenerate = true) //自动生成id
    private int id;

    @ColumnInfo(name = "is_income")
    private boolean isIncome;

    @ColumnInfo(name = "amount")
    private BigDecimal amount;

    @ColumnInfo(name = "remark")
    private String remark;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "time")
    private Date time;


    public int getId() {
        return id;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getType() {
        return type;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }


    public void setType(int type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
