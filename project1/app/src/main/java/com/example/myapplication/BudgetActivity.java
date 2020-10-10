package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends AppCompatActivity {

    private EditText mEtBudget;
    private Button mBtnSave;
    /*    private Button mBtnShow;
        private TextView mTvShow;*/
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor  mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        mEtBudget =findViewById(R.id.et_budget);
        mBtnSave = findViewById(R.id.btn_save);
/*        mBtnShow = findViewById(R.id.btn_show);
        mTvShow = findViewById(R.id.tv_show);*/

        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();



        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEtBudget.getText().toString().length() == 0) {
                    Toast.makeText(BudgetActivity.this, "请设置预算",Toast.LENGTH_SHORT).show();
                }
                else if (mEtBudget.getText().toString().length()>8 ){

                    Toast.makeText(BudgetActivity.this,"你太有钱了，不需要此服务!" +
                            "请重新设置预算",Toast.LENGTH_LONG).show();
                }
                else {
                    //int num = Integer.parseInt(mEtBudget.getText().toString());
                    //mEditor.putInt("budget",num);
                    mEditor.putString("budget",mEtBudget.getText().toString());
                    mEditor.apply();
                    Toast.makeText(BudgetActivity.this,"保存成功",Toast.LENGTH_SHORT).show();}

            }
        });

/*        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num =mSharedPreferences.getInt("budget",0);
                Log.e("BudgetActivity",Integer.toString(num));

                mTvShow.setText(Integer.toString(num));
            }
        });*/
    }
}