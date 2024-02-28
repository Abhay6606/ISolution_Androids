package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Adapter.LeadRecyclerAdapter;
import com.example.isolution.Model.FetchAllLeadsGetterSetter;
import com.example.isolution.Model.GetterSetter;
import com.example.isolution.databinding.ActivityContactLeadBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeadListingActivity extends AppCompatActivity {
    ActivityContactLeadBinding contactLeadBinding;
    ArrayList<FetchAllLeadsGetterSetter> fetchLeadArrayList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactLeadBinding = ActivityContactLeadBinding.inflate(getLayoutInflater());
        setContentView(contactLeadBinding.getRoot());

        fectchCallLogs();


    }

    // to Fetch All Leads
    private JSONObject fectchCallLogs() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");


        String url = "https://callcrm.techfreelancepro.com/api/lead/list?date_start=2024-02-27&date_end=2024-02-29&user_id_fetch=all";
        RequestQueue queue = Volley.newRequestQueue(LeadListingActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject respObj = new JSONObject(response);
                    JSONArray jsonArray= respObj.getJSONArray("result");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        FetchAllLeadsGetterSetter pack = new FetchAllLeadsGetterSetter();

                        pack.setName(object.getString("name"));
                        pack.setDisp_pri_code(object.getString("disp_pri_code_name"));
                        pack.setCategory_code(object.getString("category_code_name"));
                        pack.setSource(object.getString("source"));
                        pack.setMobile_number(object.getString("mobile_number"));
                        pack.setEmail_id(object.getString("email_id"));

                        fetchLeadArrayList.add(pack);


                    }

                    contactLeadBinding.recyclerViewdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    LeadRecyclerAdapter adapter = new LeadRecyclerAdapter(getApplicationContext(), fetchLeadArrayList);
                    contactLeadBinding.recyclerViewdetails.setAdapter(adapter);

                } catch (Exception e) {
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

    //  Category Wise

    private JSONObject getApiwRequest() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");


        String url = "https://callcrm.techfreelancepro.com/api/lead/list?date_start=2024-02-27&date_end=2024-02-27&user_id_fetch=all&disp_pri_code=hot";
        RequestQueue queue = Volley.newRequestQueue(LeadListingActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject respObj = new JSONObject(response);


                    Log.d("Check", respObj.toString());


                } catch (Exception e) {
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

    // For Follow Update

    private JSONObject getApqiRequest() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");


        String url = "https://callcrm.techfreelancepro.com/api/lead/list?user_id_fetch=all&follow_up=2024-02-29";
        RequestQueue queue = Volley.newRequestQueue(LeadListingActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject respObj = new JSONObject(response);


                    Log.d("Check", respObj.toString());


                } catch (Exception e) {
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


}