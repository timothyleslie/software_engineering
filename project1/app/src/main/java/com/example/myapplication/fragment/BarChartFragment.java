package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.database.Bill;
import com.example.myapplication.view.BarChartMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static com.example.myapplication.R.color.colorTextGray;

/**
 * BarChartFragment 柱状图fragment
 *
 * @author 布瑞艾特
 */

public class BarChartFragment extends Fragment {

    private BarChart mBarChart;
    private View view;

    public BarChartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        mBarChart = view.findViewById(R.id.bar_chart);
        initBarChart();

        return view;
    }

    private void initBarChart(){
        mBarChart.setScaleEnabled(false);   //设置不可缩放
        mBarChart.setNoDataText("");
        mBarChart.getDescription().setEnabled(false);
        mBarChart.getLegend().setEnabled(false);

        mBarChart.getAxisLeft().setAxisMinimum(0);
        mBarChart.getAxisLeft().setEnabled(false);
        mBarChart.getAxisRight().setEnabled(false);

        BarChartMarkerView mv = new BarChartMarkerView(getContext());
        mv.setChartView(mBarChart);
        mBarChart.setMarker(mv);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(ContextCompat.getColor(view.getContext(), colorTextGray));
        xAxis.setLabelCount(5);

        List<BarEntry> entries = new ArrayList<>();
        List<BarEntry> entries1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entries.add(new BarEntry(i, (float) (Math.random()) * 80));
            entries1.add(new BarEntry(i, (float) (Math.random()) * 80));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "first");
        BarDataSet barDataSet1 = new BarDataSet(entries1, "second");

        barDataSet1.setColor(Color.GRAY);
        barDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                DecimalFormat format = new DecimalFormat("￥,###.##");
                return format.format(value);
            }
        });

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        dataSets.add(barDataSet1);
        BarData barData = new BarData(dataSets);
        barData.setBarWidth(0.2f);
        barData.setDrawValues(false);   //取消数值显示

//        XAxis xAxis = mBarChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setLabelCount(5,false);

        mBarChart.getAxisRight().setEnabled(false);

        mBarChart.setData(barData);
        mBarChart.groupBars(0,0.2f,0.05f);

        mBarChart.animateY(500);

        mBarChart.invalidate();
    }

    private void setBarChartData(List<Bill> bills){
        List<BarEntry> incomeEntries = new ArrayList<>();
        List<BarEntry> outlayEntries = new ArrayList<>();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat fmt = new SimpleDateFormat("dd");
        BigDecimal[] daySumIncome = new BigDecimal[32];
        BigDecimal[] daySumOutlay = new BigDecimal[32];
        for(Bill bill : bills){
            int date = Integer.parseInt(fmt.format(bill.getTime()));
            if(bill.isIncome()){
                bigDecimalSum(daySumIncome, bill.getAmount(), date);
            }
            else {
                bigDecimalSum(daySumOutlay, bill.getAmount(), date);
            }
        }

        for(int i = 1;i < 32;i++){
            incomeEntries.add(new BarEntry(i,
                    daySumIncome[i] == null ? 0 : daySumIncome[i].floatValue()));
            outlayEntries.add(new BarEntry(i,
                    daySumOutlay[i] == null ? 0 : daySumOutlay[i].floatValue()));
        }

        BarDataSet incomeDataSet = new BarDataSet(incomeEntries, "收入");
        BarDataSet outlayDataSeet = new BarDataSet(outlayEntries, "支出");

    }

    private void bigDecimalSum(BigDecimal[] daySum, BigDecimal amount, int date){
        if(daySum[date] == null){
            daySum[date] = amount;
        }else {
            daySum[date] = daySum[date].add(amount);
        }
    }

}