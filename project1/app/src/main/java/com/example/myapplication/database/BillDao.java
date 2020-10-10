package com.example.myapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BillDao {
    @Insert
    void insertBill(Bill... bills);

    @Update
    void updateBill(Bill... bills);

    @Delete
    void deleteBill(Bill... bills);

    @Query("DELETE FROM BILL")
    void deleteAllBills();

    @Query("SELECT * FROM BILL ORDER BY ID DESC")
    LiveData<List<Bill>> getAllBills();
}
