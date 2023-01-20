package com.contelli.jetnote.util

import androidx.room.TypeConverter
import java.util.*

class UuidConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String?): UUID? {
        return UUID.fromString(string)
    }
}