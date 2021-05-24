package com.example.androidinternshippart3.manager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentManagerBinding
import com.example.androidinternshippart3.manager.list.UserAdapter


class ManagerFragment : Fragment() {
    private lateinit var binding: FragmentManagerBinding
    private val userAdapter = UserAdapter {
        navigate(ManagerFragmentDirections.actionManagerFragmentToScoreFragment(0, true, it))
    }

    private val viewModel: ManagerViewModel by viewModels {
        ManagerFragmentFactory(DataBase.getInstance(requireContext()))
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel.navigateBackEvent.observe(viewLifecycleOwner, ::navigate)
        binding = FragmentManagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.managerViewModel = viewModel
        binding.recyclerViewManager.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewManager.adapter = userAdapter
        viewModel.users.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)


    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}