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
        searchResult.alreadyAskedDiseasesQuestionsIds = mutableListOf()
        searchResult.alreadyAskedSymptomsIds = mutableListOf()
        searchResult.probableDiseaseId = null
        searchResult.possibleDiseases = diseaseRepository.getDiseasesWithSymptoms(symptomsIds)
        searchResult.symptomsIds = symptomsIds.toMutableList()
        searchProbableDisease(searchResult)
        setCurrentQuestion(searchResult)
    }

    override fun refreshSearchResult(searchResult: SearchResult) {
        searchResult.possibleDiseases = diseaseRepository.getDiseasesWithSymptoms(searchResult.symptomsIds)
        searchProbableDisease(searchResult)
        setCurrentQuestion(searchResult)
    }

    private fun setCurrentQuestion(searchResult: SearchResult) {
        val probableDisease = getCheckingDisease(searchResult) ?: return
        checkPossibleDisease(probableDisease, searchResult)
        searchResult.currentQuestion = getCurrentQuestion(probableDisease, searchResult)
    }

    private fun getCheckingDisease(searchResult: SearchResult) =
        searchResult.possibleDiseases.filter {
            it.symptomsNeeds.size <= 2 && !searchResult.alreadyAskedDiseasesQuestionsIds.contains(it.id)
        }.sortedWith(compareBy<DiseaseResult> {
            it.symptomsNeeds.count { id -> searchResult.alreadyAskedSymptomsIds.contains(id)}
        }.thenByDescending {
            it.symptomsPercentage
        }).firstOrNull()

    private fun checkPossibleDisease(diseaseResult: DiseaseResult, searchResult: SearchResult) {
        if(diseaseResult.symptomsNeeds.any { !searchResult.alreadyAskedSymptomsIds.contains(it) } ||
            searchResult.alreadyAskedSymptomsIds.count { diseaseResult.symptomsNeeds.contains(it) } > 1)
            return
        else searchResult.probableDiseaseId = diseaseResult.id
    }

    private fun getCurrentQuestion(probableDisease: DiseaseResult, searchResult: SearchResult) =
        when {
            searchResult.probableDiseaseId != null ||
                    getNotAskedSymptom(probableDisease, searchResult) == null -> null
            probableDisease.symptomsNeeds.isNotEmpty() ->
                getSymptomQuestion(probableDisease, searchResult)
            !diseaseRepository.getDisease(probableDisease.id).additionalQuestion.isNullOrEmpty() ->
                getDiseaseQuestion(probableDisease)
            else -> null
        }

    private fun getSymptomQuestion(probableDisease: DiseaseResult, searchResult: SearchResult): Question {
        val notAskedSymptom = getNotAskedSymptom(probableDisease, searchResult)
        return Question(getQuestionName(QuestionFragment.SYMPTOM_QUESTION, notAskedSymptom!!),
            QuestionFragment.SYMPTOM_QUESTION, notAskedSymptom)
    }

    private fun getDiseaseQuestion(probableDisease: DiseaseResult) = Question(
        getQuestionName(QuestionFragment.ADDITIONAL_QUESTION, probableDisease.id),
        QuestionFragment.ADDITIONAL_QUESTION, probableDisease.id)

    private fun getNotAskedSymptom(diseaseResult: DiseaseResult, searchResult: SearchResult) =
        diseaseResult.symptomsNeeds.find { id -> !searchResult.alreadyAskedSymptomsIds.contains(id) }

    private fun getQuestionName(type: Int, askingObjectId: Int) =
        if(type == QuestionFragment.ADDITIONAL_QUESTION)
            diseaseRepository.getDisease(askingObjectId).additionalQuestion!!
        else symptomRepository.getSymptom(askingObjectId).descQuestion

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