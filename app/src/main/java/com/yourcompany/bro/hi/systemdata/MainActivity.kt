package com.yourcompany.bro.hi.systemdata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yourcompany.bro.hi.systemdata.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment1, MainFragment())
            .commit()
        setContentView(R.layout.activity_main)
    }


}
