package com.whycody.catshealth.symptoms.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_symptom.view.*

class SymptomHolder(itemView: View) : RecyclerView.ViewHolder(itemView), SymptomRowView {

    override fun setSymptomName(name: String) {
        itemView.symptomName.text = name
    }

    override fun setSymptomChecked(checked: Boolean) {
        itemView.symptomCheck.isChecked = checked
    }

    override fun setUnderlineVisibility(visibility: Int) {
        itemView.underline.visibility = visibility
    }
}