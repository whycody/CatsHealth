package com.whycody.catshealth.utils

import com.whycody.catshealth.data.DiseaseResult
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.data.disease.DiseaseRepository

class SearchDiseaseUtilImpl(private val diseaseRepository: DiseaseRepository): SearchDiseaseUtil {

    override fun getSearchResult(symptomsIds: List<Int>): SearchResult {
        val searchResult = SearchResult()
        val possibleDiseases = diseaseRepository.getDiseasesWithSymptoms(symptomsIds)
        searchResult.possibleDiseases = possibleDiseases
        searchProbableDisease(possibleDiseases, searchResult)
        return searchResult
    }

    private fun searchProbableDisease(possibleDiseases: List<DiseaseResult>, searchResult: SearchResult) {
        possibleDiseases.forEach { diseaseResult ->
            if (possibleDiseaseIsTheMostProbable(diseaseResult))
                searchResult.probableDiseaseId = diseaseResult.id
        }
    }

    private fun possibleDiseaseIsTheMostProbable(possibleDisease: DiseaseResult) =
        possibleDisease.symptomsNeeds == 0 && diseaseHasAdditionalQuestion(possibleDisease.id)

    private fun diseaseHasAdditionalQuestion(id: Int) =
        diseaseRepository.getDisease(id).additionalQuestion == null
}