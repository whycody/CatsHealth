package com.whycody.catshealth.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "symptoms")
data class Symptom(
    @PrimaryKey @NonNull val id: Int,
    val name: String) {

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
    @ColumnInfo(name = "symptoms_ids") val symptoms: List<Int>,
    val treatment: String,
    @ColumnInfo(name = "additional_question") val additionalQuestion: String? = null,
    @ColumnInfo(name = "article_link") val articleLink: String,
    val priority: Int = 1)

data class SearchResult(
    val probableDisease: Disease? = null,
    val possibleDiseases: List<Disease>,
    var alreadyAskedSymptoms: List<Symptom> = listOf())
