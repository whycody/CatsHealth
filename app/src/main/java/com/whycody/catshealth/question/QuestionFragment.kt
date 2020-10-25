package com.whycody.catshealth.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.whycody.catshealth.MainActivity
import com.whycody.catshealth.R
import com.whycody.catshealth.databinding.FragmentQuestionBinding
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment : Fragment(), Animation.AnimationListener {

    private val questionViewModel: QuestionViewModel by viewModel()
    private lateinit var headerQuestion: LinearLayout
    private lateinit var questionCircle: ImageView
    private lateinit var fadeOutAnim: Animation
    private lateinit var fadeInAnim: Animation
    private lateinit var circleQuestionAnim: Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentQuestionBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_question, container, false)
        setupAnimations()
        binding.questionViewModel = questionViewModel
        binding.lifecycleOwner = activity
        headerQuestion = binding.headerQuestion
        questionCircle = binding.questionCircle
        headerQuestion.startAnimation(fadeInAnim)
        questionCircle.startAnimation(circleQuestionAnim)
        observeClickedBtnIndex()
        return binding.root
    }

    private fun observeClickedBtnIndex() {
        questionViewModel.getClickedBtnIndex().observe(activity as MainActivity, {
            if(it == -1) return@observe
            headerQuestion.startAnimation(fadeOutAnim)
        })
    }

    private fun setupAnimations() {
        fadeOutAnim = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
        fadeInAnim = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        circleQuestionAnim = AnimationUtils.loadAnimation(context, R.anim.circle_question_anim)
        fadeOutAnim.setAnimationListener(this)
    }

    override fun onAnimationStart(animation: Animation?) { }

    override fun onAnimationEnd(animation: Animation?) {
        questionViewModel.setupQuestion()
        headerQuestion.startAnimation(fadeInAnim)
    }

    override fun onAnimationRepeat(animation: Animation?) { }

    companion object {
        const val SYMPTOM_QUESTION = 0
        const val ADDITIONAL_QUESTION = 1
    }

}