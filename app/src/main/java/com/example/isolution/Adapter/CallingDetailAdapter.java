package com.example.isolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isolution.Activities.EmployTeamDetails;
import com.example.isolution.Model.CallingDetailsGetterSetter;
import com.example.isolution.databinding.CallingDetailsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CallingDetailAdapter extends RecyclerView.Adapter<CallingDetailAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    ArrayList<CallingDetailsGetterSetter> arrayList=new ArrayList<>();
  CallingDetailsItemBinding callingDetailsItemBinding;

    public CallingDetailAdapter(Context context, ArrayList<CallingDetailsGetterSetter> ArrayList){
        this.context=context;
        this.arrayList=ArrayList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CallingDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        callingDetailsItemBinding=CallingDetailsItemBinding.inflate(inflater);
        return new ViewHolder(callingDetailsItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CallingDetailAdapter.ViewHolder holder, int position) {
        CallingDetailsGetterSetter arraylistPosition=arrayList.get(position);
        callingDetailsItemBinding.name.setText(arraylistPosition.getName());
        callingDetailsItemBinding.number.setText(arraylistPosition.getNumber());
        callingDetailsItemBinding.time.setText(arraylistPosition.getTime());
        Picasso.get().load(arraylistPosition.getImage()).into(callingDetailsItemBinding.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
