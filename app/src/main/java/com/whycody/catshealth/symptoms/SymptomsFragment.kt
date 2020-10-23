package com.whycody.catshealth.symptoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.whycody.catshealth.MainActivity
import com.whycody.catshealth.R
import com.whycody.catshealth.data.SymptomItem
import com.whycody.catshealth.databinding.FragmentSymptomsBinding
import com.whycody.catshealth.symptoms.recycler.SymptomAdapter
import kotlinx.android.synthetic.main.fragment_symptoms.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class SymptomsFragment : Fragment() {

    private lateinit var symptomsBottomSheetBeh: BottomSheetBehavior<LinearLayout>
    private val symptomsViewModel: SymptomsViewModel by viewModel()
    private var checkedSymptomItems = listOf<SymptomItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentSymptomsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_symptoms, container, false)
        symptomsBottomSheetBeh = BottomSheetBehavior.from(binding.symptomsBottomSheet)
        symptomsBottomSheetBeh.state = BottomSheetBehavior.STATE_HIDDEN
        setupRecyclerView(binding)
        return binding.root
    }

    private fun setupRecyclerView(binding: FragmentSymptomsBinding) {
        val adapter = SymptomAdapter(symptomsViewModel)
        binding.symptomsRecycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.symptomsRecycler.adapter = adapter
        observeSymptoms(adapter, binding)
        loadLayoutAnimation(binding.symptomsRecycler)
    }

    private fun observeSymptoms(adapter: SymptomAdapter, binding: FragmentSymptomsBinding) {
        symptomsViewModel.getSymptomsItems().observe(activity as MainActivity, {
            refreshBinding(binding, it)
            refreshAdapter(adapter, it)
            refreshBottomSheetState()
        })
    }

    private fun refreshBinding(binding: FragmentSymptomsBinding, symptomsItemsList: List<SymptomItem>) {
        checkedSymptomItems = symptomsItemsList.filter { symptomItem -> symptomItem.checked }
        binding.checkedSymptomsSize = checkedSymptomItems.size
    }

    private fun refreshBottomSheetState() {
        symptomsBottomSheetBeh.state =
            if(checkedSymptomItems.isNotEmpty())
                BottomSheetBehavior.STATE_EXPANDED
            else BottomSheetBehavior.STATE_HIDDEN
    }

    private fun refreshAdapter(adapter: SymptomAdapter, symptomsItemsList: List<SymptomItem>) {
        if(adapter.itemCount == 0)
            view?.symptomsRecycler?.scheduleLayoutAnimation()
        adapter.submitList(symptomsItemsList)
    }

    private fun loadLayoutAnimation(recyclerView: RecyclerView) {
        val layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(recyclerView.context, R.anim.layout_fall_down)
        recyclerView.layoutAnimation = layoutAnimationController
    }
}