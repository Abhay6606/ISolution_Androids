package com.example.isolution.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.isolution.Activities.DrawerActivities.HomeActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivitySplashBinding;
import com.example.isolution.util.SessionManager;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding splashBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(splashBinding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref =getSharedPreferences("check",MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag", false);
                Intent intent;


                if (check) {
                    intent = new Intent(SplashActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }


          //      Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);



    }

}