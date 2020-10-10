package com.example.myapplication;


import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.fragment.BarChartFragment;
import com.example.myapplication.fragment.PieChartFragment;
import com.example.myapplication.databinding.ActivityStatisticsBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class StatisticsActivity extends AppCompatActivity {


    private ActivityStatisticsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_statistics);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment1,new BarChartFragment()).commit();

        RadioGroup radioGroup = mBinding.radioGroup;
        RadioGroup brChoice = mBinding.typeChoice.brChoice;
//        mBinding.rbOutlay.setText("outlay");
        initBrChoice(brChoice);
        initRadioGroup(radioGroup);

//        replaceFragment(new PieChartFragment());
    }

    private void initBrChoice(RadioGroup radioGroup){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bill:
                        replaceFragment(new BarChartFragment());
                        break;
                    case R.id.reports:
                        replaceFragment(new PieChartFragment());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initRadioGroup(RadioGroup group){
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment1, fragment);
        transaction.commit();
    }

}