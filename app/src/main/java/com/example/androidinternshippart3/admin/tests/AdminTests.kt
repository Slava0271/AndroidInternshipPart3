package com.example.androidinternshippart3.admin.tests

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.admin.users.TestAdapter
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.database.access.Access
import com.example.androidinternshippart3.database.access.AccessDao
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_admins_users.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AdminTests : Fragment() {

    var dataSourceUsers: UsersDao? = null
    var dataSourceAccess: AccessDao? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val args = arguments

        val application = requireNotNull(this.activity).application
        dataSourceUsers = DataBase.getInstance(application).usersDao
        dataSourceAccess = DataBase.getInstance(application).accessDao


        val testNumber = AdminTestsArgs.fromBundle(args!!).sendArg
        val uiScope = CoroutineScope(Dispatchers.Main)

        uiScope.launch {

            val adapter = TestAdapter(getUsersFirstTest(testNumber))
            recyclerView.adapter = adapter
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_tests, container, false)
    }

    private suspend fun getUsersFirstTest(int: Int): ArrayList<String> {
        val list: ArrayList<String> = ArrayList()
        var count = 1.toLong()
        while (true) {
            val access = getAccess(count) ?: break

            addUsersToList(count, access, list, int)
            count++
        }
        return list
    }

    private suspend fun addUsersToList(count: Long, access: Access, list: ArrayList<String>, int: Int) {
        val user = getUser(count)
        if (int == 1) {
            if (access.accessTest1) {
                list.add(
                        "name: " + user!!.firstName + " " + user.lastName
                                + "\nlogin: " + user.login + " password: " + user.password
                )
            }
        } else if (int == 2) {
            if (access.accessTest2) {
                list.add(
                        "name: " + user!!.firstName + " " + user.lastName
                                + "\nlogin: " + user.login + " password: " + user.password
                )
            }
        } else if (int == 3) {
            if (access.accessTest3) {
                list.add(
                        "name: " + user!!.firstName + " " + user.lastName
                                + "\nlogin: " + user.login + " password: " + user.password
                )
            }
        }

    }


    private suspend fun getAccess(long: Long): Access? {
        return dataSourceAccess!!.get(long)
    }

    private suspend fun getUser(long: Long): Users? {
        return dataSourceUsers!!.get(long)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        backFragment()

    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    private fun backFragment() {
        imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.adminFragment)

        }
    }


}