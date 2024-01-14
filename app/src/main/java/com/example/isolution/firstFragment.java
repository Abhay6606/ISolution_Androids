package com.example.isolution;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isolution.databinding.FragmentFirstBinding;


public class firstFragment extends Fragment implements View.OnClickListener {
    TextView textView;
    View mView;


    public firstFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      mView=inflater.inflate(R.layout.fragment_first, container, false);
      textView=mView.findViewById(R.id.allCategoriesText);
      textView.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),AllCategoriesActivity.class);
        Toast.makeText(getActivity(), "button Clicked", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}