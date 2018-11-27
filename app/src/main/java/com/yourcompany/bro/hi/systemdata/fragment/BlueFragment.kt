package com.yourcompany.bro.hi.systemdata.fragment

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.yourcompany.bro.hi.systemdata.R
import com.yourcompany.bro.hi.systemdata.receiver.ButtonClick
import org.greenrobot.eventbus.EventBus


class BlueFragment : Fragment() {

    var receiver : BroadcastReceiver? = null
    var intentFilter: IntentFilter? = null
    var intentFilter1: IntentFilter? = null
    var intentFilter2: IntentFilter? = null
    var intentFilter3: IntentFilter? = null
    var intent :Intent? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_bkue, container, false)
        receiver = ButtonClick()
        intentFilter = IntentFilter("com.yourcompany.bro.hi.systemdata.CLICK")
        intentFilter1 = IntentFilter("com.yourcompany.bro.hi.systemdata.SWITCHON")
        intentFilter2 = IntentFilter("com.yourcompany.bro.hi.systemdata.SWITCHOFF")
        intentFilter3 = IntentFilter("com.yourcompany.bro.hi.systemdata.EDITTEXT")


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val switch :Switch= view.findViewById(R.id.switch1)

            switch.setOnCheckedChangeListener { _, isChecked ->
                switch.isChecked
            if(isChecked){
                intent = Intent("com.yourcompany.bro.hi.systemdata.SWITCHON")
                activity!!.sendBroadcast(intent)}
            else if (!isChecked){
                intent = Intent("com.yourcompany.bro.hi.systemdata.SWITCHOFF")
                activity!!.sendBroadcast(intent)
            }

                }

        val button: Button? = view.findViewById(R.id.pressed)
        val editText: EditText= view.findViewById(R.id.editText)
        val msg: String = editText.text.toString()

        button?.setOnClickListener { v: View ->
            intent = Intent("com.yourcompany.bro.hi.systemdata.CLICK")
            activity!!.sendBroadcast(intent)
            if(msg.trim().isNotEmpty()) {
                intent = Intent("com.yourcompany.bro.hi.systemdata.EDITTEXT")
                activity!!.sendBroadcast(intent)
                EventBus.getDefault().post(com.yourcompany.bro.hi.systemdata.eventbus.EditText(msg))
            }else{
                Toast.makeText(context,"Please enter text",Toast.LENGTH_LONG).show()

            }
        }





    }



    override fun onResume() {
        super.onResume()
        activity!!.registerReceiver(receiver, intentFilter)
        activity!!.registerReceiver(receiver, intentFilter1)
        activity!!.registerReceiver(receiver, intentFilter2)
        activity!!.registerReceiver(receiver, intentFilter3)



    }

  override fun onDestroy() {
        super.onDestroy()
      activity!!.unregisterReceiver(receiver)
      activity!!.registerReceiver(receiver, intentFilter1)
      activity!!.registerReceiver(receiver, intentFilter2)
      activity!!.registerReceiver(receiver, intentFilter3)

    }

}