package com.example.isolution.Activities.DrawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Activities.AllCategoriesActivity;
import com.example.isolution.Activities.CategoriesCardActivities.CallingDetailMain;
import com.example.isolution.Activities.CategoriesCardActivities.LeadListingActivity;
import com.example.isolution.Activities.CategoriesCardActivities.ContectLeadForm;
import com.example.isolution.Activities.CategoriesCardActivities.MainLeadActivity;
import com.example.isolution.Activities.ChatActivity;
import com.example.isolution.Activities.FolderActivity;
import com.example.isolution.Adapter.HomeCalenderAdapter;
import com.example.isolution.Model.HomeCalenderRsltGterStter;
import com.example.isolution.Model.HomePercentageArrayGtterStter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding homeBinding;
    ArrayList<HomePercentageArrayGtterStter> perctgeArraylst = new ArrayList<>();
    ArrayList<HomeCalenderRsltGterStter> calnderArryLst = new ArrayList<>();

    // Strings for userData
    String email, isBlocked, mobileNumber, name, roleName, selfieImage, userName;

    // Strings for bannerData
    String average_duration, incoming, leadTotal, missed, outgoing, totalCalls;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        SharedPreferences pref=getSharedPreferences("check",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit().putBoolean("flag", true);
        editor.apply();




        requestPerm();



        //Click Listeners
        homeBinding.cardNewLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CallingDetailMain.class);
                startActivity(intent);
            }
        });
        homeBinding.allCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AllCategoriesActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.cardLeadGroupDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LeadListingActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.cardLeadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainLeadActivity.class);
                startActivity(intent);
            }
        });
        homeBinding.leadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ContectLeadForm.class);
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
            public void onClick(View v) {
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
                if (item.getItemId() == R.id.setting) {
                    redirectActivity(HomeActivity.this, SettingsActivity.class);
                } else if (item.getItemId() == R.id.folder) {
                    redirectActivity(HomeActivity.this, FolderActivity.class);
                } else if (item.getItemId() == R.id.comment) {
                    redirectActivity(HomeActivity.this, ChatActivity.class);
                } else if (item.getItemId() == R.id.profile) {
                    redirectActivity(HomeActivity.this, ProfileActivity.class);
                } else if (item.getItemId() == R.id.homee) {

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

    @Override
    protected void onResume() {
        
        getApiRequest();

        super.onResume();
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
        closedDrawer(homeBinding.drawerLayout);
    }

    // Method for Api

    private JSONObject getApiRequest() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");

        mProgressDialog = new ProgressDialog(HomeActivity.this);
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.show();


        String url = "https://callcrm.techfreelancepro.com/api/dashboard/list?date_start=2024-02-01&date_end=2024-02-16";
        RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("check", response.toString());
                try {
                    JSONObject respObj = new JSONObject(response);
                    JSONObject result = respObj.getJSONObject("result");
                    JSONArray percentageArray = result.getJSONArray("percentageArray");
                    JSONObject userData = result.getJSONObject("userData");
                    JSONObject bannerData = result.getJSONObject("bannerData");
                    JSONObject calenderData = result.getJSONObject("calenderData");
                    JSONArray calenerResult = calenderData.getJSONArray("result");

                    //==============================================================================
                    //Extecting userData details

                    email = userData.getString("email");
                    isBlocked = userData.getString("isBlocked");
                    mobileNumber = userData.getString("mobileNumber");
                    name = userData.getString("name");
                    roleName = userData.getString("roleName");
                    selfieImage = userData.getString("selfieImage");
                    userName = userData.getString("userName");


                    // Code for set JsonResponse Data

                    Picasso.get().load(selfieImage).into(homeBinding.circleImageView);
                    homeBinding.nameuser.setText("Hello " + name);
                    homeBinding.roleName.setText(roleName);

                    //==============================================================================
                    //Extecting bannerData details

                    average_duration = bannerData.getString("average_duration");
  //                  incoming = bannerData.getString("incoming");
                    leadTotal = bannerData.getString("leadTotal");
//                    missed = bannerData.getString("missed");
//                    outgoing = bannerData.getString("outgoing");
                    totalCalls = bannerData.getString("totalCalls");

                    // Code for set JsonResponse Data

                    homeBinding.avgTalktime.setText(average_duration + "min");
                    homeBinding.incoming.setText(incoming);
                    homeBinding.totalLeads.setText(leadTotal);
                    homeBinding.missed.setText(missed);
                    homeBinding.outgoing.setText(outgoing);
                    homeBinding.totalCalls.setText(totalCalls);


                    //==============================================================================
                    // Iterating persentage jsonResponce

                    for (int i = 0; i < percentageArray.length(); i++) {
                        JSONObject e = percentageArray.getJSONObject(i);
                        HomePercentageArrayGtterStter pack = new HomePercentageArrayGtterStter();

                        pack.setCount(String.valueOf(e.getInt("count")));
                        pack.setName(e.getString("name"));
                        pack.setCode(e.getString("code"));

                        perctgeArraylst.add(pack);


                    }

                    //================================================================================================
                    // Iterating Calender jsonResponce

                    for (int i = 0; i < calenerResult.length(); i++) {
                        JSONObject object = calenerResult.getJSONObject(i);
                        HomeCalenderRsltGterStter packk = new HomeCalenderRsltGterStter();
                        packk.setCount(object.getString("count"));

                        calnderArryLst.add(packk);
                        Log.d("itr", packk.toString());

                    }
                    mProgressDialog.hide();
                    HomeCalenderAdapter adapter=new HomeCalenderAdapter(calnderArryLst,HomeActivity.this);
                    homeBinding.recyclerView.setAdapter(adapter);



                } catch (Exception e) {
                    mProgressDialog.hide();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-user-id", userId);
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
        queue.add(request);

        return new JSONObject();

    }
    private String dateFormatter(String string){
      //  String dateString = "Friday February 16 2024";
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE MMMM dd yyyy");
        Date date = null;
        try {
            date = formatter.parse(string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd\nEEE");
        String formattedDate = targetFormat.format(date);

        return formattedDate;

    }

    //====================================
    // code for Retrieve data callLogs


    private  void requestPerm(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE},1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1000){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Granted",Toast.LENGTH_SHORT).show();
            }
        }
    }
}