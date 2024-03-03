package com.example.isolution.Activities.DrawerActivities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
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
import com.example.isolution.Activities.CategoriesCardActivities.CallingTeamDetails;
import com.example.isolution.Activities.CategoriesCardActivities.LeadListingActivity;
import com.example.isolution.Activities.CategoriesCardActivities.ContectLeadForm;
import com.example.isolution.Activities.ChatActivity;
import com.example.isolution.Activities.FolderActivity;
import com.example.isolution.Adapter.HomeCalenderAdapter;
import com.example.isolution.Model.HomeCalenderRsltGterStter;
import com.example.isolution.Model.HomePercentageArrayGtterStter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityHomeBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding homeBinding;
    ArrayList<HomePercentageArrayGtterStter> perctgeArraylst = new ArrayList<>();

    ArrayList<HomeCalenderRsltGterStter> calnderArryLst = new ArrayList<>();

    // Strings for userData
    String email, isBlocked, mobileNumber, name, roleName, selfieImage, userName;

    // Strings for bannerData
    String average_duration, incoming, leadTotal, missed, outgoing, totalCalls;
    String date;
    String startDateString="",endDateString="";
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        SharedPreferences pref = getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit().putBoolean("flag", true);
        editor.apply();

        mProgressDialog = new ProgressDialog(HomeActivity.this);
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Logging in...");



        requestPerm();


        //Click Listeners


        homeBinding.today.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                homeBinding.today.setBackgroundResource(R.drawable.selected_tab);
                homeBinding.today.setTextColor(Color.parseColor("#ffffff"));

                homeBinding.lastSeven.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.lastSeven.setTextColor(Color.parseColor("#e53538"));

                homeBinding.lastThirty.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.lastThirty.setTextColor(Color.parseColor("#e53538"));


                homeBinding.select.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.select.setTextColor(Color.parseColor("#e53538"));

                currentdate();
                startDateString=previousdate(0);

                getApiRequest();

            }
        });
        homeBinding.lastSeven.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                homeBinding.lastSeven.setBackgroundResource(R.drawable.selected_tab);
                homeBinding.lastSeven.setTextColor(Color.parseColor("#ffffff"));

                homeBinding.today.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.today.setTextColor(Color.parseColor("#e53538"));

                homeBinding.lastThirty.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.lastThirty.setTextColor(Color.parseColor("#e53538"));


                homeBinding.select.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.select.setTextColor(Color.parseColor("#e53538"));


                currentdate();
                startDateString=previousdate(7);
                getApiRequest();


            }
        });
        homeBinding.lastThirty.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                homeBinding.lastThirty.setBackgroundResource(R.drawable.selected_tab);
                homeBinding.lastThirty.setTextColor(Color.parseColor("#ffffff"));

                homeBinding.lastSeven.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.lastSeven.setTextColor(Color.parseColor("#e53538"));

                homeBinding.today.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.today.setTextColor(Color.parseColor("#e53538"));


                homeBinding.select.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.select.setTextColor(Color.parseColor("#e53538"));



                currentdate();
                startDateString=previousdate(30);
                getApiRequest();


            }
        });
        homeBinding.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog();
            }
        });









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
                Intent intent = new Intent(HomeActivity.this, CallingTeamDetails.class);
                startActivity(intent);
            }
        });
        homeBinding.cardLeadInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LeadListingActivity.class);
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



//                redirectActivity(HomeActivity.this, LogoutActivity.class);
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


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        android.icu.text.SimpleDateFormat df = new android.icu.text.SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        endDateString = df.format(c);
        startDateString = df.format(c);

        homeBinding.today.setBackgroundResource(R.drawable.selected_tab);
        homeBinding.today.setTextColor(Color.parseColor("#ffffff"));


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

        mProgressDialog.show();
        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");




        String url = "https://callcrm.techfreelancepro.com/api/dashboard/list?date_start="+startDateString+"&date_end="+endDateString;
        RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("check", response.toString());
                try {
                    calnderArryLst.clear();
                    perctgeArraylst.clear();

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
                        // Saturday March 2 2024
                        packk.setDate(object.getString("date"));
                        packk.setKey(object.getString("key"));
                        packk.setCount(object.getString("count"));
                        date=dateFormatter(object.getString("dateDay"));
                        packk.setDateday(date);
                        Log.d("itr",date);

                        calnderArryLst.add(packk);

                    }
                    mProgressDialog.hide();

                    HomeCalenderAdapter adapter = new HomeCalenderAdapter(calnderArryLst, HomeActivity.this);
                    homeBinding.recyclerView.setAdapter(adapter);


                } catch (Exception e) {
                    mProgressDialog.hide();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
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

    private String dateFormatter(String string) {
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
    // code for taking Permition

    private void requestPerm() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE}, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public String previousdate(int num)
    {
        final LocalDate date = LocalDate.now();
        final LocalDate dateMinus7Days = date.minusDays(num);

        final String formattedDate = dateMinus7Days.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(formattedDate);

        return formattedDate;
    }

    public void currentdate()
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        android.icu.text.SimpleDateFormat df = new android.icu.text.SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        endDateString = df.format(c);


    }
    private void datePickerDialog() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select Date Range");
        builder.setTheme(R.style.MyMaterialCalendarTheme);

        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                startDateString = sdf.format(new Date(startDate));
                endDateString = sdf.format(new Date(endDate));

                homeBinding.select.setBackgroundResource(R.drawable.selected_tab);
                homeBinding.select.setTextColor(Color.parseColor("#ffffff"));

                homeBinding.lastSeven.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.lastSeven.setTextColor(Color.parseColor("#e53538"));

                homeBinding.lastThirty.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.lastThirty.setTextColor(Color.parseColor("#e53538"));


                homeBinding.today.setBackgroundResource(R.drawable.orange_border_rectangle);
                homeBinding.today.setTextColor(Color.parseColor("#e53538"));



                getApiRequest();
            }

        });
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }
}