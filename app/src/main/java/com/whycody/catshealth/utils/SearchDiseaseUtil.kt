package com.whycody.catshealth.utils

import com.whycody.catshealth.data.SearchResult

interface SearchDiseaseUtil {

    fun setupSearchResult(searchResult: SearchResult, symptomsIds: List<Int>)

    fun refreshSearchResult(searchResult: SearchResult)
}