package com.example.androidinternshippart3.admin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentAdminBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_admin.*


class AdminFragment : Fragment() {

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAdminBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_admin, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSourceUsers = DataBase.getInstance(application).usersDao
        val dataSourceAccess = DataBase.getInstance(application).accessDao
        val dataSourceTests = DataBase.getInstance(application).testsDao

        val viewModelFactory = AdminFragmentFactory(
                dataSourceUsers,
                dataSourceAccess,
                dataSourceTests,
                application,
                this.requireContext(),
                this.requireActivity().supportFragmentManager
        )

        val adminViewModel =
                ViewModelProvider(this, viewModelFactory).get(AdminViewModel::class.java)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.adminViewModel = adminViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
    }

}