package com.whycody.catshealth.utils

import com.whycody.catshealth.data.DiseaseResult
import com.whycody.catshealth.data.Question
import com.whycody.catshealth.data.SearchResult
import com.whycody.catshealth.data.disease.DiseaseRepository
import com.whycody.catshealth.data.symptom.SymptomRepository
import com.whycody.catshealth.question.QuestionFragment

class SearchDiseaseUtilImpl(private val diseaseRepository: DiseaseRepository,
                            private val symptomRepository: SymptomRepository): SearchDiseaseUtil {

    override fun setupSearchResult(searchResult: SearchResult, symptomsIds: List<Int>) {
        val possibleDiseases = diseaseRepository.getDiseasesWithSymptoms(symptomsIds)
        searchResult.possibleDiseases = possibleDiseases
        searchProbableDisease(searchResult)
        setCurrentQuestion(searchResult)
    }

    private fun setCurrentQuestion(searchResult: SearchResult) {
        val probableDisease = getCheckingDisease(searchResult)
        if(searchResult.probableDiseaseId == null)
            searchResult.currentQuestion = getCurrentQuestion(probableDisease)
    }

    private fun getCheckingDisease(searchResult: SearchResult): DiseaseResult? {
        return searchResult.possibleDiseases.find { diseaseResult ->
            !diseaseResult.symptomsNeeds.any { searchResult.alreadyAskedSymptomsIds.contains(it) }
                    && !searchResult.alreadyAskedDiseasesQuestionsIds.contains(diseaseResult.id)
        }
    }

    private fun getCurrentQuestion(probableDisease: DiseaseResult?) =
        when {
            probableDisease == null -> null
            probableDisease.symptomsNeeds.isNotEmpty() ->
                Question(getQuestionName(QuestionFragment.SYMPTOM_QUESTION, probableDisease.symptomsNeeds[0]),
                    QuestionFragment.SYMPTOM_QUESTION, probableDisease.symptomsNeeds[0])
            !diseaseRepository.getDisease(probableDisease.id).additionalQuestion.isNullOrEmpty() ->
                Question(getQuestionName(QuestionFragment.ADDITIONAL_QUESTION, probableDisease.id),
                    QuestionFragment.ADDITIONAL_QUESTION, probableDisease.id)
            else -> null
        }

    private fun getQuestionName(type: Int, askingObjectId: Int) =
        if(type == QuestionFragment.ADDITIONAL_QUESTION)
            diseaseRepository.getDisease(askingObjectId).additionalQuestion!!
        else "Czy zauważyłeś u swojego kota ten objaw? ${symptomRepository.getSymptom(askingObjectId).name}"

    private fun searchProbableDisease(searchResult: SearchResult) {
        searchResult.possibleDiseases.forEach { diseaseResult ->
            if (possibleDiseaseIsTheMostProbable(diseaseResult))
                searchResult.probableDiseaseId = diseaseResult.id
        }
    }

    private fun possibleDiseaseIsTheMostProbable(possibleDisease: DiseaseResult) =
        possibleDisease.symptomsNeeds.isEmpty() && !diseaseHasAdditionalQuestion(possibleDisease.id)

    private fun diseaseHasAdditionalQuestion(id: Int) =
        diseaseRepository.getDisease(id).additionalQuestion != null
}