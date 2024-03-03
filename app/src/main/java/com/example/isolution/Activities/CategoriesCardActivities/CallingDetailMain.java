package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Activities.DrawerActivities.HomeActivity;
import com.example.isolution.Adapter.CallingDetailAdapter;
import com.example.isolution.Model.CallDataGetterSetter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityCallingDetailMainBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CallingDetailMain extends AppCompatActivity {

    ActivityCallingDetailMainBinding callingDetailMainBinding;
    String startDateString="",endDateString="";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        callingDetailMainBinding=ActivityCallingDetailMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(callingDetailMainBinding.getRoot());

        mProgressDialog = new ProgressDialog(CallingDetailMain.this);
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Logging in...");

        callingDetailMainBinding.today.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                currentdate();
                startDateString=previousdate(0);

                apiRequestt();

            }
        });
        callingDetailMainBinding.lastSeven.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                currentdate();
                startDateString=previousdate(7);
                apiRequestt();


            }
        });
        callingDetailMainBinding.lastThirty.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                currentdate();
                startDateString=previousdate(30);
                apiRequestt();


            }
        });
        callingDetailMainBinding.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog();
            }
        });




        callingDetailMainBinding.incamingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                intent.putExtra("call_status","incoming");

                 startActivity(intent);
            }
        });
        callingDetailMainBinding.outgoingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                intent.putExtra("call_status","outgoing");
                startActivity(intent);
            }
        });
        callingDetailMainBinding.missedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this,CallingDetailsActivity.class);
                intent.putExtra("call_status","missed");
                startActivity(intent);
            }
        });





        callingDetailMainBinding.callingTeamDetailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallingDetailMain.this, CallingTeamDetails.class);
                startActivity(intent);
            }
        });



    }



    private void apiRequestt() {

        mProgressDialog.show();

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");


        String url = "https://callcrm.techfreelancepro.com/api/callDetails/list?date_start="+startDateString+"&date_end="+endDateString;
        RequestQueue queue = Volley.newRequestQueue(CallingDetailMain.this);
        Log.d("nk", "qye");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject respObj = new JSONObject(response);

                    JSONObject result = respObj.getJSONObject("result");
                    JSONObject bannerData=result.getJSONObject("bannerData");
                    JSONObject  masterData=result.getJSONObject("masterData");
                    JSONArray callData=result.getJSONArray("callData");

                    callingDetailMainBinding.incomingPercent.setText(bannerData.getString("incomingPer"));
                    callingDetailMainBinding.outGoingPercent.setText(bannerData.getString("outgoingPer"));
                    callingDetailMainBinding.missedPercent.setText(bannerData.getString("missedPer"));

                    // Iterate CallData JSONArray

                    for (int i=0;i<callData.length();i++){
                        JSONObject object=callData.getJSONObject(i);
                        CallDataGetterSetter pack=new CallDataGetterSetter();
                        pack.setCall_end_time(object.getString("call_end_time"));
                        pack.setCall_start_time(object.getString("call_start_time"));
                        pack.setCall_status(object.getString("call_status"));
                        pack.setCall_status_name(object.getString("call_status_name"));
                        pack.setCreated_at(object.getString("created_at"));
                        pack.setHistory_id(object.getString("history_id"));
                        pack.setId(object.getString("id"));
                        pack.setLead_id(object.getString("lead_id"));
                        pack.setLead_name(object.getString("lead_name"));
                        pack.setMobile_number(object.getString("mobile_number"));
                        pack.setRinging(object.getString("ringing"));
                        pack.setTalktime(object.getString("talktime"));
                        pack.setTotal_time(object.getString("total_time"));
                        pack.setType(object.getString("type"));
                        pack.setType_name(object.getString("type_name"));
                        pack.setUpdated_at(object.getString("updated_at"));
                        pack.setUser_id(object.getString("user_id"));

//                        callDataarraylist.add(pack);

                    }
                    mProgressDialog.hide();
//                    callingDetailsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapter = new CallingDetailAdapter(this, callDataarraylist);
//                    callingDetailsBinding.recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                    mProgressDialog.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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


    }
//    public String getdate(){
//        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        String formattedDate = df.format(c);
//
//        return formattedDate;
//    }

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

        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
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

                apiRequestt();
            }

        });
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    @Override
    protected void onResume() {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        android.icu.text.SimpleDateFormat df = new android.icu.text.SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        endDateString = df.format(c);
        startDateString = df.format(c);

        apiRequestt();

        super.onResume();



    }
}