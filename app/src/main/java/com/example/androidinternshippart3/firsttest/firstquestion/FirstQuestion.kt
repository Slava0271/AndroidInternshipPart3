package com.example.androidinternshippart3.firsttest.firstquestion

import android.graphics.drawable.Drawable
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
import com.example.androidinternshippart3.databinding.FragmentFirstQuestionBinding
import kotlinx.android.synthetic.main.fragment_first_question.*
import java.io.FileNotFoundException
import java.io.IOException


class FirstQuestion : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFirstQuestionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_first_question, container, false
        )
        val args = arguments
        val userId = FirstQuestionArgs.fromBundle(args!!).userId
        val question = FirstQuestionArgs.fromBundle(args).question
        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory =
                FirstQuestionFragmentFactory(
                        application,
                        dataSourceQuestions,
                        dataSourceAnswers,
                        userId,
                        dataSourceResults,
                        question
                )

        val firstQuestionViewModel =
                ViewModelProvider(this, viewModelFactory).get(FirstQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.firstQuestionViewModel = firstQuestionViewModel

        firstQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion1.setImageDrawable(getDrawable("question$question.jpg"))
        }

        firstQuestionViewModel.navigate.observe(viewLifecycleOwner, ::navigate)


        return binding.root
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    private fun getDrawable(name: String): Drawable {
        val resource =
                try {
                    context?.assets?.open(name)
                } catch (exc: IOException) {
                    throw FileNotFoundException("No such file at $name")
                }
        return Drawable.createFromStream(resource, null)
                ?: throw Exception("Can't convert file $name to drawable")
    }
}