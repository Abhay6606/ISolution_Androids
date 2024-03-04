package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.example.isolution.Activities.LoginActivity;
import com.example.isolution.Adapter.LeadRecyclerAdapter;
import com.example.isolution.Model.FetchAllLeadsGetterSetter;
import com.example.isolution.Model.GetterSetter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityContactLeadBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LeadListingActivity extends AppCompatActivity {
    ActivityContactLeadBinding contactLeadBinding;
    ArrayList<FetchAllLeadsGetterSetter> fetchLeadArrayList = new ArrayList<>();

    ProgressDialog mProgressDialog;
    String endDateString = "", startDateString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactLeadBinding = ActivityContactLeadBinding.inflate(getLayoutInflater());
        setContentView(contactLeadBinding.getRoot());

        mProgressDialog = new ProgressDialog(LeadListingActivity.this);
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Logging in...");

        contactLeadBinding.today.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                contactLeadBinding.today.setBackgroundResource(R.drawable.selected_tab);
                contactLeadBinding.today.setTextColor(Color.parseColor("#ffffff"));

                contactLeadBinding.lastSeven.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.lastSeven.setTextColor(Color.parseColor("#e53538"));

                contactLeadBinding.lastThirty.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.lastThirty.setTextColor(Color.parseColor("#e53538"));


                contactLeadBinding.select.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.select.setTextColor(Color.parseColor("#e53538"));

                startDateString = previousdate(0);

                currentdate();
                apiRequestt();

            }
        });
        contactLeadBinding.lastSeven.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                contactLeadBinding.lastSeven.setBackgroundResource(R.drawable.selected_tab);
                contactLeadBinding.lastSeven.setTextColor(Color.parseColor("#ffffff"));

                contactLeadBinding.today.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.today.setTextColor(Color.parseColor("#e53538"));

                contactLeadBinding.lastThirty.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.lastThirty.setTextColor(Color.parseColor("#e53538"));


                contactLeadBinding.select.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.select.setTextColor(Color.parseColor("#e53538"));


                currentdate();
                startDateString = previousdate(7);
                apiRequestt();


            }
        });
        contactLeadBinding.lastThirty.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                contactLeadBinding.lastThirty.setBackgroundResource(R.drawable.selected_tab);
                contactLeadBinding.lastThirty.setTextColor(Color.parseColor("#ffffff"));

                contactLeadBinding.lastSeven.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.lastSeven.setTextColor(Color.parseColor("#e53538"));

                contactLeadBinding.today.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.today.setTextColor(Color.parseColor("#e53538"));


                contactLeadBinding.select.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.select.setTextColor(Color.parseColor("#e53538"));


                currentdate();
                startDateString = previousdate(30);
                apiRequestt();


            }
        });
        contactLeadBinding.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                datePickerDialog();
            }
        });

    }

    // to Fetch All Leads
    private void apiRequestt() {
        mProgressDialog.show();
        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");
        String client_id = preferences.getString("client_id", "null");

        String url = "https://callcrm.techfreelancepro.com/api/lead/list?date_start=" + startDateString + "&date_end=" + endDateString + "&user_id_fetch=all";
        RequestQueue queue = Volley.newRequestQueue(LeadListingActivity.this);
        Log.d("nk", "qye");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    fetchLeadArrayList.clear();
                    JSONObject respObj = new JSONObject(response);

                    if (respObj.getString("message").equalsIgnoreCase("Data Error")) {
                        Toast.makeText(LeadListingActivity.this, "No Lead Found", Toast.LENGTH_SHORT).show();
                    } else {

                        JSONObject result = respObj.getJSONObject("result");
                        JSONArray jsonArray = result.getJSONArray("leadData");

                        JSONObject bannerObj = result.getJSONObject("bannerData");
                        String outGoing = bannerObj.getString("outgoing");
                        String incoming = bannerObj.getString("incoming");
                        String missed = bannerObj.getString("missed");
                        String totalCalls = bannerObj.getString("totalCalls");
                        String leadTotal = bannerObj.getString("leadTotal");
                        String duration = bannerObj.getString("average_duration");

                        try {
                            contactLeadBinding.outgoing.setText(outGoing);
                            contactLeadBinding.incoming.setText(incoming);
                            contactLeadBinding.missed.setText(missed);
                        }catch (Exception e)
                        {
                            contactLeadBinding.outgoing.setText("0");
                            contactLeadBinding.incoming.setText("0");
                            contactLeadBinding.missed.setText("0");
                        }
                        contactLeadBinding.totalCalls.setText(totalCalls);
                        contactLeadBinding.totalLeads.setText(leadTotal);
                        contactLeadBinding.avgTalktime.setText(duration);


                        Log.d("cp", bannerObj.toString());


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            FetchAllLeadsGetterSetter pack = new FetchAllLeadsGetterSetter();

                            pack.setId(object.getString("id"));
                            pack.setUser_id(object.getString("user_id"));
                            pack.setName(object.getString("name"));
                            pack.setMobile_number(object.getString("mobile_number"));
                            pack.setEmail_id(object.getString("email_id"));
                            pack.setDate_of_birth(object.getString("date_of_birth"));
                            pack.setAge(object.getString("age"));
                            pack.setWhatsapp_no(object.getString("whatsapp_no"));
                            pack.setAlt_mobile(object.getString("alt_mobile"));
                            pack.setSelfie_filename(object.getString("selfie_filename"));
                            pack.setSource(object.getString("source"));
                            pack.setCity(object.getString("city"));
                            pack.setState(object.getString("state"));
                            pack.setPincode(object.getString("pincode"));
                            pack.setAllocated_date(object.getString("allocated_date"));
                            pack.setFollow_up_date(object.getString("follow_up_date"));
                            pack.setLast_reallocated_date(object.getString("last_reallocated_date"));
                            pack.setDisp_pri_code(object.getString("disp_pri_code"));
                            pack.setDisp_sec_code(object.getString("disp_sec_code"));
                            pack.setDisp_lead_type(object.getString("disp_lead_type"));
                            pack.setDisp_attempt_count(object.getString("disp_attempt_count"));
                            pack.setComments(object.getString("comments"));
                            pack.setCategory_code(object.getString("category_code"));
                            pack.setSub_category_code(object.getString("sub_category_code"));
                            pack.setStatus(object.getString("status"));
                            pack.setIs_visible_lead(object.getString("is_visible_lead"));
                            pack.setLast_contacted_at(object.getString("last_contacted_at"));
                            pack.setCreated_at(object.getString("created_at"));
                            pack.setUpdated_at(object.getString("updated_at"));
                            pack.setSource_name(object.getString("source_name"));
                            pack.setCategory_code_name(object.getString("category_code_name"));
                            pack.setDisp_pri_code_name(object.getString("disp_pri_code_name"));
                            pack.setUser_id_name(object.getString("user_id_name"));

                            Log.d("ys", "success");

                            fetchLeadArrayList.add(pack);


                        }

                        contactLeadBinding.recyclerViewdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        LeadRecyclerAdapter adapter = new LeadRecyclerAdapter(getApplicationContext(), fetchLeadArrayList);
                        contactLeadBinding.recyclerViewdetails.setAdapter(adapter);
                        mProgressDialog.hide();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LeadListingActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressDialog.hide();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-user-id", userId);
                params.put("x-client-id", client_id);
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
        queue.add(request);


    }

//    private void apiRequesttoday() {
//        mProgressDialog.show();
//        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
//        String userId = preferences.getString("user_id", "null");
//        String token = preferences.getString("token", "null");
//
//
//        String url = "https://callcrm.techfreelancepro.com/api/lead/list?&date_end=" + endDateString + "&user_id_fetch=all";
//        RequestQueue queue = Volley.newRequestQueue(LeadListingActivity.this);
//
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    fetchLeadArrayList.clear();
//                    JSONObject respObj = new JSONObject(response);
//
//                    if (respObj.getString("message").equalsIgnoreCase("Data Error")) {
//                        Toast.makeText(LeadListingActivity.this, "No Lead Found", Toast.LENGTH_SHORT).show();
//                    } else {
//                        JSONObject result = respObj.getJSONObject("result");
//                        JSONArray jsonArray = result.getJSONArray("leadData");
//
//                        JSONObject bannerObj = result.getJSONObject("bannerData");
//                        String outGoing = bannerObj.getString("outgoing");
//                        String incoming = bannerObj.getString("incoming");
//                        String missed = bannerObj.getString("missed");
//                        String totalCalls = bannerObj.getString("totalCalls");
//                        String leadTotal = bannerObj.getString("leadTotal");
//                        String duration = bannerObj.getString("average_duration");
//
//                        contactLeadBinding.outgoing.setText(outGoing);
//                        contactLeadBinding.incoming.setText(incoming);
//                        contactLeadBinding.missed.setText(missed);
//                        contactLeadBinding.totalCalls.setText(totalCalls);
//                        contactLeadBinding.totalLeads.setText(leadTotal);
//                        contactLeadBinding.avgTalktime.setText(duration);
//
//
//                        Log.d("cp", bannerObj.toString());
//
//
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject object = jsonArray.getJSONObject(i);
//                            FetchAllLeadsGetterSetter pack = new FetchAllLeadsGetterSetter();
//
//                            pack.setId(object.getString("id"));
//                            pack.setUser_id(object.getString("user_id"));
//                            pack.setName(object.getString("name"));
//                            pack.setMobile_number(object.getString("mobile_number"));
//                            pack.setEmail_id(object.getString("email_id"));
//                            pack.setDate_of_birth(object.getString("date_of_birth"));
//                            pack.setAge(object.getString("age"));
//                            pack.setWhatsapp_no(object.getString("whatsapp_no"));
//                            pack.setAlt_mobile(object.getString("alt_mobile"));
//                            pack.setSelfie_filename(object.getString("selfie_filename"));
//                            pack.setSource(object.getString("source"));
//                            pack.setCity(object.getString("city"));
//                            pack.setState(object.getString("state"));
//                            pack.setPincode(object.getString("pincode"));
//                            pack.setAllocated_date(object.getString("allocated_date"));
//                            pack.setFollow_up_date(object.getString("follow_up_date"));
//                            pack.setLast_reallocated_date(object.getString("last_reallocated_date"));
//                            pack.setDisp_pri_code(object.getString("disp_pri_code"));
//                            pack.setDisp_sec_code(object.getString("disp_sec_code"));
//                            pack.setDisp_lead_type(object.getString("disp_lead_type"));
//                            pack.setDisp_attempt_count(object.getString("disp_attempt_count"));
//                            pack.setComments(object.getString("comments"));
//                            pack.setCategory_code(object.getString("category_code"));
//                            pack.setSub_category_code(object.getString("sub_category_code"));
//                            pack.setStatus(object.getString("status"));
//                            pack.setIs_visible_lead(object.getString("is_visible_lead"));
//                            pack.setLast_contacted_at(object.getString("last_contacted_at"));
//                            pack.setCreated_at(object.getString("created_at"));
//                            pack.setUpdated_at(object.getString("updated_at"));
//                            pack.setSource_name(object.getString("source_name"));
//                            pack.setCategory_code_name(object.getString("category_code_name"));
//                            pack.setDisp_pri_code_name(object.getString("disp_pri_code_name"));
//                            pack.setUser_id_name(object.getString("user_id_name"));
//
//                            Log.d("ys", "success");
//
//                            fetchLeadArrayList.add(pack);
//
//
//                        }
//
//                        contactLeadBinding.recyclerViewdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                        LeadRecyclerAdapter adapter = new LeadRecyclerAdapter(getApplicationContext(), fetchLeadArrayList);
//                        contactLeadBinding.recyclerViewdetails.setAdapter(adapter);
//                        mProgressDialog.hide();
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LeadListingActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                mProgressDialog.hide();
//            }
//        }) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("x-user-id", userId);
//                params.put("Authorization", "Bearer " + token);
//                return params;
//            }
//        };
//        queue.add(request);
//
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String previousdate(int num) {
        final LocalDate date = LocalDate.now();
        final LocalDate dateMinus7Days = date.minusDays(num);

        final String formattedDate = dateMinus7Days.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(formattedDate);

        return formattedDate;
    }

    public void currentdate() {
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

                contactLeadBinding.select.setBackgroundResource(R.drawable.selected_tab);
                contactLeadBinding.select.setTextColor(Color.parseColor("#ffffff"));

                contactLeadBinding.lastSeven.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.lastSeven.setTextColor(Color.parseColor("#e53538"));

                contactLeadBinding.lastThirty.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.lastThirty.setTextColor(Color.parseColor("#e53538"));


                contactLeadBinding.today.setBackgroundResource(R.drawable.orange_border_rectangle);
                contactLeadBinding.today.setTextColor(Color.parseColor("#e53538"));


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

        contactLeadBinding.today.setBackgroundResource(R.drawable.selected_tab);
        contactLeadBinding.today.setTextColor(Color.parseColor("#ffffff"));

        apiRequestt();

        super.onResume();


    }

}