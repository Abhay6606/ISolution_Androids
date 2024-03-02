package com.example.isolution.Activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.Calendar
import java.util.concurrent.TimeUnit

class Reciever : BroadcastReceiver() {

    companion object {
        var startTimeSec: Long = 0
        var type = "type"
        var ringingTime = 10
        var formatedRingingDate = "sdk"
        var formatedRecievedDate = "sdk"
        var formattedEndDate = "sdk"
        var endTimeSec: Long = 0
        var incomingCheck = false
        var formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
        var num = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

        if (num != null) {
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                val ringingTimn = Calendar.getInstance().timeInMillis
                val ringingTime = TimeUnit.SECONDS.convert(
                    ringingTimn,
                    TimeUnit.MILLISECONDS
                ) // getting time in sec

                var ringingDate = Calendar.getInstance().time
                Reciever.formatter.timeZone = TimeZone.getTimeZone("GMT+05:30")
                Reciever.formatedRingingDate = Reciever.formatter.format(ringingDate).toString()


                Toast.makeText(context, "Ringing", Toast.LENGTH_SHORT).show()
                Reciever.incomingCheck = true

            } else if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                val startTime = Calendar.getInstance().timeInMillis
                Reciever.startTimeSec = TimeUnit.SECONDS.convert(
                    startTime,
                    TimeUnit.MILLISECONDS
                )  //getting time in sec

                var ringingDatte = Calendar.getInstance().time
                Reciever.formatter.timeZone = TimeZone.getTimeZone("GMT+05:30")
                Reciever.formatedRecievedDate = Reciever.formatter.format(ringingDatte).toString()

                Toast.makeText(
                    context,
                    "Answered" + Reciever.startTimeSec.toString(),
                    Toast.LENGTH_SHORT
                ).show()

            } else if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                val endTime = Calendar.getInstance().timeInMillis
                Reciever.endTimeSec = TimeUnit.SECONDS.convert(endTime, TimeUnit.MILLISECONDS)

                var ringingDattte = Calendar.getInstance().time
                Reciever.formatter.timeZone = TimeZone.getTimeZone("GMT+05:30")
                Reciever.formattedEndDate = Reciever.formatter.format(ringingDattte).toString()

                Toast.makeText(
                    context,
                    "Ended" + Reciever.endTimeSec.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                val calculatedTime = Reciever.endTimeSec - Reciever.startTimeSec
                Log.d("check", calculatedTime.toString())
                Toast.makeText(context, "Result" + calculatedTime.toString(), Toast.LENGTH_SHORT)
                    .show()

                if (Reciever.incomingCheck) {
                    Toast.makeText(context, "INCOMING Call", Toast.LENGTH_SHORT).show()
                    Reciever.type = "INCOMING"
                } else {
                    Toast.makeText(context, "OUTGOING Call" + num, Toast.LENGTH_SHORT).show()
                    Reciever.type = "OUTGOING"

                }
                apiRequest(num.toString(), formatedRecievedDate, formattedEndDate,"10", type,calculatedTime.toString(),context)
                Log.d(
                    "details",
                    calculatedTime.toString() + "sec" + "\n" + Reciever.type + "\n" + num.toString() + "\n" + Reciever.formatedRecievedDate + "\n" + Reciever.formattedEndDate
                )

                Reciever.startTimeSec = 0
                Reciever.endTimeSec = 0
                Reciever.incomingCheck = false


            }
        }
    }



    private fun apiRequest(
        mobile_number: String,
        call_start_time: String,
        call_end_time: String,
        ringing: String,
        calltype: String,
        talktime: String,
        context: Context
    ) {

        val preferences: SharedPreferences = context.getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val userId = preferences.getString("user_id", "null")
        val token = preferences.getString("token", "null")


        val url = "https://callcrm.techfreelancepro.com/api/callDetails/lead"
        val queue = Volley.newRequestQueue(context)
        val request: StringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener<String?> { response ->
                try {
                    val respObj = JSONObject(response)
                    Log.d("obj",respObj.toString())






                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                Toast.makeText(context, "Credentials Are Incorrect", Toast.LENGTH_SHORT)
                    .show()

            }) {

            override fun getHeaders(): MutableMap<String, String> {
                val params: MutableMap<String, String> = java.util.HashMap()
                params.put("x-user-id",userId.toString())
                params.put("Authorization","Bearer $token")
                return params
            }

            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
//                params["mobile_number"] = mobile_number
//                params["call_start_time"] = call_start_time
//                params["call_end_time"] = call_end_time
//                params["ringing"] = ringing
//                params["calltype"] = calltype
//                params["talktime"] = talktime


                val jsonObject = JSONObject()
                try {
                    jsonObject.put("mobile_number", mobile_number)
                    jsonObject.put("call_start_time", call_start_time)
                    jsonObject.put("call_end_time", call_end_time)
                    jsonObject.put("ringing", ringing)
                    jsonObject.put("calltype", calltype)
                    jsonObject.put("talktime", talktime)
                    params["call_details"] = jsonObject.toString()
                } catch (e: JSONException) {
                    throw RuntimeException(e)
                }

                val dd=params


                return dd
            }
        }
        queue.add(request)
    }
}