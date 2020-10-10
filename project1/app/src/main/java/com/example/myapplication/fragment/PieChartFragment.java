package com.example.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.database.Bill;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * PieChartFragment 饼状图fragment
 *
 * @author 布瑞艾特
 */
public class PieChartFragment extends Fragment {


    private PieChart pieChart;

    public PieChartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        pieChart = view.findViewById(R.id.pie_chart);
        initPieChart();
        initPieChartData();
        return view;
    }

    private void initPieChart(){
        pieChart.setUsePercentValues(false);//这货，是否使用百分比显示，但是我还是没操作出来。
        pieChart.getDescription().setEnabled(false);
        pieChart.setHighlightPerTapEnabled(true); //设置piecahrt图表点击Item高亮是否可用
        pieChart.animateX(500);
        initPieChartData();

        pieChart.setDrawEntryLabels(true); // 设置entry中的描述label是否画进饼状图中
        pieChart.setEntryLabelColor(Color.GRAY);//设置该文字是的颜色
        pieChart.setEntryLabelTextSize(10f);//设置该文字的字体大小

        pieChart.setDrawHoleEnabled(true);//设置圆孔的显隐，也就是内圆
        pieChart.setHoleRadius(28f);//设置内圆的半径。外圆的半径好像是不能设置的，改变控件的宽度和高度，半径会自适应。
        pieChart.setHoleColor(Color.WHITE);//设置内圆的颜色
        pieChart.setDrawCenterText(false);//设置是否显示文字
        pieChart.setCenterText("Test");//设置饼状图中心的文字
        pieChart.setCenterTextSize(10f);//设置文字的消息
        pieChart.setCenterTextColor(Color.RED);//设置文字的颜色
        pieChart.setTransparentCircleRadius(31f);//设置内圆和外圆的一个交叉园的半径，这样会凸显内外部的空间
        pieChart.setTransparentCircleColor(Color.BLACK);//设置透明圆的颜色
        pieChart.setTransparentCircleAlpha(50);//设置透明圆的透明度



        Legend legend = pieChart.getLegend();//图例
        legend.setEnabled(false);//是否显示
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//对齐
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//对齐
        legend.setForm(Legend.LegendForm.DEFAULT);//设置图例的图形样式,默认为圆形
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);//设置图例的排列走向:vertacal相当于分行
        legend.setFormSize(6f);//设置图例的大小
        legend.setTextSize(8f);//设置图注的字体大小
        legend.setFormToTextSpace(4f);//设置图例到图注的距离

        legend.setDrawInside(true);//不是很清楚
        legend.setWordWrapEnabled(false);//设置图列换行(注意使用影响性能,仅适用legend位于图表下面)，我也不知道怎么用的
        legend.setTextColor(Color.BLACK);


    }

    private void initPieChartData(){
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(70f, "cash banlance : 1500"));
        pieEntries.add(new PieEntry(30f, "consumption banlance : 500"));
        pieEntries.add(new PieEntry(30f, "else : 500"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setColors(Color.parseColor("#f17548"), Color.parseColor("#FF9933"), Color.YELLOW);
        pieDataSet.setSliceSpace(3f);//设置每块饼之间的空隙
        pieDataSet.setSelectionShift(10f);//点击某个饼时拉长的宽度

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLUE);

        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.animateY(1000);

    }

    private void setPieChartData(List<Bill> bills, boolean isIncome){

    }

}