package com.example.isolution.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.isolution.Adapter.ViewPagerAdapter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position == 0) {

                mainBinding.button.setText("Get Started");
                mainBinding.button.setBackground(getDrawable(R.drawable.button_background));
                mainBinding.deshboardText.setText("A Unique & Adavance Tool for you\ncalling solutions Manage Your Call\nWith i-Solution");
                mainBinding.constntId.setBackgroundColor(getResources().getColor(R.color.pink));


            } else if (position == 1) {
                mainBinding.button.setText("NEXTStep");
                mainBinding.button.setBackground(getDrawable(R.drawable.button_orange_background));
                mainBinding.deshboardText.setText("Convert Your Inquiries to Clients....\nand Manage & Maintain Your Clients\nwith I-Solutions");
                mainBinding.constntId.setBackgroundColor(getResources().getColor(R.color.purple_200));
            } else if (position == 2) {
                mainBinding.button.setText("Finish");
                mainBinding.button.setBackground(getDrawable(R.drawable.button_background));
                mainBinding.deshboardText.setText("manage & Grow Your Business with\npalm, lets your working more\nproductive with i-solution");
                mainBinding.constntId.setBackgroundColor(getResources().getColor(R.color.orange));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mainBinding.viewPager.setCurrentItem(getitem(1), true);
                else {

                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });


        viewPagerAdapter = new ViewPagerAdapter(this);

        mainBinding.viewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mainBinding.viewPager.addOnPageChangeListener(viewListener);

    }

    public void setUpindicator(int position) {

        dots = new TextView[3];
        mainBinding.dotLinear.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            mainBinding.dotLinear.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));

    }

    private int getitem(int i) {

        return mainBinding.viewPager.getCurrentItem() + i;
    }

}





