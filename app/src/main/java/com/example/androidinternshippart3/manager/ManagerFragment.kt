package com.example.androidinternshippart3.manager

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.RecyclerItemClickListener
import com.example.androidinternshippart3.admin.users.TestAdapter
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_admins_users.*
import kotlinx.android.synthetic.main.fragment_manager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ManagerFragment : Fragment() {
    var dataSourceUsers: UsersDao? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val uiScope = CoroutineScope(Dispatchers.Main)

        uiScope.launch {
            val adapter = TestAdapter(getUsers())
            recyclerViewManager.adapter = adapter
        }
        dataSourceUsers = DataBase.getInstance(application).usersDao


        return inflater.inflate(R.layout.fragment_manager, container, false)
    }

    private suspend fun getUsers(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        var i: Long = 1
        while (true) {
            if (get(i) == null) break
            arrayList.add(
                "name: " + get(i)!!.firstName + " " + get(i)!!.lastName
                        + "\nlogin: " + get(i)!!.login + " password: " + get(i)!!.password
            )
            i++
        }

        return arrayList
    }


    private suspend fun get(long: Long): Users? {
        return dataSourceUsers!!.get(long)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        recyclerViewManager.layoutManager = LinearLayoutManager(this.requireContext())
        recycleViewListener()
    }
    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()

        bundle.putString("number", "-$int")
        return bundle
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    private fun recycleViewListener(){
        recyclerViewManager.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Log.d("item", position.toString())
                            findNavController().navigate(R.id.scoreFragment, sendData(position))
                    }
                    override fun onLongItemClick(view: View, position: Int) {
                    }
                })
        )
    }

}