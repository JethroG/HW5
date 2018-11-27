package com.yourcompany.bro.hi.systemdata.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.yourcompany.bro.hi.systemdata.MainActivity
import com.yourcompany.bro.hi.systemdata.R

class CommunicationFrgment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_communication, container, false)
        activity!!.title = "Fragment communic"
        val systemInfo = BlueFragment()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentblue, systemInfo)
        fragmentTransaction.addToBackStack(null)

        val systegreen = GreenFragment()
        fragmentTransaction.replace(R.id.fragmentgreen, systegreen)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        return view
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
}