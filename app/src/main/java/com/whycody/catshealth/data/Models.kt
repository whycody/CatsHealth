package com.whycody.catshealth.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "symptoms")
data class Symptom(
    @PrimaryKey @NonNull val id: Int,
    val name: String,
    @ColumnInfo(name = "desc_question") val descQuestion: String) {

    override fun toString(): String {
        return "Symptom $id: $name"
    }
}

data class SymptomItem(
    val symptom: Symptom,
    var checked: Boolean = false)

@Entity(tableName = "diseases")
data class Disease(
    @PrimaryKey @NotNull val id: Int,
    @NotNull val name: String,
    val type: String,
    @ColumnInfo(name = "symptoms_names") val symptomsNames: String,
    @ColumnInfo(name = "symptoms_ids") val symptomsIds: List<Int>,
    val treatment: String,
    @ColumnInfo(name = "additional_question") val additionalQuestion: String? = null,
    @ColumnInfo(name = "article_link") val articleLink: String,
    val priority: Int = 1)

data class DiseaseResult(
    val id: Int,
    val priority: Int = 1,
    val symptomsIds: List<Int>,
    val symptomsContains: List<Int>,
    val symptomsNeeds: List<Int>)

data class SearchResult(
    var probableDiseaseId: Int? = null,
    var possibleDiseases: List<DiseaseResult> = listOf(),
    var alreadyAskedSymptomsIds: MutableList<Int> = mutableListOf(),
    var alreadyAskedDiseasesQuestionsIds: MutableList<Int> = mutableListOf(),
    var currentQuestion: Question? = null)

data class Question(
    val question: String,
    val type: Int,
    val askingObjectId: Int)
