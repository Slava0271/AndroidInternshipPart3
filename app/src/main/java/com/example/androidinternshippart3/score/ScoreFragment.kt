package com.example.androidinternshippart3.score

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.androidinternshippart3.R
import com.example.androidinternshippart3.database.DataBase
import com.example.androidinternshippart3.databinding.FragmentScoreBinding
import kotlinx.android.synthetic.main.activity_main.*


class ScoreFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_score, container, false
        )

        val application = requireNotNull(this.activity).application
        val args = arguments

        val userId = ScoreFragmentArgs.fromBundle(args!!).userId
        val isManager = ScoreFragmentArgs.fromBundle(args).manager
        Log.d("score", userId.toString())
        var toManager: Boolean = false
//        if (userId!!.toCharArray()[0] == '-') {
//            toManager = true
//            userId = userId.substring(1)
//            Log.d("user",userId)
//        }

        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory = ScoreFragmentFactory(application, dataSourceResults, userId.toInt(), isManager)
        val scoreViewModel =
                ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.scoreViewModel = scoreViewModel

        scoreViewModel.buttonClickEventToUser.observe(viewLifecycleOwner, ::navigate)
        scoreViewModel.buttonClickEventToManager.observe(viewLifecycleOwner, ::navigate)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideOtherFragments()
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    private fun hideOtherFragments() {
        fragment.view?.setBackgroundColor(Color.WHITE);
    }
}