package com.example.isolution.Activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Activities.DrawerActivities.HomeActivity;
import com.example.isolution.databinding.ActivityLoginBinding;
import com.example.isolution.util.SessionManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding loginBinding;
    String deviceToken;
    String android = "Android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        deviceToken();

        loginBinding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();


            }
        });
        loginBinding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tokenGenerate(loginBinding.userName.getText().toString(), loginBinding.password.getText().toString());
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
        String url = "https://callcrm.techfreelancepro.com/api/tokenGenerate";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loginBinding.userName.setText("");
                loginBinding.password.setText("");
                Log.d("resp",response.toString());
                try {

                    JSONObject respObj = new JSONObject(response);
                    JSONObject result = respObj.getJSONObject("result");
                    JSONObject userData = result.getJSONObject("userData");



                    String message = respObj.getString("message");

                    String user_id = result.getString("user_id");
                    String token = result.getString("token");

                    String selfieImage = userData.getString("selfieImage");
                    String name = userData.getString("name");
                    String mobileNumber = userData.getString("mobileNumber");
                    String userName = userData.getString("userName");
                    String email = userData.getString("email");
                    String roleName = userData.getString("roleName");
                    String isBlocked = userData.getString("isBlocked");


                    //Shared Preference Code

                    SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);

                    SessionManager.savePreference(preferences,"message",message);
                    SessionManager.savePreference(preferences,"user_id",user_id);
                    SessionManager.savePreference(preferences,"token",token);
                    SessionManager.savePreference(preferences,"selfieImage",selfieImage);
                    SessionManager.savePreference(preferences,"name",name);
                    SessionManager.savePreference(preferences,"mobileNumber",mobileNumber);
                    SessionManager.savePreference(preferences,"userName",userName);
                    SessionManager.savePreference(preferences,"email",email);
                    SessionManager.savePreference(preferences,"roleName",roleName);
                    SessionManager.savePreference(preferences,"isBlocked",isBlocked);

                    Log.d("name",getSharedPreferences("loginData",MODE_PRIVATE).getString("name","null"));

                    if(respObj.getBoolean("status")){
                        startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this , "Invalid Credential" , Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("user_name", userName.toString().trim());
                params.put("password", password.toString().trim());
                params.put("device_token", deviceToken);
                params.put("device_type", android);

                return params;
            }
        };
        queue.add(request);

    }

}