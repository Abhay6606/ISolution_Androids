package com.example.isolution.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.isolution.R;
import com.example.isolution.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    Button button;
    ConstraintLayout constraintLayout;
    TextView textView;
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

                button.setText("Get Started");
                button.setBackground(getDrawable(R.drawable.button_background));
                textView.setText("A Unique & Adavance Tool for you\ncalling solutions Manage Your Call\nWith i-Solution");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.pink));


            } else if (position == 1) {
                button.setText("NEXTStep");
                button.setBackground(getDrawable(R.drawable.button_orange_background));
                textView.setText("Convert Your Inquiries to Clients....\nand Manage & Maintain Your Clients\nwith I-Solutions");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.purple_200));
            } else if (position == 2) {
                button.setText("Finish");
                button.setBackground(getDrawable(R.drawable.button_background));
                textView.setText("manage & Grow Your Business with\npalm, lets your working more\nproductive with i-solution");
                constraintLayout.setBackgroundColor(getResources().getColor(R.color.orange));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        mSLideViewPager = findViewById(R.id.viewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotLinear);
        textView = findViewById(R.id.deshboardText);
        constraintLayout = findViewById(R.id.constntId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 2)
                    mSLideViewPager.setCurrentItem(getitem(1), true);
                else {

                    Intent i = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });


        viewPagerAdapter = new ViewPagerAdapter(this);

        mSLideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

    }

    public void setUpindicator(int position) {

        dots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));

    }

    private int getitem(int i) {

        return mSLideViewPager.getCurrentItem() + i;
    }

}





