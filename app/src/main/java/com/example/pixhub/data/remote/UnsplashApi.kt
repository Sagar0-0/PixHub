package com.example.pixhub.data.remote

import com.example.pixhub.data.remote.model.UnsplashResponse
import com.example.pixhub.utils.UNSPLASH_CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") clientId: String = UNSPLASH_CLIENT_ID,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): UnsplashResponse
}