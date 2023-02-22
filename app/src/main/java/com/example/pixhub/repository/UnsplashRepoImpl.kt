package com.example.pixhub.repository

import com.example.pixhub.data.remote.UnsplashApi
import com.example.pixhub.data.remote.model.UnsplashResponse
import com.example.pixhub.utils.PAGE_SIZE
import com.example.pixhub.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UnsplashRepoImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : UnsplashRepository{

    override suspend fun searchPhotos(query: String, page: Int) : Resource<UnsplashResponse>{
        val response = try{
            unsplashApi.searchPhotos(query = query, page = page, pageSize = PAGE_SIZE)
        }catch (e: Exception){
            return Resource.Error("Got an Exception while calling API")
        }
        return Resource.Success(response)
    }

}