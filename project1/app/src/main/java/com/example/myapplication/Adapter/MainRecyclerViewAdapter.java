package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.Bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder> {

    List<Bill> allBills = new ArrayList<>();

    public void setAllBills(List<Bill> allBills) {
        this.allBills = allBills;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_cell, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Bill bill = allBills.get(position);
            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("HH:mm:ss");

            holder.textAmount.setText(String.valueOf(bill.getAmount()));
            holder.textId.setText(String.valueOf(bill.getId()));
            holder.textTime.setText(formatter.format(bill.getTime()));
            holder.textType.setText(String.valueOf(bill.getType()));
            holder.textRemark.setText(bill.getRemark());

    }

    @Override
    public int getItemCount() {
        return allBills.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textAmount,textType,textId,textTime,textRemark;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textAmount = itemView.findViewById(R.id.textViewAmount);
            textType = itemView.findViewById(R.id.textViewType);
            textId = itemView.findViewById(R.id.textViewId);
            textRemark = itemView.findViewById(R.id.textViewRemark);
            textTime = itemView.findViewById(R.id.textViewTime);

        }
    }
}
