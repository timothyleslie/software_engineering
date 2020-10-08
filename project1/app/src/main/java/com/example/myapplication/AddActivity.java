package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private TextView mTvDate, mTvTime;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private  int mYear, mMonth, mDay;
    private int mHour, mMinute;
    private Spinner spinner_class, spinner_account, spinner_user;
    private String account[]={"cash", "alipay"};
    private String user[] = {"自己"};

    private TextView mTvClass;
    private ArrayList list = new ArrayList();
    private ExpandableListView expandableListView;
    private MyExtendableListViewAdapter myExtendableListViewAdapter;
    private  boolean isShow;
    private PopupWindow popupWindow;

    public String[][] childString = {
            {"孙尚香", "后羿", "马可波罗", "狄仁杰"},
            {"孙膑", "蔡文姬", "鬼谷子", "杨玉环"},
            {"张飞", "廉颇", "牛魔", "项羽"},
            {"诸葛亮", "王昭君", "安琪拉", "干将"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        //choose account
        spinner_account = findViewById(R.id.sp_account);
        ArrayAdapter<String>adapter_account=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,account);
        spinner_account.setAdapter(adapter_account);

        //choose user
        spinner_user = findViewById(R.id.sp_user);
        ArrayAdapter<String>adapter_user=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,user);
        spinner_user.setAdapter(adapter_user);

        //choose date
        mTvDate = findViewById(R.id.tv_date_select);
        initDate();

        //set date listener
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = String.format("%d 年 %d 月 %d 日", year, month+1, dayOfMonth);
                mTvDate.setText(date);
            }
        };

        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, dateSetListener, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        //choose time
        mTvTime = findViewById(R.id.tv_time_select);
        initTime();

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = String.format("%d 时 %d 分", hourOfDay, minute);
                mTvTime.setText(time);
            }
        };

        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddActivity.this, TimePickerDialog.THEME_HOLO_LIGHT, timeSetListener, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });


        //choose class
        mTvClass = findViewById(R.id.tv_class_select);
        mTvClass.setText(childString[0][0]);
        initPop();
        mTvClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAsDropDown(mTvClass);
                }
            }
        });
        //设置分组的监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });
        //设置子项布局监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mTvClass.setText(childString[groupPosition][childPosition]);
                return true;

            }
        });
    }


    //init date
    public void initDate(){
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        String date = String.format("%d 年 %d 月 %d 日", mYear, mMonth+1, mDay);
        mTvDate.setText(date);
    }

    //init time
    public void initTime(){
        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        String time = String.format("%d 时 %d 分", mHour, mMinute);
        mTvTime.setText(time);
    }

    private void initPop() {
        View view= LayoutInflater.from(this).inflate(R.layout.class_menu,null);
        myExtendableListViewAdapter =new MyExtendableListViewAdapter();
        expandableListView= (ExpandableListView) view.findViewById(R.id.expend_list);
        expandableListView.setAdapter(myExtendableListViewAdapter);
        popupWindow =new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setContentView(view);
    }
}