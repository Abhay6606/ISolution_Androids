package com.example.isolution.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.isolution.Adapter.CallingDetailAdapter;
import com.example.isolution.Model.CallingDetailsGetterSetter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityCallingDetailsBinding;

import java.util.ArrayList;

public class CallingDetailsActivity extends AppCompatActivity {
  ActivityCallingDetailsBinding  callingDetailsBinding;
  ArrayList<CallingDetailsGetterSetter>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callingDetailsBinding=ActivityCallingDetailsBinding.inflate(getLayoutInflater());
        setContentView(callingDetailsBinding.getRoot());

        CallingDetailsGetterSetter pack= new CallingDetailsGetterSetter("https://instagram.fdel27-5.fna.fbcdn.net/v/t51.2885-19/269382591_431678785170231_3739845402388046538_n.jpg?stp=dst-jpg_s150x150&efg=e30&_nc_ht=instagram.fdel27-5.fna.fbcdn.net&_nc_cat=103&_nc_ohc=2f-l3xXFQsQAX-1yEGN&edm=ABmJApABAAAA&ccb=7-5&oh=00_AfDyr8nPqNe05uvvv2TCUZ3wk-ob5pZKi3byudyAQ2txtA&oe=65B80EAD&_nc_sid=b41fef","Abhay Bhadauria","6398960435","23m12s");
        arrayList.add(pack);

        callingDetailsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CallingDetailAdapter adapter=new CallingDetailAdapter(this,arrayList);
        callingDetailsBinding.recyclerView.setAdapter(adapter);



    }
}
