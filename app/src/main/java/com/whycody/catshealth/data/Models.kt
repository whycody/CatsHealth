package com.whycody.catshealth.data

import androidx.annotation.NonNull
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
    val symptoms: List<Int>,
    val type: Int,
    val priority: Int = 1,
    val description: String,
    val additionalQuestion: String? = null)

data class SearchResult(
    val probableDisease: Disease? = null,
    val possibleDiseases: List<Disease>,
    val alreadyAskedSymptoms: List<Symptom> = listOf())
