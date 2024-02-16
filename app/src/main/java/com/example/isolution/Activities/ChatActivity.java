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
import com.example.isolution.Activities.DrawerActivities.NotificationActivity;
import com.example.isolution.Activities.DrawerActivities.ProfileActivity;
import com.example.isolution.Activities.DrawerActivities.SettingsActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityChatBinding;
import com.google.android.material.navigation.NavigationBarView;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding chatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatBinding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(chatBinding.getRoot());


        //code For Bottom Naigation AppaBar

        chatBinding.includeBottomNavigationBar.bottomNavigationView.setBackground(null);
        chatBinding.includeBottomNavigationBar.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.setting){
                    redirectActivity(ChatActivity.this, SettingsActivity.class);
                } else if (item.getItemId()==R.id.folder) {
                    redirectActivity(ChatActivity.this, FolderActivity.class);

                } else if (item.getItemId()==R.id.comment) {
                    Toast.makeText(ChatActivity.this, "you Are already on Chat Activity", Toast.LENGTH_SHORT).show();

                } else if (item.getItemId()==R.id.profile) {
                    redirectActivity(ChatActivity.this, ProfileActivity.class);
                } else if (item.getItemId()==R.id.homee) {
                    redirectActivity(ChatActivity.this, HomeActivity.class);
                }
                return true;
            }
        });
        chatBinding.includeBottomNavigationBar.homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatActivity.this, HomeActivity.class);
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