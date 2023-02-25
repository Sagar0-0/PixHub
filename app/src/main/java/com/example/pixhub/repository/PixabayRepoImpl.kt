package com.example.pixhub.repository

import com.example.pixhub.data.remote.PexelsApi
import com.example.pixhub.data.remote.PixabayApi
import com.example.pixhub.data.remote.model.PixabayResponse
import com.example.pixhub.utils.PAGE_SIZE
import com.example.pixhub.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class PixabayRepoImpl @Inject constructor(
    private val pixabayApi: PixabayApi
) : PixabayRepository {
    override suspend fun searchPhotos(query: String, page: Int): Resource<PixabayResponse> {
        val response = try{
            pixabayApi.searchPhotos(query = query, page = page, pageSize = PAGE_SIZE)
        }catch (e: Exception){
            Timber.d(e.message.toString())
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}