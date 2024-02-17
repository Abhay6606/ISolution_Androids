package com.example.isolution.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isolution.Model.HomeCalenderRsltGterStter;
import com.example.isolution.R;
import com.example.isolution.databinding.ItemHomeCalenderBinding;

import java.util.ArrayList;

public class HomeCalenderAdapter extends RecyclerView.Adapter<HomeCalenderAdapter.ViewHolder> {
    @NonNull
    ArrayList<HomeCalenderRsltGterStter>arraylist;
    Context context;


    public HomeCalenderAdapter(@NonNull ArrayList<HomeCalenderRsltGterStter> arraylist, Context context) {
        this.arraylist = arraylist;
    }

    @Override
    public HomeCalenderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home__calender,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull HomeCalenderAdapter.ViewHolder holder, int position) {
        HomeCalenderRsltGterStter aa = arraylist.get(position);
        holder.itemHomeCalenderBinding.singleDate.setText(aa.getDate());
        holder.itemHomeCalenderBinding.singleDay.setText(aa.getDateday());
        holder.itemHomeCalenderBinding.singleCard.setOnClickListener(view ->{
            
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemHomeCalenderBinding itemHomeCalenderBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemHomeCalenderBinding = ItemHomeCalenderBinding.bind(itemView);
        }
    }
}
