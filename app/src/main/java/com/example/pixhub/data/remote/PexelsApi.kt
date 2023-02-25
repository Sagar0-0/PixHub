package com.example.pixhub.data.remote

import com.example.pixhub.data.remote.model.PexelsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {
    //https://api.pexels.com/v1/search?query=gun&per_page=20&page=1
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): PexelsResponse
}