package com.example.isolution.Activities.DrawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isolution.Activities.AllCategoriesActivity;
import com.example.isolution.Activities.CategoriesCardActivities.CallingDetailMain;
import com.example.isolution.Activities.CategoriesCardActivities.ContactLeadActivity;
import com.example.isolution.Activities.CategoriesCardActivities.ContectLeadForm;
import com.example.isolution.Activities.CategoriesCardActivities.MainLeadActivity;
import com.example.isolution.Activities.ChatActivity;
import com.example.isolution.Activities.FolderActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {




    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());



         //Click Listeners

        homeBinding.cardNewLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , CallingDetailMain.class);
                startActivity(intent);
            }
        });
        homeBinding.allCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , AllCategoriesActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.cardLeadGroupDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , ContactLeadActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.cardLeadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , MainLeadActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.leadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , ContectLeadForm.class);
                startActivity(intent);
            }
        });





        //code for navigation drawer
        homeBinding.includeAppBar.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(homeBinding.drawerLayout);
            }
        });
        homeBinding.includeDrawer.drwrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                recreate();
            }
        });
        homeBinding.includeDrawer.drwrSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, SettingsActivity.class);
            }
        });
        homeBinding.includeDrawer.drwrLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, LogoutActivity.class);
            }
        });
        homeBinding.includeDrawer.drwrProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, ProfileActivity.class);
            }
        });
        homeBinding.includeDrawer.drerNearbyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, NearbyMeActivity.class);
            }
        });
        homeBinding.includeDrawer.drwrFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, FavouriteActivity.class);
            }
        });
        homeBinding.includeDrawer.drwrNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, NotificationActivity.class);
            }
        });
        homeBinding.includeDrawer.drwrPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, PromotionActivity.class);
            }
        });
        homeBinding.includeDrawer.drwrHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, HelpActivity.class);
            }
        });



        //  Code for Bottom Navigaion Bar

        homeBinding.includeBottomNavigationBar.bottomNavigationView.setBackground(null);
        homeBinding.includeBottomNavigationBar.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.setting){
                    redirectActivity(HomeActivity.this, SettingsActivity.class);
                } else if (item.getItemId()==R.id.folder) {
                    redirectActivity(HomeActivity.this, FolderActivity.class);
                } else if (item.getItemId()==R.id.comment) {
                    redirectActivity(HomeActivity.this, ChatActivity.class);
                } else if (item.getItemId()==R.id.profile) {
                    redirectActivity(HomeActivity.this, ProfileActivity.class);
                } else if (item.getItemId()==R.id.homee) {

                }
                return true;
            }
        });
        homeBinding.includeBottomNavigationBar.homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HomeActivity.this, "You are already on Home Page", Toast.LENGTH_SHORT).show();

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
    public static void redirectActivity(Activity activity,Class secondactivity){
        Intent intent=new Intent(activity,secondactivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closedDrawer(homeBinding.drawerLayout);
    }













}