package com.whycody.catshealth.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromString(stringListString: String) = stringListString.split(";").map { it.toInt() }

    @TypeConverter
    fun toString(stringList: List<Int>) = stringList.joinToString(";")
}