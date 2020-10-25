package com.whycody.catshealth.result.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whycody.catshealth.R
import com.whycody.catshealth.data.Disease
import com.whycody.catshealth.databinding.ItemDiseaseBinding

class ResultAdapter: ListAdapter<Disease, ResultAdapter.ResultHolder>(ResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemDiseaseBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_disease, parent, false)
        return ResultHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.setupData(getItem(position), position)
    }

    inner class ResultHolder(private val binding: ItemDiseaseBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun setupData(disease: Disease, position: Int) {
            binding.disease = disease
            binding.position = position
        }
    }
}