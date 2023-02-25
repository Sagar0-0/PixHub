package com.example.pixhub.repository

import com.example.pixhub.data.remote.PexelsApi
import com.example.pixhub.data.remote.model.PexelsResponse
import com.example.pixhub.utils.PAGE_SIZE
import com.example.pixhub.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PexelsRepoImpl @Inject constructor(
    private val pexelsApi: PexelsApi
) : PexelsRepository {

    override suspend fun searchPhotos(query: String, page: Int): Resource<PexelsResponse> {
        val response = try{
            pexelsApi.searchPhotos(query = query, page = page, pageSize = PAGE_SIZE)
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

}