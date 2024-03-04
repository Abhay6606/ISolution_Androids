package com.example.isolution.Activities.CategoriesCardActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Activities.DrawerActivities.HomeActivity;
import com.example.isolution.Adapter.HomeCalenderAdapter;
import com.example.isolution.Model.HomeCalenderRsltGterStter;
import com.example.isolution.Model.HomePercentageArrayGtterStter;
import com.example.isolution.Model.LeadCategoryGetterSetter;
import com.example.isolution.Model.LeadSourceCodeGetterSetter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityContectLeadFormBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContectLeadForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityContectLeadFormBinding leadFormBinding;
    ArrayList<LeadCategoryGetterSetter> categoryArraylst = new ArrayList<>();
    ArrayList<LeadSourceCodeGetterSetter> sourceCodeyArraylst = new ArrayList<>();
    String source;
    String categroy;
    ArrayList<String> strcategoryArraylst = new ArrayList<>();
    ArrayList<String> strsourceCodeyArraylst = new ArrayList<>();
    ProgressDialog mProgressDialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leadFormBinding = ActivityContectLeadFormBinding.inflate(getLayoutInflater());
        setContentView(leadFormBinding.getRoot());

        getApiRequest();

        leadFormBinding.leadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(leadFormBinding.firstName.getText()) || TextUtils.isEmpty(leadFormBinding.lastName.getText()) || TextUtils.isEmpty(leadFormBinding.email.getText()) || TextUtils.isEmpty(leadFormBinding.mobileNumber.getText()) || TextUtils.isEmpty(leadFormBinding.city.getText())) {

                    Toast.makeText(ContectLeadForm.this, "You missing some credentials ", Toast.LENGTH_SHORT).show();

                } else {


                    String mobileNumber = leadFormBinding.mobileNumber.getText().toString();
                    String email = leadFormBinding.email.getText().toString();
                    String firstName = leadFormBinding.firstName.getText().toString();
                    String lastName = leadFormBinding.lastName.getText().toString();
                    String city = leadFormBinding.city.getText().toString();

                    apiRequest(mobileNumber, email, "1", source, firstName + " " + lastName,city, ContectLeadForm.this);


                }


            }
        });
        leadFormBinding.referenceSpinner.setOnItemSelectedListener(ContectLeadForm.this);


    }

    private void apiRequest(String mobile_number, String email_id, String category_code, String source, String name,String city, Context context) {


        SharedPreferences preferences = context.getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");
        String client_id = preferences.getString("client_id", "null");

        mProgressDialog = new ProgressDialog(ContectLeadForm.this);
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.show();


        String url = "https://callcrm.techfreelancepro.com/api/lead/create";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject respObj = new JSONObject(response);

                    Toast.makeText(context, respObj.getString("message"), Toast.LENGTH_SHORT).show();

                    finish();
                    mProgressDialog.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                    mProgressDialog.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Credentials Are Incorrect", Toast.LENGTH_SHORT).show();
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

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("lead_user_id", userId);
                params.put("mobile_number", mobile_number);
                params.put("email_id", email_id);
                params.put("category_code", category_code);
                params.put("source", source);
                params.put("name", name);
                params.put("city", city);


                return params;
            }


        };
        queue.add(request);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spin = (Spinner) parent;
        Spinner spin2 = (Spinner) parent;
        if (spin.getId() == R.id.referenceSpinner) {
            Toast.makeText(this, "Your choose :" + sourceCodeyArraylst.get(position).getId(), Toast.LENGTH_SHORT).show();
            source = sourceCodeyArraylst.get(position).getCode();
            Log.d("str", source);
        }
        if (spin2.getId() == R.id.categorySpinner) {
            categroy = categoryArraylst.get(position).getSub_category_present();
            Toast.makeText(this, "Your choose :" + categoryArraylst.get(position).getId(), Toast.LENGTH_SHORT).show();
            Log.d("str", categroy);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private JSONObject getApiRequest() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");
        String client_id = preferences.getString("client_id", "null");

        mProgressDialog = new ProgressDialog(ContectLeadForm.this);
        mProgressDialog.setTitle("Please Wait..");
        mProgressDialog.setMessage("Logging in...");
        mProgressDialog.show();


        String url = "https://callcrm.techfreelancepro.com/api/master/data";
        RequestQueue queue = Volley.newRequestQueue(ContectLeadForm.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("check", response.toString());
                try {
                    JSONObject respObj = new JSONObject(response);
                    JSONObject result = respObj.getJSONObject("result");
                    JSONArray categoryDetails = result.getJSONArray("categoryDetails");
                    JSONArray soureCodeDetails = result.getJSONArray("soureCodeDetails");

                    categoryArraylst.clear();
                    sourceCodeyArraylst.clear();
                    strcategoryArraylst.clear();
                    strsourceCodeyArraylst.clear();

                    //==============================================================================
                    // Iterating Category jsonResponce

                    for (int i = 0; i < categoryDetails.length(); i++) {
                        JSONObject e = categoryDetails.getJSONObject(i);
                        LeadCategoryGetterSetter pack = new LeadCategoryGetterSetter();

                        pack.setName(e.getString("name"));
                        pack.setId(e.getString("id"));
                        pack.setStatus(e.getString("status"));
                        pack.setSub_category_present(e.getString("sub_category_present"));
                        pack.setCreated_at(e.getString("created_at"));
                        pack.setUpdated_at(e.getString("updated_at"));
                        categoryArraylst.add(pack);
                        strcategoryArraylst.add(e.getString("name"));


                    }

                    //================================================================================================
                    // Iterating SourceCode jsonResponce

                    for (int i = 0; i < soureCodeDetails.length(); i++) {
                        JSONObject c = soureCodeDetails.getJSONObject(i);
                        LeadSourceCodeGetterSetter packk = new LeadSourceCodeGetterSetter();
                        packk.setId(c.getString("id"));
                        packk.setName(c.getString("name"));
                        packk.setCode(c.getString("code"));
                        packk.setIs_active(c.getString("is_active"));
                        packk.setCreated_at(c.getString("created_at"));

                        sourceCodeyArraylst.add(packk);
                        strsourceCodeyArraylst.add(c.getString("name"));
                        Log.d("itr", packk.toString());

                    }

                    ArrayAdapter ResourceAdapter = new ArrayAdapter(ContectLeadForm.this, android.R.layout.simple_spinner_item, strsourceCodeyArraylst);
                    ResourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    leadFormBinding.referenceSpinner.setAdapter(ResourceAdapter);
                    leadFormBinding.referenceSpinner.setOnItemSelectedListener(ContectLeadForm.this);


                    ArrayAdapter CategoryAdapter = new ArrayAdapter(ContectLeadForm.this, android.R.layout.simple_spinner_item, strcategoryArraylst);
                    ResourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    leadFormBinding.categorySpinner.setAdapter(CategoryAdapter);
                    leadFormBinding.categorySpinner.setOnItemSelectedListener(ContectLeadForm.this);


                    mProgressDialog.hide();


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
                params.put("x-client-id", client_id);
                params.put("Authorization", "Bearer " + token);
                return params;
            }
        };
        queue.add(request);

        return new JSONObject();

    }


}