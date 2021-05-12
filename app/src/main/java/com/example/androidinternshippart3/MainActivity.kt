package com.example.androidinternshippart3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.androidinternshippart3.login.LoginFragment
import com.example.androidinternshippart3.register.RegisterFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val fragmentRegister = RegisterFragment()
                val loginFragment = LoginFragment()
                when (tab!!.position) {
                    1 -> changeFragment(loginFragment)
                    0 -> changeFragment(fragmentRegister)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = this.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        this.supportFragmentManager.executePendingTransactions();
        fragmentTransaction.commit()
    }

}

