package com.yourcompany.bro.hi.systemdata.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yourcompany.bro.hi.systemdata.eventbus.OnReceiverHeadPhoneEvent
import org.greenrobot.eventbus.EventBus

class HeadPhoneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_HEADSET_PLUG) {
            val state = intent.getIntExtra("state", -1)
            when (state) {
                0 ->EventBus.getDefault().post(OnReceiverHeadPhoneEvent("Headset unplugged"))
                1 ->EventBus.getDefault().post(OnReceiverHeadPhoneEvent("Headset plugged"))
            }
        }
    }
}

