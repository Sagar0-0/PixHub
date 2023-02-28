package com.example.pixhub.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImageItem(imageItem: ImageItem)

    @Delete
    suspend fun deleteImageItem(imageItem: ImageItem)

    @Query("SELECT * FROM saved_images")
    fun observeAllSavedImages(): LiveData<List<ImageItem>>
}