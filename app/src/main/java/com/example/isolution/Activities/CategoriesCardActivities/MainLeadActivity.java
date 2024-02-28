package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.isolution.databinding.ActivityMainLeadBinding;

public class MainLeadActivity extends AppCompatActivity {

   ActivityMainLeadBinding mainLeadBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLeadBinding=ActivityMainLeadBinding.inflate(getLayoutInflater());
        setContentView(mainLeadBinding.getRoot());

        mainLeadBinding.cardNewLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainLeadActivity.this, ContectLeadForm.class);
                startActivity(intent);
            }
        });
        mainLeadBinding.cardLeadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainLeadActivity.this, LeadListingActivity.class);
                startActivity(intent);
            }
        });

    }
}