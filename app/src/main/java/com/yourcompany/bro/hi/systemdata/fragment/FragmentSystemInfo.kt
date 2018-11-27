package com.yourcompany.bro.hi.systemdata.fragment



import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.yourcompany.bro.hi.systemdata.MainActivity
import com.yourcompany.bro.hi.systemdata.R
import com.yourcompany.bro.hi.systemdata.eventbus.OnReceiverEvent
import com.yourcompany.bro.hi.systemdata.eventbus.OnReceiverEventPower
import com.yourcompany.bro.hi.systemdata.eventbus.OnReceiverHeadPhoneEvent
import com.yourcompany.bro.hi.systemdata.eventbus.OnTimeZoneEv
import com.yourcompany.bro.hi.systemdata.receiver.HeadPhoneReceiver
import com.yourcompany.bro.hi.systemdata.receiver.NetworkChangeReceiver
import com.yourcompany.bro.hi.systemdata.receiver.PowerConnectionReceiver
import com.yourcompany.bro.hi.systemdata.receiver.TimeZon
import kotlinx.android.synthetic.main.info_system_fragment.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


open class FragmentSystemInfo : Fragment() {

    var mNetworkReceiver: BroadcastReceiver? = null
    var receiver :BroadcastReceiver? = null
    var receiver1 :BroadcastReceiver? = null
    var receiverTime :BroadcastReceiver? = null




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        registerTimeReceiver()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.info_system_fragment, container, false)
        activity!!.title = "System Info"
        mNetworkReceiver = NetworkChangeReceiver()
        receiver= HeadPhoneReceiver()
        receiver1 = PowerConnectionReceiver()
        receiverTime = TimeZon()
        registerNetworkBroadcastForNougat()
        registerPowerStatusChangeReceiver()
        registerHeadsetReceiver()

        return view
    }

    @Subscribe
    fun onInternetStatus(event: OnReceiverEvent) {

        val interStatus = event.status

        buttonI.text= interStatus

    }
    @Subscribe
    fun onPowerStatus(event: OnReceiverEventPower) {

        val powerStatus = event.status

        buttonP.text= powerStatus

    }
    @Subscribe
    fun onHeadSetStatus(event: OnReceiverHeadPhoneEvent) {

        val hedSetStatus = event.status

        buttonH.text= hedSetStatus

    }
    @Subscribe
    fun onTimeZoneStatus(event: OnTimeZoneEv) {

        val timeStatus = event.status

        buttonT.text= timeStatus

    }

    fun registerPowerStatusChangeReceiver() {

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        activity!!.registerReceiver(receiver1, intentFilter)


    }
    fun registerHeadsetReceiver() {

        val filter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        activity!!.registerReceiver(receiver, filter)
    }
    fun registerTimeReceiver() {

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        activity!!.registerReceiver(receiverTime, filter)
    }



    override fun onResume() {
        super.onResume()
        val actionBar = (activity as MainActivity).getSupportActionBar()
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setHomeButtonEnabled(true)

        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity!!.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            activity!!.registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity!!.registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    private fun unregisterNetworkChanges() {
        try {
            activity!!.unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChanges()


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