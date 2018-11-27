package com.yourcompany.bro.hi.systemdata.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yourcompany.bro.hi.systemdata.eventbus.OnReceiverEventPower
import org.greenrobot.eventbus.EventBus


class PowerConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        if(intent.action == Intent.ACTION_POWER_CONNECTED)
        {
            EventBus.getDefault().post(OnReceiverEventPower("MyPowerReceiver: Power is Connected."))

        }
        else  if(intent.action == Intent.ACTION_POWER_DISCONNECTED)
        {
            EventBus.getDefault().post(OnReceiverEventPower("MyPowerReceiver: Power is Dis-connected."))

        }
    }
}