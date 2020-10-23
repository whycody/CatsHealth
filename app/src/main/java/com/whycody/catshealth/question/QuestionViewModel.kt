package com.whycody.catshealth.question

import androidx.lifecycle.ViewModel
import com.whycody.catshealth.data.SearchResult

class QuestionViewModel(private var searchResult: SearchResult): ViewModel() {

    val question = searchResult.currentQuestion?.question

    fun btnClicked(noticed: Boolean) {

    }
}