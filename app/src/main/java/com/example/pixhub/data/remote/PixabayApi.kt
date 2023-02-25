package com.example.pixhub.data.remote

import com.example.pixhub.data.remote.model.PixabayResponse
import com.example.pixhub.data.remote.model.UnsplashResponse
import com.example.pixhub.utils.PIXABAY_API_KEY
import com.example.pixhub.utils.UNSPLASH_CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api")
    suspend fun searchPhotos(
        @Query("key") key: String = PIXABAY_API_KEY,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("image_type") type: String = "photo"
    ): PixabayResponse
}