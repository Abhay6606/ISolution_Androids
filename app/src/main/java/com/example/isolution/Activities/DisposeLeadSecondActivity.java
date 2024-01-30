package com.example.isolution.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.isolution.R;
import com.example.isolution.databinding.ActivityDisposeLeadSecondBinding;

public class DisposeLeadSecondActivity extends AppCompatActivity {

    ActivityDisposeLeadSecondBinding disposeLeadSecondBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposeLeadSecondBinding=ActivityDisposeLeadSecondBinding.inflate(getLayoutInflater());
        setContentView(disposeLeadSecondBinding.getRoot());

        disposeLeadSecondBinding.notConnectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisposeLeadSecondActivity.this, DisposeLeadActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}