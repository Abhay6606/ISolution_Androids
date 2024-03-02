package com.example.isolution.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isolution.Model.CallDataGetterSetter;
import com.example.isolution.Model.CallLogsModelGetter;
import com.example.isolution.Model.CallingDetailsGetterSetter;
import com.example.isolution.databinding.CallingDetailsItemBinding;

import java.util.ArrayList;

public class CallingDetailAdapter extends RecyclerView.Adapter<CallingDetailAdapter.ViewHolder> {
    LayoutInflater inflater;
    Context context;
    ArrayList<CallDataGetterSetter> arrayList=new ArrayList<>();
  CallingDetailsItemBinding callingDetailsItemBinding;

    public CallingDetailAdapter(Context context, ArrayList<CallDataGetterSetter> arrayList){
        this.context=context;
        this.arrayList=arrayList;
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
        CallDataGetterSetter arraylistPosition=arrayList.get(position);
        callingDetailsItemBinding.name.setText(arraylistPosition.getLead_name());
        callingDetailsItemBinding.number.setText(arraylistPosition.getMobile_number());
        callingDetailsItemBinding.time.setText(arraylistPosition.getTalktime());
        callingDetailsItemBinding.callDuration.setText(arraylistPosition.getCall_start_time());
//        Picasso.get().load(arraylistPosition.getImage()).into(callingDetailsItemBinding.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
