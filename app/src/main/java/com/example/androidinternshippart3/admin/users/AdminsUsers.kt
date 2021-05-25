package com.example.androidinternshippart3.admin.users

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.RecyclerItemClickListener
import com.example.androidinternshippart3.ShowDialog
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.database.users.Users
import com.example.androidinternshippart3.database.users.UsersDao
import com.example.androidinternshippart3.dialog.Dialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_admins_users.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AdminsUsers : Fragment(), ShowDialog {

    var dataSourceUsers: UsersDao? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application


//        val dataSourceUsers = DataBase.getInstance(application).usersDao
//        val dataSourceAccess = DataBase.getInstance(application).accessDao
        dataSourceUsers = DataBase.getInstance(application).usersDao
        val uiScope = CoroutineScope(Dispatchers.Main)

        uiScope.launch {
            val adapter = TestAdapter(getUsers())
            recyclerView.adapter = adapter
        }

        return inflater.inflate(R.layout.fragment_admins_users, container, false)
    }


    private suspend fun getUsers(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        for (n in 0 until getListSize()) {
            arrayList.add(
                    "name: " + get(n)!!.firstName + " " + get(n)!!.lastName
                            + "\nlogin: " + get(n)!!.login + " password: " + get(n)!!.password
            )
        }

        return arrayList
    }

    private suspend fun get(int: Int): Users? {
        return dataSourceUsers!!.getAllUsers()[int]
    }

    private suspend fun getListSize():Int{
        return dataSourceUsers!!.getAllUsers().size
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        changeBackFragment()

        recyclerView.addOnItemTouchListener(
                RecyclerItemClickListener(
                        context,
                        recyclerView,
                        object : RecyclerItemClickListener.OnItemClickListener {
                            override fun onItemClick(view: View, position: Int) {
                                Log.d("item", position.toString())
//                        val fragment: Fragment = UsersTestFragment()
//                        changeFragment(fragment)
//                        // do whatever
                                if (position != 0)
                                    findNavController().navigate(R.id.usersTestFragment, sendData(position))
                                else showDialog("you cannot change the admin (yourself) options")
                            }

                            override fun onLongItemClick(view: View, position: Int) {
                                // do whatever
                            }
                        })
        )
    }

    private fun changeBackFragment() {
        imageViewBack.setOnClickListener {
            findNavController().navigate(R.id.adminFragment)
        }
    }

    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()
        bundle.putString("position", int.toString())
        return bundle
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    override fun showDialog(string: String) {
        val dialog = Dialog(string)
        dialog.show(this.requireActivity().supportFragmentManager, string)

    }
}