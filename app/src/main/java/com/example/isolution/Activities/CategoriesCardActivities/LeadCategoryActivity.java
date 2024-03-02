package com.example.isolution.Activities.CategoriesCardActivities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.isolution.databinding.ActivityLeadCategoryBinding;
import com.example.isolution.databinding.CustomDilogboxBinding;

public class LeadCategoryActivity extends AppCompatActivity {
    ActivityLeadCategoryBinding leadCategoryBinding;
    CustomDilogboxBinding customDilogboxBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leadCategoryBinding = ActivityLeadCategoryBinding.inflate(getLayoutInflater());
        setContentView(leadCategoryBinding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name", "Default");
        String number=bundle.getString("mobile_number","Default");
        String  status= bundle.getString("disp_pri_code_name","Default");
        String topic= bundle.getString("category_code_name","Defoult");

        leadCategoryBinding.namee.setText(name);
        leadCategoryBinding.numberr.setText(number);
        leadCategoryBinding.statuss.setText(status);



        leadCategoryBinding.disposeLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeadCategoryActivity.this, DisposeLeadActivity.class);
                startActivity(intent);
            }
        });

        // Code for Dialogbox

        leadCategoryBinding.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDilogboxBinding = CustomDilogboxBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(LeadCategoryActivity.this);
               dialog.setContentView(customDilogboxBinding.getRoot());
                dialog.setCancelable(false);



                customDilogboxBinding.conName.setText(name);
                customDilogboxBinding.conNum.setText(number);
                customDilogboxBinding.conStatus.setText(status);
                customDilogboxBinding.conTopic.setText(topic);
                customDilogboxBinding.backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(LeadCategoryActivity.this, "Dialogbox closed", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }
}