package com.whycody.catshealth.result.recycler

import androidx.recyclerview.widget.DiffUtil
import com.whycody.catshealth.data.Disease

class ResultDiffCallback: DiffUtil.ItemCallback<Disease>() {

    override fun areItemsTheSame(oldItem: Disease, newItem: Disease): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Disease, newItem: Disease): Boolean {
        return oldItem == newItem
    }
}