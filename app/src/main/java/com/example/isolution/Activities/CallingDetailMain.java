package com.example.isolution.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.isolution.databinding.ActivityCallingDetailMainBinding;
import com.example.isolution.databinding.ActivityCallingDetailsBinding;

public class CallingDetailMain extends AppCompatActivity {

    ActivityCallingDetailMainBinding callingDetailMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        callingDetailMainBinding=ActivityCallingDetailMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(callingDetailMainBinding.getRoot());

        callingDetailMainBinding.recievedCallsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                startActivity(intent);
            }
        });
        callingDetailMainBinding.missedCallsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                startActivity(intent);
            }
        });
        callingDetailMainBinding.dialCallsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                startActivity(intent);
            }
        });
        callingDetailMainBinding.callRecordingCArds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                startActivity(intent);
            }
        });
        callingDetailMainBinding.callingTeamDetailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this, CallingTeamDetails.class);
                startActivity(intent);
            }
        });



    }
}