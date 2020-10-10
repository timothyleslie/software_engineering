package com.example.myapplication.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BillRepository {
    private LiveData<List<Bill>>allBills;
    private BillDao billDao;

    public BillRepository(Context context) {
        BillDatabase billDatabase = BillDatabase.getDatabase(context.getApplicationContext());
        billDao = billDatabase.getBillDao();
        allBills = billDao.getAllBills();
    }

    public LiveData<List<Bill>> getAllBills() {
        return allBills;
    }

    public void insertBills(Bill... bills) {
        billDao.insertBill(bills);
    }

    public void updateBills(Bill... bills) {
        billDao.updateBill(bills);
    }


    public void deleteBills(Bill... bills) {
        billDao.deleteBill(bills);
    }

    public void clearBills(Bill... bills) {
        billDao.deleteAllBills();
    }
}
