package com.whycody.catshealth.question

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.utils.SearchDiseaseUtil

class QuestionViewModel(private val searchResult: SearchResult,
                        private val searchDiseaseUtil: SearchDiseaseUtil): ViewModel() {

    var question: MutableLiveData<String> = MutableLiveData(searchResult.currentQuestion?.question)

    fun btnClicked(noticed: Boolean) {
        setSearchResultVariables(noticed)
        searchDiseaseUtil.refreshSearchResult(searchResult)
        setupQuestion()
    }

    private fun setSearchResultVariables(noticed: Boolean) {
        val currentQuestion = searchResult.currentQuestion
        if(currentQuestion?.type == QuestionFragment.SYMPTOM_QUESTION) {
            if(noticed) searchResult.symptomsIds.add(currentQuestion.askingObjectId)
            else searchResult.alreadyAskedSymptomsIds.add(currentQuestion.askingObjectId)
        } else if(!noticed)
            searchResult.alreadyAskedDiseasesQuestionsIds.add(currentQuestion!!.askingObjectId)
    }

    private fun setupQuestion() {
        question.value = "No more questions"
        if(searchResult.currentQuestion != null)
            question.value = searchResult.currentQuestion?.question
        else if(searchResult.currentQuestion == null && searchResult.probableDiseaseId != null)
            Log.d("MOJTAG", "Probable disease: ${searchResult.probableDiseaseId}")
        else Log.d("MOJTAG", "Could not find probable disease")
    }
}