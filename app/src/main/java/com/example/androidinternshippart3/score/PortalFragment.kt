package com.example.androidinternshippart3.score

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_portal.*


class PortalFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
        val userId = arguments?.getString("number")!!.toInt()
        seeResultButton.setOnClickListener {
            findNavController().navigate(R.id.scoreFragment, sendData(userId))
        }
        exitButton.setOnClickListener {
            findNavController().navigate(R.id.userFragment)
        }
    }

    private fun sendData(int: Int): Bundle {
        val bundle = Bundle()
        bundle.putString("number", int.toString())
        return bundle
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }
}