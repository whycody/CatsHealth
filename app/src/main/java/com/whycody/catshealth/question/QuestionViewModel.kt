package com.whycody.catshealth.question

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.utils.SearchDiseaseUtil

class QuestionViewModel(private val searchResult: SearchResult,
                        private val searchDiseaseUtil: SearchDiseaseUtil): ViewModel() {

    var question: MutableLiveData<String> = MutableLiveData(searchResult.currentQuestion?.question)
    var clickedBtnIndex: MutableLiveData<Int> = MutableLiveData(-1)

    fun btnClicked(noticed: Boolean) {
        setSearchResultVariables(noticed)
        searchDiseaseUtil.refreshSearchResult(searchResult)
        clickedBtnIndex.value = if(noticed) 0 else 1
    }

    private fun setSearchResultVariables(noticed: Boolean) {
        val currentQuestion = searchResult.currentQuestion
        if(currentQuestion?.type == QuestionFragment.SYMPTOM_QUESTION) {
            if(noticed) searchResult.symptomsIds.add(currentQuestion.askingObjectId)
            else searchResult.alreadyAskedSymptomsIds.add(currentQuestion.askingObjectId)
        } else if(!noticed)
            searchResult.alreadyAskedDiseasesQuestionsIds.add(currentQuestion!!.askingObjectId)
    }

    fun setupQuestion() {
        question.value = "No more questions"
        clickedBtnIndex.value = -1
        if(searchResult.currentQuestion != null)
            question.value = searchResult.currentQuestion?.question
        else if(searchResult.currentQuestion == null && searchResult.probableDiseaseId != null)
            Log.d("MOJTAG", "Probable disease: ${searchResult.probableDiseaseId}")
        else Log.d("MOJTAG", "Could not find probable disease")
    }

    fun getQuestion(): LiveData<String> = question

    fun getClickedBtnIndex(): LiveData<Int> = clickedBtnIndex
}