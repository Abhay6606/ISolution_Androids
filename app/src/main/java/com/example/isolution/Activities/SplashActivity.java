package com.example.isolution.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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

        requestPerm();


    }
    private void requestPerm() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE}, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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


            }else {
                Toast.makeText(this, "Please Grant All Permission", Toast.LENGTH_SHORT).show();
                finish();

            }
        }
    }




}