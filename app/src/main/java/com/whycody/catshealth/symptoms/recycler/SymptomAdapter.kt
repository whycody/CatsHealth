package com.whycody.catshealth.symptoms.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.whycody.catshealth.R
import com.whycody.catshealth.BR
import com.whycody.catshealth.data.SymptomItem
import com.whycody.catshealth.symptoms.SymptomClickListener
import kotlinx.android.synthetic.main.item_symptom.view.*

class SymptomAdapter(val symptomClickListener: SymptomClickListener): RecyclerView.Adapter<SymptomAdapter.SymptomHolder>(){

    private var symptomsItems: List<SymptomItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
            R.layout.item_symptom, parent, false)
        return SymptomHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomHolder, position: Int) {
        holder.setupData(symptomsItems[position])
        holder.setCheckBoxListener()
    }

    override fun getItemCount(): Int {
        return symptomsItems.size
    }

    fun setSymptomsItems(symptomsItems: List<SymptomItem>) {
        this.symptomsItems = symptomsItems
        notifyDataSetChanged()
    }

    inner class SymptomHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

        fun setupData(symptomItem: SymptomItem) {
            binding.setVariable(BR.symptomItem, symptomItem)
            binding.setVariable(BR.position, layoutPosition)
            binding.setVariable(BR.listener, symptomClickListener)
            binding.executePendingBindings()
        }

        fun setCheckBoxListener() {
            binding.root.setOnClickListener{
                binding.root.symptomCheck.isChecked = !binding.root.symptomCheck.isChecked
            }
        }
    }
}