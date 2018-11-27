package com.yourcompany.bro.hi.systemdata.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.yourcompany.bro.hi.systemdata.eventbus.ButtonStatus
import com.yourcompany.bro.hi.systemdata.eventbus.SwitchOff
import com.yourcompany.bro.hi.systemdata.eventbus.SwitchOn
import org.greenrobot.eventbus.EventBus

class ButtonClick :BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when {
            intent.action == "com.yourcompany.bro.hi.systemdata.CLICK" -> EventBus.getDefault().post(ButtonStatus("Pressed"))
            intent.action == "com.yourcompany.bro.hi.systemdata.SWITCHON" -> EventBus.getDefault().post(SwitchOn("ON"))
            intent.action == "com.yourcompany.bro.hi.systemdata.SWITCHOFF" -> EventBus.getDefault().post(SwitchOff("OFF"))
           // intent.action == "com.yourcompany.bro.hi.systemdata.EDITTEXT" ->
        }
    }


}