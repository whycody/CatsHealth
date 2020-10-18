package com.whycody.catshealth.symptoms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whycody.catshealth.data.SymptomItem
import com.whycody.catshealth.data.symptom.SymptomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SymptomsViewModel(symptomRepository: SymptomRepository): ViewModel(), SymptomClickListener {

    private val symptomsListFlow = symptomRepository.flowAllSymptoms()
    private val symptomsItemsList: MutableLiveData<List<SymptomItem>> = MutableLiveData()
    private var checkedIdsFlow = MutableStateFlow(emptyList<Int>())

    init {
        viewModelScope.launch {
            collectSymptomsItems()
        }
    }

    private suspend fun collectSymptomsItems() {
        flowSymptomsItems().collect { symptomsItems ->
            symptomsItemsList.postValue(symptomsItems)
        }
    }

    private fun flowSymptomsItems() : Flow<List<SymptomItem>> {
        return symptomsListFlow
            .combine(checkedIdsFlow) { list, checkedIds ->
                list.map { SymptomItem(it, checkedIds.contains(it.id)) }
            }
    }

    private fun checkItem(id: Int) {
        if(!checkedIdsFlow.value.isNullOrEmpty()) {
            val checkedList = checkedIdsFlow.value.toMutableList()
            checkedList.add(id)
            checkedIdsFlow.value = checkedList
        } else checkedIdsFlow.value = listOf(id)
    }

    private fun uncheckItem(id: Int) {
        if(checkedIdsFlow.value.isNullOrEmpty()) return
        val checkedList = checkedIdsFlow.value.toMutableList()
        checkedList.remove(id)
        checkedIdsFlow.value = checkedList
    }

    fun getSymptomsItems(): LiveData<List<SymptomItem>> {
        return symptomsItemsList
    }

    override fun onClick(symptomItem: SymptomItem, position: Int) {
        if(symptomItem.checked) uncheckItem(symptomItem.symptom.id)
        else checkItem(symptomItem.symptom.id)
    }
}

