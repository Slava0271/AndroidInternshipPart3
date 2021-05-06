package com.example.androidinternshippart3.admin

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.admin.users.AdminsUsers
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.tests.Tests
import com.example.androidinternshippart3.database.tests.TestsDao
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.login.LoginFragment
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_admins_users.*
import kotlinx.coroutines.launch

class AdminViewModel(
        val usersDao: UsersDao,
        val accessDao: AccessDao,
        val testsDao: TestsDao,
        application: Application,
        val context: Context,
        val supportFragmentManager: FragmentManager


) : AndroidViewModel(application) {
    fun switchFragment() {
        val fragment: Fragment = AdminsUsers()
        changeFragment(fragment)
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = this.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        this.supportFragmentManager.executePendingTransactions();
        fragmentTransaction.commit()
    }


    private suspend fun get(long: Long): Tests? {
        return testsDao.get(long)
    }

     fun changeBackFragment() {
        val fragment: Fragment = LoginFragment()
        changeFragment(fragment)
    }

}