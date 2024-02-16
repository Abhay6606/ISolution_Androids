package com.example.isolution.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.isolution.Activities.DrawerActivities.HomeActivity;
import com.example.isolution.Activities.DrawerActivities.LogoutActivity;
import com.example.isolution.Activities.DrawerActivities.NotificationActivity;
import com.example.isolution.Activities.DrawerActivities.ProfileActivity;
import com.example.isolution.Activities.DrawerActivities.SettingsActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityFolderBinding;
import com.google.android.material.navigation.NavigationBarView;

public class FolderActivity extends AppCompatActivity {
    ActivityFolderBinding folderBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        folderBinding=ActivityFolderBinding.inflate(getLayoutInflater());
        setContentView(folderBinding.getRoot());


        //code For Bottom Naigation AppaBar

        folderBinding.includeBottomNavigationBar.bottomNavigationView.setBackground(null);
        folderBinding.includeBottomNavigationBar.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.setting){
                    redirectActivity(FolderActivity.this, SettingsActivity.class);
                } else if (item.getItemId()==R.id.folder) {

                    Toast.makeText(FolderActivity.this, "you Are already on Folder Activity", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId()==R.id.comment) {
                    redirectActivity(FolderActivity.this, ChatActivity.class);
                } else if (item.getItemId()==R.id.profile) {
                    redirectActivity(FolderActivity.this, ProfileActivity.class);
                } else if (item.getItemId()==R.id.homee) {
                    redirectActivity(FolderActivity.this, HomeActivity.class);
                }
                return true;
            }
        });
        folderBinding.includeBottomNavigationBar.homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FolderActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public static void redirectActivity(Activity activity, Class secondactivity){
        Intent intent=new Intent(activity,secondactivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}