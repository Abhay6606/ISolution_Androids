package com.example.isolution.Activities.CategoriesCardActivities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.isolution.Adapter.CallingDetailAdapter;
import com.example.isolution.Model.CallDataGetterSetter;
import com.example.isolution.Model.CallLogsModelGetter;
import com.example.isolution.Model.CallingDetailsGetterSetter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityCallingDetailsBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CallingDetailsActivity extends AppCompatActivity {
    ActivityCallingDetailsBinding callingDetailsBinding;
    ArrayList<CallLogsModelGetter> arrayList = new ArrayList<>();

    ArrayList<CallDataGetterSetter> callDataarraylist=new ArrayList<>();
    CallingDetailAdapter adapter;
    String startDateString="",endDateString="",call_status="";
    JSONArray jsonArray=new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callingDetailsBinding = ActivityCallingDetailsBinding.inflate(getLayoutInflater());
        setContentView(callingDetailsBinding.getRoot());
        Bundle extras = getIntent().getExtras();

        //Called from Class1 show "class 1 string"
        //Called from Class2 show "class 2 string"
        call_status=(extras.getString("call_status"));

        askPermission();

        Toast.makeText(this, "Select Date Range", Toast.LENGTH_SHORT).show();


        // Adapter for RecyclerView (Access Contect logs)


        //===========================================================================================

        // Click Listeners
        callingDetailsBinding.startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog();

            }
        });
        callingDetailsBinding.endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog();

            }
        });


    }


    // Code (Method).For DateRangePicker
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

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                 startDateString = sdf.format(new Date(startDate));
                 endDateString = sdf.format(new Date(endDate));

//               selectedDate.setText(selectedDateRange);
                callingDetailsBinding.startDate.setText(startDateString);
                callingDetailsBinding.endDate.setText(endDateString);

                apiRequestt();
            }

        });
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    // Code for Ask Permission for Call Logs
    private void askPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1000);
        } else {

            List<CallLogsModelGetter> details = getCallDetails();
            arrayList = (ArrayList<CallLogsModelGetter>) details;

            Log.d("DETAILS", details.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                List<CallLogsModelGetter> details = getCallDetails();
                arrayList = (ArrayList<CallLogsModelGetter>) details;
                Log.d("DETAILS", details.toString());
            }
        }
    }

    private List<CallLogsModelGetter> getCallDetails() {

        ArrayList<CallLogsModelGetter> logListArrayList = new ArrayList<>();



        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
//        sb.append("Call Details :");

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


//            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
//                    + dir + " \nCall Date:--- " + callDayTime
//                    + " \nCall duration in sec :--- " + callDuration);
//            sb.append("\n----------------------------------");
        }

        managedCursor.close();

//        return sb.toString();
        return logListArrayList;
    }

    private void apiRequest(Context context) {


        SharedPreferences preferences = context.getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");

        String url = "https://callcrm.techfreelancepro.com/api/callDetails/user";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject respObj = new JSONObject(response);

                    Toast.makeText(context, respObj.getString("message"), Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
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

    private void apiRequestt() {

        SharedPreferences preferences = getSharedPreferences("loginData", MODE_PRIVATE);
        String userId = preferences.getString("user_id", "null");
        String token = preferences.getString("token", "null");


        String url = "https://callcrm.techfreelancepro.com/api/callDetails/list?date_start="+startDateString+"&date_end="+endDateString+"&call_status="+call_status+"&call_type=all";
        RequestQueue queue = Volley.newRequestQueue(CallingDetailsActivity.this);
        Log.d("nk", "qye");
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    callDataarraylist.clear();
                    JSONObject respObj = new JSONObject(response);

                    JSONObject result = respObj.getJSONObject("result");
                    JSONObject bannerData=result.getJSONObject("bannerData");
                    JSONObject  masterData=result.getJSONObject("masterData");
                    JSONArray callData=result.getJSONArray("callData");

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

                        callDataarraylist.add(pack);

                    }
                    callingDetailsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(CallingDetailsActivity.this));
                    adapter = new CallingDetailAdapter(CallingDetailsActivity.this, callDataarraylist);
                    callingDetailsBinding.recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CallingDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

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

