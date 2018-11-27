package com.yourcompany.bro.hi.systemdata.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Button
import com.yourcompany.bro.hi.systemdata.MainActivity
import com.yourcompany.bro.hi.systemdata.R


class MainFragment : Fragment(), View.OnClickListener {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(false)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity!!.title = "My App Name"
        val view: View = inflater!!.inflate(R.layout.main_fragment, container, false)
        val systemInfo: Button = view.findViewById(R.id.button)
        systemInfo.setOnClickListener(this)
        val fragCommun: Button = view.findViewById(R.id.button2)
        fragCommun.setOnClickListener(this)
        return view

    }


    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.button -> {
                swapFragment()
            }

            R.id.button2 -> {
                secFragment()
             }
            }
    }

    private fun swapFragment() {
        val systemInfo = FragmentSystemInfo()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment1, systemInfo)
         fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    private fun secFragment() {
        val systemInfo = CommunicationFrgment()
        val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment1, systemInfo)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    override fun onResume() {
        super.onResume()
        val actionBar = (activity as MainActivity).getSupportActionBar()
        actionBar!!.setDisplayHomeAsUpEnabled(false)
        actionBar!!.setHomeButtonEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.fragment_menu, menu)
    }
}