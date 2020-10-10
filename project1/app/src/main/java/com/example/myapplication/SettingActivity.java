package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.myapplication.databinding.ActivityStatisticsBinding;

public class SettingActivity extends AppCompatActivity {
    ImageView image1;
    private Button type;
    private Button person;
    private Button clock;
    private Button budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        type = (Button) findViewById(R.id.btn_class);
        person = (Button) findViewById(R.id.btn_person);
        clock = (Button) findViewById(R.id.btn_clock);
        budget = (Button) findViewById(R.id.btn_budget);

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SettingActivity.this,activity_settings_singlesetting.class);
//                startActivity(intent);
            }
        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SettingActivity.this,activity_settings_singlesetting.class);
//                startActivity(intent);
            }
        });
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SettingActivity.this,activity_settings_singlesetting.class);
//                startActivity(intent);
            }
        });
        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,BudgetActivity.class);
                startActivity(intent);
            }
        });
    }
}