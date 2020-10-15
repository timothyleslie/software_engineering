package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.graphics.Color;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.Adapter.MyExtendableListViewAdapter;
import com.example.myapplication.database.Bill;
import com.example.myapplication.database.BillViewModel;
import com.example.myapplication.databinding.ActivityAddBinding;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.picker.DateTimePicker;

import static cn.addapp.pickers.picker.DateTimePicker.*;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding addBinding;
    private BillViewModel billViewModel;
    private Bill bill;
    private int etState, inType;
    private RelativeLayout mRl;
    private EditText mEtAmount, mEtRemarks;
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
    private RadioGroup mRvGroup;

    private int groupPos, childPos;
    private String date, time;
    private Button mBtnSave;

    private boolean isIncome;
    private final boolean IS_INCOME = true;
    private final boolean IS_OUTLAY = false;

    public String[][] childString = {
            {"早餐", "午餐", "晚餐"},
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addBinding = DataBindingUtil.setContentView(this, R.layout.activity_add);

        billViewModel = new ViewModelProvider(this).get(BillViewModel.class);
        bill = new Bill();

        mRvGroup = addBinding.radioGroup;
        mEtAmount = addBinding.etAmount;
        mTvTime = addBinding.tvTimeSelect;
        mTvDate = addBinding.tvDateSelect;
        mEtRemarks = addBinding.etRemarks;

//
        mRvGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_income:
                        isIncome = IS_INCOME;
                        break;
                    case R.id.rb_expand:
                        isIncome = IS_OUTLAY;
                        break;
                    default:
                        break;
                }
            }
        });
        //choose account
        spinner_account = findViewById(R.id.sp_account);
        ArrayAdapter<String>adapter_account=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,account);
        spinner_account.setAdapter(adapter_account);

        //choose user
        spinner_user = findViewById(R.id.sp_user);
        ArrayAdapter<String>adapter_user=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,user);
        spinner_user.setAdapter(adapter_user);

        //choose date
        initDate();

        //set date listener
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                mTvDate.setText(date);
            }
        };

        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, dateSetListener, mYear, mMonth, mDay);
//                datePickerDialog.show();
                onYearMonthDayTimePicker(LayoutInflater.from(AddActivity.this).inflate(R.layout.activity_add, null));
            }
        });


        //choose time
        initTime();

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time = String.format("%d:%d:00", hourOfDay, minute);
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
                childPos = childPosition;
                return true;

            }
        });


        //save data
        mBtnSave = addBinding.btnSave;
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveData();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AddActivity.this, bill.getAmount().toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        billViewModel.getAllBills().observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(List<Bill> bills) {
                if(bills.size()!=0){
                    Log.d("bills:", bills.get(0).getAmount().toString());
                }
                else {
                    Log.d("fail:", "None");
                }
            }
        });
    }


    //init date
    public void initDate(){
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        date = String.format("%d-%d-%d", mYear, mMonth, mDay);
        mTvDate.setText(date);
    }

    //init time
    public void initTime(){
        //        bill.setTime(new Date(System.currentTimeMillis()));
        Date current_time = new Date(System.currentTimeMillis());
//        mYear = ();
//        mHour = (calendar.get(Calendar.HOUR_OF_DAY) + 8) % 24;
//        mMinute = calendar.get(Calendar.MINUTE);

        time = String.format("%d:%d:00", mHour, mMinute);
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

    private void saveData() throws ParseException {

        //get value of isIncome

        bill.setIncome(isIncome);

        //get value of amount
        String context = mEtAmount.getText().toString();
        BigDecimal amount = new BigDecimal(context);
        bill.setAmount(amount);

       //get value of class
       bill.setType(childPos);

       //get value of time
        String datetime = date +' '+ time;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time_value = sdf.parse(datetime);
        bill.setTime(time_value);


       //get value of remarks
        String remarks = mEtRemarks.getText().toString();
        bill.setRemark(remarks);


        billViewModel.insertBills(bill);
    }

    public void onYearMonthDayTimePicker(View view) {
        DateTimePicker picker = new DateTimePicker(this, HOUR_24);
        picker.setActionButtonTop(false);
        picker.setDateRangeStart(2017, 1, 1);
        picker.setDateRangeEnd(2025, 11, 11);
        picker.setSelectedItem(2018,6,16,0,0);
        picker.setTimeRangeStart(9, 0);
        picker.setTimeRangeEnd(20, 30);
        picker.setCanLinkage(false);
        picker.setTitleText("请选择");
//        picker.setStepMinute(5);
        picker.setWeightEnable(true);
//        picker.setCanceledOnTouchOutside(true);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
        config.setVisible(true);//线不显示 默认显示
        picker.setLineConfig(config);
        picker.setOuterLabelEnable(true);
//        picker.setLabel(null,null,null,null,null);
        picker.setOnDateTimePickListener(new OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
//                ToastUtils.showShort(year + "-" + month + "-" + day + " " + hour + ":" + minute);
            }
        });
        picker.show();
    }
}