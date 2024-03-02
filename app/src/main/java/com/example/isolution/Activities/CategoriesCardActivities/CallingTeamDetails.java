package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Adapter.CallingTeamDetailsAdapter;
import com.example.isolution.Adapter.LeadRecyclerAdapter;
import com.example.isolution.Model.CallingTeamDetailsGetterSetter;
import com.example.isolution.Model.FetchAllLeadsGetterSetter;
import com.example.isolution.databinding.ActivityCallingTeamDetailsBinding;
import com.example.isolution.databinding.CallDetailsAppbarBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CallingTeamDetails extends AppCompatActivity {
    ActivityCallingTeamDetailsBinding callingTeamDetailsBinding;
    CallDetailsAppbarBinding callDetailsAppbarBinding;

    ArrayList<CallingTeamDetailsGetterSetter> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callingTeamDetailsBinding = ActivityCallingTeamDetailsBinding.inflate(getLayoutInflater());
        setContentView(callingTeamDetailsBinding.getRoot());

        callingTeamDetailsBinding.include.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CallingTeamDetails.this, CallingDetailMain.class);
                startActivity(intent);
                finish();

            }
        });


        CallingTeamDetailsGetterSetter pack = new CallingTeamDetailsGetterSetter("https://instagram.fdel27-5.fna.fbcdn.net/v/t51.2885-19/269382591_431678785170231_3739845402388046538_n.jpg?stp=dst-jpg_s150x150&efg=e30&_nc_ht=instagram.fdel27-5.fna.fbcdn.net&_nc_cat=103&_nc_ohc=2f-l3xXFQsQAX-1yEGN&edm=ABmJApABAAAA&ccb=7-5&oh=00_AfDyr8nPqNe05uvvv2TCUZ3wk-ob5pZKi3byudyAQ2txtA&oe=65B80EAD&_nc_sid=b41fef", "Abhay Bhdauria", "23m28s", "Checked Out", "Android Developer");
        arrayList.add(pack);


        callingTeamDetailsBinding.callingTeamRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CallingTeamDetailsAdapter adapter = new CallingTeamDetailsAdapter(CallingTeamDetails.this, arrayList);
        callingTeamDetailsBinding.callingTeamRecyclerView.setAdapter(adapter);


    }



}