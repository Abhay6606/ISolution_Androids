package com.example.isolution.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isolution.R;

public class PromotionActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;
    TextView setting,profile,nearbyMe,favourite,notification,promotion,help,home,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        drawerLayout=findViewById(R.id.drawerLayout);
        menu=findViewById(R.id.menu);
        setting=findViewById(R.id.drwrSetting);
        profile=findViewById(R.id.drwrProfile);
        nearbyMe=findViewById(R.id.drerNearbyme);
        favourite=findViewById(R.id.drwrFavourite);
        notification=findViewById(R.id.drwrNotification);
        promotion=findViewById(R.id.drwrPromotion);
        help=findViewById(R.id.drwrHelp);
        home=findViewById(R.id.drwrHome);
        logout=findViewById(R.id.drwrLogout);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(PromotionActivity.this, MainActivity2.class);            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(PromotionActivity.this, SettingsActivity.class);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(PromotionActivity.this, ProfileActivity.class);
            }
        });
        nearbyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(PromotionActivity.this, NearbyMeActivity.class);
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(PromotionActivity.this, FavouriteActivity.class);

            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(PromotionActivity.this, NotificationActivity.class);}
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(PromotionActivity.this, LogoutActivity.class);


            }
        });
        promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(PromotionActivity.this, HelpActivity.class);

            }
        });

    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closedDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActivity(Activity activity, Class secondactivity){
        Intent intent=new Intent(activity,secondactivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closedDrawer(drawerLayout);
    }
}