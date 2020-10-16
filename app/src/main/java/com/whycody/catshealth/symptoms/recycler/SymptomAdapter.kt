package com.whycody.catshealth.symptoms.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whycody.catshealth.R
import com.whycody.catshealth.data.Symptom

class SymptomAdapter: RecyclerView.Adapter<SymptomHolder>() {

    private var symptoms: List<Symptom> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.item_symptom, parent, false)
        return SymptomHolder(itemView)
    }

    override fun onBindViewHolder(holder: SymptomHolder, position: Int) {
        holder.setSymptomName(symptoms[position].name)
        setUnderlineVisibility(holder, position)
    }

    private fun setUnderlineVisibility(holder: SymptomHolder, position: Int) {
        if(position == 0) holder.setUnderlineVisibility(View.INVISIBLE)
        else holder.setUnderlineVisibility(View.VISIBLE)
    }

    override fun getItemCount(): Int {
        return symptoms.size
    }

    fun setSymptoms(symptoms: List<Symptom>) {
        this.symptoms = symptoms
        notifyDataSetChanged()
    }
}