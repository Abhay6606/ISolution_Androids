package com.example.isolution.Activities.DrawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isolution.Activities.ChatActivity;
import com.example.isolution.Activities.FolderActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivitySettingsBinding;
import com.google.android.material.navigation.NavigationBarView;

public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding settingsBinding;
//    DrawerLayout drawerLayout;
//    ImageView menu;
//    TextView setting,profile,nearbyMe,favourite,notification,promotion,help,home,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsBinding=ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(settingsBinding.getRoot());

//        drawerLayout=findViewById(R.id.drawerLayout);
//        menu=findViewById(R.id.menu);
//        setting=findViewById(R.id.drwrSetting);
//        profile=findViewById(R.id.drwrProfile);
//        nearbyMe=findViewById(R.id.drerNearbyme);
//        favourite=findViewById(R.id.drwrFavourite);
//        notification=findViewById(R.id.drwrNotification);
//        promotion=findViewById(R.id.drwrPromotion);
//        help=findViewById(R.id.drwrHelp);
//        home=findViewById(R.id.drwrHome);
//        logout=findViewById(R.id.drwrLogout);

        settingsBinding.includeAppBar.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(settingsBinding.drawerLayout);
            }
        });
        settingsBinding.includeDrawer.drwrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SettingsActivity.this, HomeActivity.class);            }
        });
        settingsBinding.includeDrawer.drwrSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        settingsBinding.includeDrawer.drwrProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(SettingsActivity.this, ProfileActivity.class);
            }
        });
        settingsBinding.includeDrawer.drerNearbyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(SettingsActivity.this, NearbyMeActivity.class);
            }
        });
        settingsBinding.includeDrawer.drwrFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SettingsActivity.this, FavouriteActivity.class);

            }
        });
        settingsBinding.includeDrawer.drwrNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(SettingsActivity.this, NotificationActivity.class);}
        });
        settingsBinding.includeDrawer.drwrLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SettingsActivity.this, LogoutActivity.class);


            }
        });
        settingsBinding.includeDrawer.drwrPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(SettingsActivity.this, PromotionActivity.class);
            }
        });
        settingsBinding.includeDrawer.drwrHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(SettingsActivity.this, HelpActivity.class);

            }
        });



        //  Code for Bottom Navigaion Bar

        settingsBinding.includeBottomNavigationBar.bottomNavigationView.setBackground(null);
        settingsBinding.includeBottomNavigationBar.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.setting){
                    Toast.makeText(SettingsActivity.this, "you Are already on Setting Activity", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId()==R.id.folder) {
                    redirectActivity(SettingsActivity.this, FolderActivity.class);
                } else if (item.getItemId()==R.id.comment) {
                    redirectActivity(SettingsActivity.this, ChatActivity.class);
                } else if (item.getItemId()==R.id.profile) {
                    redirectActivity(SettingsActivity.this, ProfileActivity.class);
                } else if (item.getItemId()==R.id.homee) {
                    redirectActivity(SettingsActivity.this, HomeActivity.class);
                }
                return true;
            }
        });
        settingsBinding.includeBottomNavigationBar.homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
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
        closedDrawer(settingsBinding.drawerLayout);
    }
}