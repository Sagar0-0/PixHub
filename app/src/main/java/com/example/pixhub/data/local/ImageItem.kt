package com.example.pixhub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pixhub.utils.ApiType
import com.example.pixhub.utils.ApiTypeConverter

@Entity(tableName = "saved_images")
data class ImageItem(
    val apiType: ApiType,
    val imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
