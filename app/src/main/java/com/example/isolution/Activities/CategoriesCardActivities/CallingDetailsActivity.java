package com.example.isolution.Activities.CategoriesCardActivities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.isolution.Adapter.CallingDetailAdapter;
import com.example.isolution.Model.CallLogsModelGetter;
import com.example.isolution.Model.CallingDetailsGetterSetter;
import com.example.isolution.R;
import com.example.isolution.databinding.ActivityCallingDetailsBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CallingDetailsActivity extends AppCompatActivity {
    ActivityCallingDetailsBinding callingDetailsBinding;
    ArrayList<CallLogsModelGetter> arrayList = new ArrayList<>();
    CallingDetailAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callingDetailsBinding = ActivityCallingDetailsBinding.inflate(getLayoutInflater());
        setContentView(callingDetailsBinding.getRoot());
        askPermission();

//        CallingDetailsGetterSetter pack = new CallingDetailsGetterSetter("https://instagram.fdel27-5.fna.fbcdn.net/v/t51.2885-19/269382591_431678785170231_3739845402388046538_n.jpg?stp=dst-jpg_s150x150&efg=e30&_nc_ht=instagram.fdel27-5.fna.fbcdn.net&_nc_cat=103&_nc_ohc=2f-l3xXFQsQAX-1yEGN&edm=ABmJApABAAAA&ccb=7-5&oh=00_AfDyr8nPqNe05uvvv2TCUZ3wk-ob5pZKi3byudyAQ2txtA&oe=65B80EAD&_nc_sid=b41fef", "Abhay Bhadauria", "6398960435", "23m12s");
//        arrayList.add(pack);

        callingDetailsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new CallingDetailAdapter(this, arrayList);
        callingDetailsBinding.recyclerView.setAdapter(adapter);
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

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String startDateString = sdf.format(new Date(startDate));
                String endDateString = sdf.format(new Date(endDate));

                String selectedDateRange = startDateString + " - " + endDateString;

//               selectedDate.setText(selectedDateRange);
                callingDetailsBinding.startDate.setText(startDateString);
                callingDetailsBinding.endDate.setText(endDateString);

            }
        });
        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    private void askPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1000);
        }else {

            List<CallLogsModelGetter> details = getCallDetails();
            arrayList = (ArrayList<CallLogsModelGetter>) details;

            Log.d("DETAILS", details.toString());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1000){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                List<CallLogsModelGetter>  details = getCallDetails();
                arrayList = (ArrayList<CallLogsModelGetter>) details;
                Log.d("DETAILS",details.toString());
            }
        }
    }

    private List<CallLogsModelGetter> getCallDetails() {

        ArrayList<CallLogsModelGetter> logListArrayList = new ArrayList<>();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int name=managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
//        sb.append("Call Details :");

        while (managedCursor.moveToNext()) {
            String callerName=managedCursor.getString(name);
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

            CallLogsModelGetter pack = new CallLogsModelGetter(callerName,phNumber,dir,callDate,callDayTime.toString(),callDuration);
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
}
