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
    String android="Android";
    String appVersion="1";


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

                tokenGenerate(loginBinding.userName.getText().toString(),loginBinding.password.getText().toString());
//                Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
//                finish();

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
    private void tokenGenerate(String userName,String password){
        String url="https://callcrm.techfreelancepro.com/api/tokenGenerate";
        RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loginBinding.userName.setText("");
                loginBinding.password.setText("");
                try {

                    JSONObject respObj = new JSONObject(response);

                    String userName = respObj.getString("user_name");
                    String password = respObj.getString("password");

                    SharedPreferences preferences=getSharedPreferences("loginData",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("user_name",loginBinding.userName.getText().toString());
//                    editor.putString("password",loginBinding.password.getText().toString());
//                    editor.putString("deviceToken",deviceToken);
//                    editor.putString("appVersion",appVersion);
//                    editor.putString("deviceType",android);
//
//                    editor.apply();
                    SessionManager.savePreference(preferences,"user_name",loginBinding.userName.getText().toString());
                    SessionManager.savePreference(preferences,"password",loginBinding.password.getText().toString());
                    SessionManager.savePreference(preferences,"deviceToken",deviceToken);
                    SessionManager.savePreference(preferences,"appVersion",appVersion);
                    SessionManager.savePreference(preferences,"deviceType",android);

                    // on below line we are setting this string s to our text view.
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                params.put("user_name", userName);
                params.put("password",password);
                params.put("device_token",deviceToken);
                params.put("device_type",android);
                params.put("app_version",appVersion);

                return params;
            }
        };
        queue.add(request);

    }

}