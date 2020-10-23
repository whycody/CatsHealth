package com.whycody.catshealth.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.whycody.catshealth.R
import com.whycody.catshealth.databinding.FragmentQuestionBinding
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment : Fragment() {

    private val questionViewModel: QuestionViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentQuestionBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_question, container, false)
        binding.questionViewModel = questionViewModel
        return binding.root
    }

    companion object {
        const val SYMPTOM_QUESTION = 0
        const val ADDITIONAL_QUESTION = 1
    }

}