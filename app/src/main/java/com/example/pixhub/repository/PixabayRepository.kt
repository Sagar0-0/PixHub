package com.example.pixhub.repository

import com.example.pixhub.data.remote.model.PexelsResponse
import com.example.pixhub.data.remote.model.PixabayResponse
import com.example.pixhub.utils.Resource

interface PixabayRepository {

    suspend fun searchPhotos(query: String,page: Int) : Resource<PixabayResponse>
}