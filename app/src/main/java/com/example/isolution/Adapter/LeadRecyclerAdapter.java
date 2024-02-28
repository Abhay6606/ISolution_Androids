package com.example.isolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isolution.Model.FetchAllLeadsGetterSetter;
import com.example.isolution.Model.GetterSetter;
import com.example.isolution.Activities.CategoriesCardActivities.LeadCategoryActivity;
import com.example.isolution.databinding.LeadRecyclerItemBinding;

import java.util.ArrayList;

public class LeadRecyclerAdapter extends RecyclerView.Adapter<LeadRecyclerAdapter.ViewHolder> {
    @NonNull
    LayoutInflater inflater;
    Context context;
    ArrayList<FetchAllLeadsGetterSetter> arrayList=new ArrayList<>();
    LeadRecyclerItemBinding leadRecyclerItemBinding;

    public LeadRecyclerAdapter(Context context, ArrayList<FetchAllLeadsGetterSetter> ArrayList){
        this.context=context;
        this.arrayList=ArrayList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public LeadRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        leadRecyclerItemBinding=LeadRecyclerItemBinding.inflate(inflater);
        return new ViewHolder(leadRecyclerItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull LeadRecyclerAdapter.ViewHolder holder, int position) {
        FetchAllLeadsGetterSetter arraylistposion=arrayList.get(position);
        leadRecyclerItemBinding.itemName.setText(arraylistposion.getName());
        leadRecyclerItemBinding.itemCity.setText(arraylistposion.getCity());
        leadRecyclerItemBinding.itemCategory.setText(arraylistposion.getCategory_code());
        leadRecyclerItemBinding.itemStatus.setText(arraylistposion.getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LeadCategoryActivity.class);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
