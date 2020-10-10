package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
    private SharedPreferences mSharedPreferences;
    private Typeface num_type, text_type;
    private TextView tv_out, tv_out_num, tv_in, tv_in_num, tv_budget, tv_budget_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        num_type = Typeface.createFromAsset(getAssets(), "fonts/DIN-Black.otf");
        text_type = Typeface.createFromAsset(getAssets(), "fonts/PingFang-SC-Regular.ttf");

        tv_out = mainBinding.mainTv1Out;
        tv_out_num = mainBinding.mainTvOutNum;
        tv_in = mainBinding.mainTvIn;
        tv_in_num = mainBinding.mainTvInNum;
        tv_budget = mainBinding.mainTvBudget;
        tv_budget_num = mainBinding.mainTvBudgetNum;
        tv_out.setTypeface(text_type);
        tv_out_num.setTypeface(num_type);
        tv_in.setTypeface(text_type);
        tv_in_num.setTypeface(num_type);
        tv_budget.setTypeface(text_type);
        tv_budget_num.setTypeface(num_type);




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
        mBtnStatistics.setTypeface(text_type);
        mBtnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }
        });

        mBtnSetting= findViewById(R.id.btn_setting);
        mBtnSetting.setTypeface(text_type);
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
        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String budget =mSharedPreferences.getString("budget","").toString();

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