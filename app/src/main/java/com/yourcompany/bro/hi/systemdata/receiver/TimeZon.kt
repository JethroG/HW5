package com.yourcompany.bro.hi.systemdata.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.yourcompany.bro.hi.systemdata.eventbus.OnTimeZoneEv
import org.greenrobot.eventbus.EventBus

class TimeZon : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
     override fun onReceive(context: Context, intent: Intent) {

        if ( intent.action == Intent.ACTION_TIMEZONE_CHANGED){
            EventBus.getDefault().post(OnTimeZoneEv("TimeZone was changed "))
            Log.e("ola", "TimeZone was changed ")
        }

    }
}