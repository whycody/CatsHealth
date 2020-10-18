package com.whycody.catshealth.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whycody.catshealth.MainActivity
import com.whycody.catshealth.R
import com.whycody.catshealth.symptoms.recycler.SymptomAdapter
import kotlinx.android.synthetic.main.fragment_symptoms.view.*
import org.koin.android.ext.android.inject

class SymptomsFragment : Fragment() {

    private val symptomsViewModel: SymptomsViewModel by inject()
    private val adapter = SymptomAdapter(symptomsViewModel)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_symptoms, container, false)
        view.symptomsRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        view.symptomsRecycler.adapter = adapter
        loadLayoutAnimation(view.symptomsRecycler)
        observeSymptoms()
        return view
    }

    private fun observeSymptoms() {
        symptomsViewModel.getSymptomsItems().observe(activity as MainActivity, {
            adapter.setSymptomsItems(it)
            view?.symptomsRecycler?.scheduleLayoutAnimation()
        })
    }

    private fun loadLayoutAnimation(recyclerView: RecyclerView) {
        val layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.layout_fall_down)
        recyclerView.layoutAnimation = layoutAnimationController
    }
}