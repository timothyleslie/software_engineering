package com.example.myapplication.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BillViewModel extends AndroidViewModel {
    private BillDao billDao;
    private LiveData<List<Bill>>allBills;
    private BillRepository billRepository;

    public BillViewModel(@NonNull Application application) {
        super(application);
        billRepository = new BillRepository(application);
    }

    public LiveData<List<Bill>> getAllBills() {
        return billRepository.getAllBills();
    }

    public void insertBills(Bill... bills) {
        billRepository.insertBills(bills);
    }

    public void updateBills(Bill... bills) {
        billRepository.updateBills(bills);
    }


    public void deleteBills(Bill... bills) {
        billRepository.deleteBills(bills);
    }

    public void clearBills(Bill... bills) {
        billRepository.clearBills();
    }

}
