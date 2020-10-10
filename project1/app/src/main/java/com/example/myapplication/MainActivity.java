package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//import com.example.myapplication.Adapter.RVAdapter;
import com.example.myapplication.Adapter.MainRecyclerViewAdapter;
import com.example.myapplication.database.Bill;
import com.example.myapplication.database.BillViewModel;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStatistics, mBtnSetting;
    private ImageButton mBtnAdd;
    private RecyclerView mRv;
    private ActivityMainBinding mainBinding;
    private BillViewModel billViewModel;
    private LiveData<List<Bill>> bills;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        billViewModel =  new ViewModelProvider(this).get(BillViewModel.class);
        recyclerView = mainBinding.rvMain;
        recyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        billViewModel.getAllBills().observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
                recyclerViewAdapter.setAllBills(bills);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });


        mBtnStatistics = findViewById(R.id.btn_statistics);
        mBtnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        mBtnSetting= findViewById(R.id.btn_setting);
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        mBtnAdd= mainBinding.btnAdd;
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

//        initializeData();
//        mRv = findViewById(R.id.rv_main);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        mRv.setLayoutManager(llm);
//        RVAdapter rvAdapter = new RVAdapter(persons);
//        mRv.setAdapter(rvAdapter);

    }



    private  List<Record> records;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
//    private void initializeData(){
//        records = new ArrayList<>();
//        records.add(new Record("Emma Wilson", "23 years old"));
//        records.add(new Record("Lavery Maiss", "25 years old"));
//
//    }
}