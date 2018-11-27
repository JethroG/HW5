package com.yourcompany.bro.hi.systemdata.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yourcompany.bro.hi.systemdata.R
import com.yourcompany.bro.hi.systemdata.eventbus.ButtonStatus
import com.yourcompany.bro.hi.systemdata.eventbus.EditText
import com.yourcompany.bro.hi.systemdata.eventbus.SwitchOff
import com.yourcompany.bro.hi.systemdata.eventbus.SwitchOn
import kotlinx.android.synthetic.main.fragment_green.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class GreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_green, container, false)
        return view
    }

    @SuppressLint("SetTextI18n")
    @Subscribe
    fun onSwitch(event: SwitchOn) {

        val interStatus = event.status

        textViewSwitch.text= "Switch state :$interStatus"

    }
    @SuppressLint("SetTextI18n")
    fun offSwitch(event: SwitchOff) {
        val interStatus = event.status
        textViewSwitch.text= "Switch state :$interStatus"

    }


    @SuppressLint("SetTextI18n")
    @Subscribe
    fun onStatus(event: ButtonStatus) {

        val interStatus = event.status

        textViewButtonState.text= "Button state :$interStatus"

    }
    @SuppressLint("SetTextI18n")
    @Subscribe
    fun onText(event: EditText) {

        val interStatus = event.status

        textViewEditText.text= "TextView detail: :$interStatus"

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }


    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

}