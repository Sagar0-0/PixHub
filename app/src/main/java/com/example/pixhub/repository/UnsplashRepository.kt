package com.example.pixhub.repository

import com.example.pixhub.data.remote.model.UnsplashResponse
import com.example.pixhub.utils.Resource

interface UnsplashRepository {
    suspend fun searchPhotos(query: String,page: Int) : Resource<UnsplashResponse>
}