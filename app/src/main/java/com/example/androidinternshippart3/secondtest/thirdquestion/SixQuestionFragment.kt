package com.example.androidinternshippart3.secondtest.thirdquestion

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
import com.example.androidinternshippart3.databinding.FragmentSixQuestionBinding
import com.example.androidinternshippart3.secondtest.secondquestion.FifthQuestionFactory
import com.example.androidinternshippart3.secondtest.secondquestion.FifthQuestionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fifth_question.*
import kotlinx.android.synthetic.main.fragment_six_question.*
import java.io.FileNotFoundException
import java.io.IOException

class SixQuestionFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSixQuestionBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_six_question, container, false
        )
        val args = arguments
        val userId = SixQuestionFragmentArgs.fromBundle(args!!).userId

        val application = requireNotNull(this.activity).application

        val dataSourceQuestions = DataBase.getInstance(application).questionsDao
        val dataSourceAnswers = DataBase.getInstance(application).answersDao
        val dataSourceResults = DataBase.getInstance(application).resultsDao

        val viewModelFactory =
                SixQuestionFactory(
                        application,
                        dataSourceQuestions,
                        dataSourceAnswers,
                        userId,
                        dataSourceResults
                )

        val sixQuestionViewModel =
                ViewModelProvider(this, viewModelFactory).get(SixQuestionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.sixQuestionViewModel = sixQuestionViewModel

        sixQuestionViewModel.setImageEvent.observe(viewLifecycleOwner) {
            imageViewQuestion6.setImageDrawable(getDrawable("question6.jpg"))
        }
        sixQuestionViewModel.firstButtonEvent.observe(viewLifecycleOwner) {
            sixQuestionViewModel.sixQuestion(false)
            navigate(it)
        }
        sixQuestionViewModel.secondButtonEvent.observe(viewLifecycleOwner) {
            sixQuestionViewModel.sixQuestion(true)
            navigate(it)
        }
        sixQuestionViewModel.thirdButtonEvent.observe(viewLifecycleOwner) {
            sixQuestionViewModel.sixQuestion(false)
            navigate(it)
        }
        sixQuestionViewModel.navigateBackEvent.observe(viewLifecycleOwner, ::navigate)

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
