package com.example.isolution.Activities.DrawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isolution.Activities.AllCategoriesActivity;
import com.example.isolution.Activities.CallingDetailMain;
import com.example.isolution.Activities.ContactLeadActivity;
import com.example.isolution.Activities.ContectLeadForm;
import com.example.isolution.Activities.MainLeadActivity;
import com.example.isolution.R;
public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button leadbtn;
    ImageView menu;
    TextView setting,profile,nearbyMe,favourite,notification,promotion,help,home,allCategories,logout;
    CardView cardCallingDetais,cardLeadDetails,cardLeadGroupDetails,cardFollowupDetails,cardCallRecoreding,cardAgentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout=findViewById(R.id.drawerLayout);
        menu=findViewById(R.id.menu);
        setting=findViewById(R.id.drwrSetting);
        logout=findViewById(R.id.drwrLogout);
        profile=findViewById(R.id.drwrProfile);
        leadbtn=findViewById(R.id.leadbtn);
        nearbyMe=findViewById(R.id.drerNearbyme);
        favourite=findViewById(R.id.drwrFavourite);
        notification=findViewById(R.id.drwrNotification);
        promotion=findViewById(R.id.drwrPromotion);
        help=findViewById(R.id.drwrHelp);
        home=findViewById(R.id.drwrHome);
        cardCallingDetais=findViewById(R.id.cardNewLeads);
        cardLeadDetails=findViewById(R.id.cardLeadInfo);
        cardLeadGroupDetails=findViewById(R.id.cardLeadGroupDetails);
        cardFollowupDetails=findViewById(R.id.cardFollowupDetails);
        cardCallRecoreding=findViewById(R.id.cardCallRecordingDetails);
        cardAgentDetails=findViewById(R.id.cardAjentDetails);
        allCategories=findViewById(R.id.allCategories);

//        headerLayoutBinding.drwrLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity2.this, "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

         //Click Listeners

        cardCallingDetais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , CallingDetailMain.class);
                startActivity(intent);
            }
        });
        allCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , AllCategoriesActivity.class);
                startActivity(intent);
            }
        });
        cardLeadGroupDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , ContactLeadActivity.class);
                startActivity(intent);
            }
        });
        cardLeadDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , MainLeadActivity.class);
                startActivity(intent);
            }
        });
        leadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this , ContectLeadForm.class);
                startActivity(intent);
            }
        });













        //code for navigation drawer
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                recreate();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, SettingsActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, LogoutActivity.class);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, ProfileActivity.class);
            }
        });
        nearbyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, NearbyMeActivity.class);
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, FavouriteActivity.class);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, NotificationActivity.class);
            }
        });
        promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, PromotionActivity.class);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(HomeActivity.this, HelpActivity.class);
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
        closedDrawer(drawerLayout);
    }
}