package com.example.isolution.Activities.DrawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Activities.CategoriesCardActivities.LeadCategoryActivity;
import com.example.isolution.Activities.LoginActivity;
import com.example.isolution.R;
import com.example.isolution.databinding.CustomDilogboxBinding;
import com.example.isolution.databinding.LogoutDialogboxBinding;
import com.example.isolution.util.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogoutActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;
    TextView setting, profile, nearbyMe, favourite, notification, promotion, help, home, logout;

    LogoutDialogboxBinding logoutDialogboxBinding;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        setting = findViewById(R.id.drwrSetting);
        profile = findViewById(R.id.drwrProfile);
        nearbyMe = findViewById(R.id.drerNearbyme);
        favourite = findViewById(R.id.drwrFavourite);
        notification = findViewById(R.id.drwrNotification);
        promotion = findViewById(R.id.drwrPromotion);
        help = findViewById(R.id.drwrHelp);
        btn=findViewById(R.id.btn);
        home = findViewById(R.id.drwrHome);
        logout = findViewById(R.id.drwrLogout);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenDestroy();
            }
        });




        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, HomeActivity.class);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, SettingsActivity.class);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, ProfileActivity.class);
            }
        });
        nearbyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, NearbyMeActivity.class);
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, FavouriteActivity.class);

            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, NotificationActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();

            }
        });
        promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, PromotionActivity.class);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(LogoutActivity.this, HelpActivity.class);

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
        closedDrawer(drawerLayout);
    }

    private void tokenDestroy() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");


        String url = "https://callcrm.techfreelancepro.com/api/tokenLogOut";
        RequestQueue queue = Volley.newRequestQueue(LogoutActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject respObj = new JSONObject(response);
                    dialogBox();
                    Log.d("log",respObj.getJSONObject("result").getString("message"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogoutActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

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

    }

    private void dialogBox(){
        logoutDialogboxBinding = logoutDialogboxBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(LogoutActivity.this);
        dialog.setContentView(logoutDialogboxBinding.getRoot());
        dialog.setCancelable(false);

        logoutDialogboxBinding.noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(LogoutActivity.this, "Dialogbox closed", Toast.LENGTH_SHORT).show();
            }
        });
        logoutDialogboxBinding.yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences preferences =getSharedPreferences("check",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                SharedPreferences preferencess =getSharedPreferences("check",MODE_PRIVATE);
                SharedPreferences.Editor editorr = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent=new Intent(LogoutActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

                dialog.dismiss();
            }
        });
        dialog.show();

    }


}