package com.whycody.catshealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.whycody.catshealth.startup.StartupFragment

class MainActivity : AppCompatActivity(), MainNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) showStartupFragment()
    }

    private fun showStartupFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, StartupFragment())
            .commit()
    }

    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean, tag: String?) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)
        if (addToBackstack) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun removeFragmentFromBackStack(tag: String) {
        val fragment = supportFragmentManager.findFragmentByTag(tag)?: return
        val transaction = supportFragmentManager
            .beginTransaction()
            .remove(fragment)
        transaction.commit()
    }
}