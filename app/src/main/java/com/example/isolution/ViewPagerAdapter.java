package com.example.isolution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;

    int backgroundimages[] = {

            R.drawable.bg,
            R.drawable.bgtwo,
            R.drawable.bgthree,

    };

    int mainimages[] = {

            R.drawable.topimage,
            R.drawable.topimagetwo,
            R.drawable.topimagethree,

    };


    String headings[] = {

            "Welcome!", "Snap it", "Grabit"
    };

    public ViewPagerAdapter(Context context) {

        this.context = context;

    }


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.one_res,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.titleImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.titlefirst);
        ImageView slidemainimage=(ImageView)view.findViewById(R.id.imagee);


        slidetitleimage.setImageResource(backgroundimages[position]);
        slideHeading.setText(headings[position]);
        slidemainimage.setImageResource(mainimages[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }
}
