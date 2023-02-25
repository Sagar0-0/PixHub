package com.example.pixhub.repository

import com.example.pixhub.data.remote.model.PexelsResponse
import com.example.pixhub.data.remote.model.UnsplashResponse
import com.example.pixhub.utils.Resource

interface PexelsRepository {

    suspend fun searchPhotos(query: String,page: Int) : Resource<PexelsResponse>
}