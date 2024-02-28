package com.example.isolution.Activities;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Activities.DrawerActivities.HomeActivity;
import com.example.isolution.Adapter.HomeCalenderAdapter;
import com.example.isolution.Model.CallLogsModelGetter;
import com.example.isolution.databinding.ActivityLoginBinding;
import com.example.isolution.util.SessionManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding loginBinding;
    String deviceToken;
    String android = "Android";
    ProgressDialog mProgressDialog;

    JSONArray jsonArray=new JSONArray();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        deviceToken();



        loginBinding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog = new ProgressDialog(LoginActivity.this);
                mProgressDialog.setTitle("Please Wait..");
                mProgressDialog.setMessage("Logging in...");




                if (TextUtils.isEmpty(loginBinding.userName.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Enter the Username", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(loginBinding.password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Enter the Password ", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressDialog.show();
                    tokenGenerate(loginBinding.userName.getText().toString().trim(), loginBinding.password.getText().toString().trim());
                }


            }

        });


    }

    private void deviceToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String token) {
                if (!TextUtils.isEmpty(token)) {
                    Log.d(TAG, "retrieve token successful : " + token);
                    deviceToken = token;
                    Log.d("devToh",deviceToken);
                } else {
                    Log.w(TAG, "token should not be null...");
                    deviceToken = "NOT FOUND";
                }
            }
        }).addOnFailureListener(e -> {
            //handle e
        }).addOnCanceledListener(() -> {
            //handle cancel
        }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));

    }

    private void tokenGenerate(String userName, String password) {


        String url =   "https://callcrm.techfreelancepro.com/api/tokenGenerate";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject respObj = new JSONObject(response);


                    JSONObject result = respObj.getJSONObject("result");
                    JSONObject userData = result.getJSONObject("userData");


                    String user_id = result.getString("user_id");
                    String token = result.getString("token");
                    String email = userData.getString("email");
                    String isBlocked = userData.getString("isBlocked");
                    String mobileNumber = userData.getString("mobileNumber");
                    String name = userData.getString("name");
                    String roleName = userData.getString("roleName");
                    String selfieImage = userData.getString("selfieImage");
                    String userName = userData.getString("userName");


                    SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);


                    SessionManager.savePreference(preferences, "user_id", user_id);
                    SessionManager.savePreference(preferences, "token", token);
                    SessionManager.savePreference(preferences, "email", email);
                    SessionManager.savePreference(preferences, "mobileNumber", mobileNumber);
                    SessionManager.savePreference(preferences, "name", name);


                    if (respObj.getBoolean("status")) {

                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                        getCallDetails();

                    }

                } catch (JSONException e) {
                    mProgressDialog.hide();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
                loginBinding.userName.setText("");
                loginBinding.password.setText("");
            }
        }) {


            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("user_name", userName);
                params.put("password", password);
                params.put("device_token", deviceToken);
                params.put("device_type", android);

                return params;
            }


        };
        queue.add(request);

    }


    private List<CallLogsModelGetter> getCallDetails() {

        ArrayList<CallLogsModelGetter> logListArrayList = new ArrayList<>();



        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);


        while (managedCursor.moveToNext()) {
            String callerName = managedCursor.getString(name);
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);

            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            JSONObject jsonObject=new JSONObject();

            try {
                jsonObject.put("mobile_number",phNumber);
                jsonObject.put("call_start_time",callDate);
                jsonObject.put("call_end_time","");
                jsonObject.put("ringing","");
                jsonObject.put("calltype",dir);
                jsonObject.put("talktime",callDuration);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            jsonArray.put(jsonObject);

            CallLogsModelGetter pack = new CallLogsModelGetter(callerName, phNumber, dir, callDate, callDayTime.toString(), callDuration);
            logListArrayList.add(pack);


        }

        managedCursor.close();

        apiRequest();startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();

        return logListArrayList;
    }

    private void apiRequest() {


        SharedPreferences preferences = this.getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");

        String url = "https://callcrm.techfreelancepro.com/api/callDetails/user";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject respObj = new JSONObject(response);

                    Toast.makeText(LoginActivity.this, respObj.getString("message"), Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Credentials Are Incorrect", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-user-id", userId);
                params.put("Authorization", "Bearer " + token);
                return params;
            }

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("call_details", String.valueOf(jsonArray));


                return params;
            }


        };
        queue.add(request);

    }





}