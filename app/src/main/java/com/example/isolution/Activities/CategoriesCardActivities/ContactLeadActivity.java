package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.isolution.Adapter.LeadRecyclerAdapter;
import com.example.isolution.Model.GetterSetter;
import com.example.isolution.databinding.ActivityContactLeadBinding;

import java.util.ArrayList;

public class ContactLeadActivity extends AppCompatActivity {
  ActivityContactLeadBinding contactLeadBinding;
  ArrayList<GetterSetter>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactLeadBinding=ActivityContactLeadBinding.inflate(getLayoutInflater());
        setContentView(contactLeadBinding.getRoot());

        GetterSetter pack=new GetterSetter("Abhay","Etawah","Hot,Worm,Followup","Worm");
        arrayList.add(pack);
        GetterSetter packk=new GetterSetter("Deep","Etawah","Hot,Worm,Followup","Hot");
        arrayList.add(packk);
        GetterSetter packkk=new GetterSetter("Amit","Etawah","Hot,Worm,Followup","Follow up");
        arrayList.add(packkk);




        contactLeadBinding.recyclerViewdetails.setLayoutManager(new LinearLayoutManager(this));
        LeadRecyclerAdapter adapter=new LeadRecyclerAdapter(this,arrayList);
        contactLeadBinding.recyclerViewdetails.setAdapter(adapter);





    }
}