package com.example.isolution.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.isolution.R;
import com.example.isolution.databinding.ActivityDisposeLeadBinding;

public class DisposeLeadActivity extends AppCompatActivity {

    ActivityDisposeLeadBinding disposeLeadBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposeLeadBinding=ActivityDisposeLeadBinding.inflate(getLayoutInflater());
        setContentView(disposeLeadBinding.getRoot());

        disposeLeadBinding.yesConnectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisposeLeadActivity.this, DisposeLeadSecondActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}