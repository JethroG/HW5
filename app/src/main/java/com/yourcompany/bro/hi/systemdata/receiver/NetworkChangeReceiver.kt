package com.yourcompany.bro.hi.systemdata.receiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.yourcompany.bro.hi.systemdata.eventbus.OnReceiverEvent
import org.greenrobot.eventbus.EventBus


internal class NetworkChangeReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        try {
            if (isOnline(context)) {
                EventBus.getDefault().post(OnReceiverEvent("Online Connect Intenet "))
                Log.e("ola", "Online Connect Intenet ")
            } else {
                EventBus.getDefault().post(OnReceiverEvent("Conectivity Failure !!! "))
                Log.e("ola", "Conectivity Failure !!! ")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }



    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }

    }
}