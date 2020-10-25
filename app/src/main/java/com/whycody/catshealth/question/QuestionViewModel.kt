package com.whycody.catshealth.question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.utils.SearchDiseaseUtil

class QuestionViewModel(private val searchResult: SearchResult,
                        private val searchDiseaseUtil: SearchDiseaseUtil): ViewModel() {

    var question: MutableLiveData<String> = MutableLiveData(searchResult.currentQuestion?.question)
    var clickedBtnIndex: MutableLiveData<Int> = MutableLiveData(QuestionFragment.DEFAULT_VALUE)
    var questionState: MutableLiveData<Int> = MutableLiveData(QuestionFragment.DEFAULT_VALUE)

    fun btnClicked(noticed: Boolean) {
        setSearchResultVariables(noticed)
        searchDiseaseUtil.refreshSearchResult(searchResult)
        clickedBtnIndex.value = if(noticed) 0 else 1
        questionState.value =
            if(searchResult.currentQuestion == null) QuestionFragment.QUESTION_IS_EMPTY
            else QuestionFragment.QUESTION_IS_FILLED
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
        if(searchResult.currentQuestion != null)
            question.value = searchResult.currentQuestion?.question
        else question.value = ""
        clickedBtnIndex.value = QuestionFragment.DEFAULT_VALUE
        questionState.value = QuestionFragment.DEFAULT_VALUE
    }
}