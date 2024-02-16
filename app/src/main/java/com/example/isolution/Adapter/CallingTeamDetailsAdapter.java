package com.example.isolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isolution.Activities.CategoriesCardActivities.EmployTeamDetails;
import com.example.isolution.Model.CallingTeamDetailsGetterSetter;
import com.example.isolution.databinding.CallingTeamdetailsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CallingTeamDetailsAdapter extends RecyclerView.Adapter<CallingTeamDetailsAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    ArrayList<CallingTeamDetailsGetterSetter> arrayList=new ArrayList<>();
    CallingTeamdetailsItemBinding callingTeamdetailsItemBinding;

    public CallingTeamDetailsAdapter(Context context, ArrayList<CallingTeamDetailsGetterSetter> ArrayList){
        this.context=context;
        this.arrayList=ArrayList;
        inflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CallingTeamDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        callingTeamdetailsItemBinding=CallingTeamdetailsItemBinding.inflate(inflater);
        return new ViewHolder(callingTeamdetailsItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CallingTeamDetailsAdapter.ViewHolder holder, int position) {
        CallingTeamDetailsGetterSetter arraylistposition=arrayList.get(position);
        callingTeamdetailsItemBinding.itemName.setText(arraylistposition.getName());
        callingTeamdetailsItemBinding.itemStatus.setText(arraylistposition.getStatus());
        callingTeamdetailsItemBinding.itemPosition.setText(arraylistposition.getPosition());
        callingTeamdetailsItemBinding.itemTime.setText(arraylistposition.getTime());
        Picasso.get().load(arraylistposition.getImage()).into(callingTeamdetailsItemBinding.itemimageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EmployTeamDetails.class);
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
