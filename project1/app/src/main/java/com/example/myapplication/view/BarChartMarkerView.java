package com.example.myapplication.view;

import android.content.Context;
import android.widget.TextView;

import com.example.myapplication.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;


public class BarChartMarkerView extends MarkerView {
    private TextView tvContent;

    public BarChartMarkerView(Context context) {
        super(context,R.layout.bar_chart_marker_view);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String content = (int) e.getX() + "日" +
                new DecimalFormat("￥,###.##").format(e.getY());
        tvContent.setText(content);
        if(e.getY() > 0){
            tvContent.setVisibility(VISIBLE);
        } else {
            tvContent.setVisibility(GONE);
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-((float)getWidth() / 2), -getHeight());
    }
}
