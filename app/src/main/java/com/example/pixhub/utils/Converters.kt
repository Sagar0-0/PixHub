package com.example.pixhub.utils

import androidx.room.TypeConverter
import com.example.pixhub.R

class ApiTypeConverter {

    @TypeConverter
    fun toApiType(value: Int): ApiType {
        return when (value) {
            R.drawable.pixabay_logo -> ApiType.PIXABAY
            R.drawable.pexels_logo -> ApiType.PEXELS
            else -> {
                ApiType.UNSPLASH
            }
        }
    }

    @TypeConverter
    fun fromApiType(value: ApiType) = value.logo
}