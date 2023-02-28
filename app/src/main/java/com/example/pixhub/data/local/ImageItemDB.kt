package com.example.pixhub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ImageItem::class],
    version = 1
)
abstract class ImageItemDB : RoomDatabase(){

    abstract fun imagesDao() : ImagesDao
}