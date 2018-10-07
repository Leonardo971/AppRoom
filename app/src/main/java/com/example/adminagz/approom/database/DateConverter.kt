package com.example.adminagz.approom.database

import android.arch.persistence.room.TypeConverter
import java.util.*  // IMPORTANTE PARA LA FECHA

class DateConverter {
    @TypeConverter
    fun toDate(timestamp:Long?): Date?{
        return  if (timestamp != null) Date(timestamp) else null
    }

    @TypeConverter
    fun toTimeStamp(date: Date?): Long? = date?.time
}