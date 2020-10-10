package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Record;
import com.example.myapplication.R;

import java.util.List;

//public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
//
//    private List<Person> persons;
//
//    public RVAdapter(List<Person> persons){
//        this.persons = persons;
//    }
//
//    @NonNull
//    @Override
//    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_cardview, parent, false);
//        PersonViewHolder pvh = new PersonViewHolder(v);
//        return pvh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
//        holder.personName.setText(persons.get(position).name);
//        holder.personAge.setText(persons.get(position).age);
//    }
//
//    @Override
//    public int getItemCount() {
//        return persons.size();
//    }
//
//public static class PersonViewHolder extends RecyclerView.ViewHolder {
//    CardView cv;
//    TextView personName;
//    TextView personAge;
//
//    PersonViewHolder(View itemView) {
//        super(itemView);
//        cv = (CardView)itemView.findViewById(R.id.cv);
//        personName = (TextView)itemView.findViewById(R.id.person_name);
//        personAge = (TextView)itemView.findViewById(R.id.person_age);
//    }
//}
//
//    @Override
//    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//
//
//}