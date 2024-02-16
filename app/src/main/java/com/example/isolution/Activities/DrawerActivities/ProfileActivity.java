package com.example.isolution.Activities.DrawerActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.isolution.Activities.ChatActivity;
import com.example.isolution.Activities.FolderActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityProfileBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding profileBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(profileBinding.getRoot());


        profileBinding.includeAppBar.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(profileBinding.drawerLayout);
            }
        });
        profileBinding.includeDrawer.drwrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ProfileActivity.this, HomeActivity.class);
            }
        });
        profileBinding.includeDrawer.drwrSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ProfileActivity.this, SettingsActivity.class);
            }
        });
        profileBinding.includeDrawer.drwrProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        profileBinding.includeDrawer.drerNearbyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(ProfileActivity.this, NearbyMeActivity.class);
            }
        });
        profileBinding.includeDrawer.drwrFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ProfileActivity.this, FavouriteActivity.class);

            }
        });
        profileBinding.includeDrawer.drwrNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(ProfileActivity.this, NotificationActivity.class);
            }
        });
        profileBinding.includeDrawer.drwrLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ProfileActivity.this, LogoutActivity.class);


            }
        });
        profileBinding.includeDrawer.drwrPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ProfileActivity.this, PromotionActivity.class);
            }
        });
        profileBinding.includeDrawer.drwrHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ProfileActivity.this, HelpActivity.class);

            }
        });


        //  Code for Bottom Navigaion Bar

        profileBinding.includeBottomNavigationBar.bottomNavigationView.setBackground(null);
        profileBinding.includeBottomNavigationBar.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.setting) {
                    redirectActivity(ProfileActivity.this, SettingsActivity.class);
                } else if (item.getItemId() == R.id.folder) {
                    redirectActivity(ProfileActivity.this, FolderActivity.class);
                } else if (item.getItemId() == R.id.comment) {
                    redirectActivity(ProfileActivity.this, ChatActivity.class);
                } else if (item.getItemId() == R.id.profile) {
                    Toast.makeText(ProfileActivity.this, "you Are already on Profile Activity", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.homee) {
                    redirectActivity(ProfileActivity.this, HomeActivity.class);
                }
                return true;
            }
        });
        profileBinding.includeBottomNavigationBar.homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closedDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondactivity) {
        Intent intent = new Intent(activity, secondactivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closedDrawer(profileBinding.drawerLayout);
    }
}