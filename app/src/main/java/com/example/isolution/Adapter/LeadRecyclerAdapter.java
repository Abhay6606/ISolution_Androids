package com.example.isolution.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        leadRecyclerItemBinding.itemCategory.setText(arraylistposion.getCategory_code_name());
        leadRecyclerItemBinding.itemStatus.setText(arraylistposion.getDisp_pri_code_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, LeadCategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Bundle bundle = new Bundle();
                bundle.putString("id",arraylistposion.getId());
                bundle.putString("user_id",arraylistposion.getUser_id());
                bundle.putString("name",arraylistposion.getName());
                bundle.putString("mobile_number",arraylistposion.getMobile_number());
                bundle.putString("email_id",arraylistposion.getEmail_id());
                bundle.putString("date_of_birth",arraylistposion.getDate_of_birth());
                bundle.putString("age",arraylistposion.getAge());
                bundle.putString("whatsapp_no",arraylistposion.getWhatsapp_no());
                bundle.putString("alt_mobile",arraylistposion.getAlt_mobile());
                bundle.putString("selfie_filename",arraylistposion.getSelfie_filename());
                bundle.putString("source",arraylistposion.getSource());
                bundle.putString("city",arraylistposion.getCity());
                bundle.putString("state",arraylistposion.getState());
                bundle.putString("pincode",arraylistposion.getPincode());
                bundle.putString("allocated_date",arraylistposion.getAllocated_date());
                bundle.putString("follow_up_date",arraylistposion.getFollow_up_date());
                bundle.putString("last_reallocated_date",arraylistposion.getLast_reallocated_date());
                bundle.putString("disp_pri_code",arraylistposion.getDisp_pri_code());
                bundle.putString("disp_lead_type",arraylistposion.getDisp_lead_type());
                bundle.putString("disp_attempt_count",arraylistposion.getDisp_attempt_count());
                bundle.putString("comments",arraylistposion.getComments());
                bundle.putString("category_code",arraylistposion.getCategory_code());
                bundle.putString("sub_category_code",arraylistposion.getCategory_code_name());
                bundle.putString("status",arraylistposion.getStatus());
                bundle.putString("is_visible_lead",arraylistposion.getIs_visible_lead());
                bundle.putString("last_contacted_at",arraylistposion.getLast_contacted_at());
                bundle.putString("created_at",arraylistposion.getCreated_at());
                bundle.putString("updated_at",arraylistposion.getUpdated_at());
                bundle.putString("source_name",arraylistposion.getSource_name());
                bundle.putString("category_code_name",arraylistposion.getCategory_code_name());
                bundle.putString("disp_pri_code_name",arraylistposion.getDisp_pri_code_name());
                bundle.putString("user_id_name",arraylistposion.getUser_id_name());


                intent.putExtras(bundle);


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
