package com.whycody.catshealth.symptoms.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whycody.catshealth.R
import com.whycody.catshealth.BR
import com.whycody.catshealth.data.SymptomItem
import com.whycody.catshealth.symptoms.SymptomClickListener

class SymptomAdapter(val symptomClickListener: SymptomClickListener): ListAdapter<SymptomItem,
        SymptomAdapter.SymptomHolder>(SymptomDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
            R.layout.item_symptom, parent, false)
        return SymptomHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomHolder, position: Int) {
        holder.setupData(getItem(position))
    }

    inner class SymptomHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

        fun setupData(symptomItem: SymptomItem) {
            binding.setVariable(BR.symptomItem, symptomItem)
            binding.setVariable(BR.position, layoutPosition)
            binding.setVariable(BR.listener, symptomClickListener)
            binding.executePendingBindings()
        }
    }
}