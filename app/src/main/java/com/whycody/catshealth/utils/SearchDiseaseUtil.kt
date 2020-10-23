package com.whycody.catshealth.utils

import com.whycody.catshealth.data.SearchResult

interface SearchDiseaseUtil {

    fun getSearchResult(symptomsIds: List<Int>): SearchResult
}