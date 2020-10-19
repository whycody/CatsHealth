package com.whycody.catshealth.symptoms.recycler

import androidx.recyclerview.widget.DiffUtil
import com.whycody.catshealth.data.SymptomItem

class SymptomDiffCallback: DiffUtil.ItemCallback<SymptomItem>() {

    override fun areItemsTheSame(oldItem: SymptomItem, newItem: SymptomItem): Boolean {
        return oldItem.symptom.id == newItem.symptom.id
    }

    override fun areContentsTheSame(oldItem: SymptomItem, newItem: SymptomItem): Boolean {
        return oldItem == newItem
    }
}