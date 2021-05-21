package com.example.androidinternshippart3.secondtest.secondquestion

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
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
import com.example.androidinternshippart3.databinding.FragmentFifthQuestionBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fifth_question.*
import java.io.FileNotFoundException
import java.io.IOException

class FifthQuestionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFifthQuestionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_fifth_question, container, false
        )
        val args = arguments
        val userId = FifthQuestionFragmentArgs.fromBundle(args!!).userId

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory =
                FifthQuestionFactory(
                        application,
                        dataSourceQuestions,
                        dataSourceAnswers,
                        userId,
                        dataSourceResults
                )

        val fifthQuestionViewModel =
                ViewModelProvider(this, viewModelFactory).get(FifthQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.fifthQuestionViewModel = fifthQuestionViewModel
        fifthQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion5.setImageDrawable(getDrawable("question5.jpg"))
        }
        fifthQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            fifthQuestionViewModel.fifthQuestion(false)
            navigate(it)
        }
        fifthQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            fifthQuestionViewModel.fifthQuestion(true)
            navigate(it)
        }
        fifthQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            fifthQuestionViewModel.fifthQuestion(false)
            navigate(it)
        }

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