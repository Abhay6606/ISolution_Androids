package com.example.isolution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AllCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        getSupportActionBar().hide();
    }
}