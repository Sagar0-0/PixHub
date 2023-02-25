package com.example.pixhub.ui.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhub.repository.PexelsRepository
import com.example.pixhub.repository.UnsplashRepository
import com.example.pixhub.utils.PAGE_SIZE
import com.example.pixhub.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val unsplashRepo: UnsplashRepository,
    private val pexelsRepo: PexelsRepository
) : ViewModel() {
    private var unsplashCurrentPage = 1
    var unsplashImagesList = mutableStateOf<List<String>>(listOf())
    var searchQuery = mutableStateOf("")
    var isUnsplashLoading = mutableStateOf(false)
    var unsplashEndReached = mutableStateOf(false)
    var unsplashError = mutableStateOf("")


    private var pexelsCurrentPage = 1
    var pexelsImagesList = mutableStateOf<List<String>>(listOf())
    var isPexelsLoading = mutableStateOf(false)
    var pexelsEndReached = mutableStateOf(false)
    var pexelsError = mutableStateOf("")

    fun searchUnsplashImage(query: String = searchQuery.value) {
        if(query==searchQuery.value) {
            unsplashCurrentPage++
        }else{
            isUnsplashLoading.value = true
            unsplashImagesList.value = listOf()
            unsplashCurrentPage = 1
            searchQuery.value = query
        }
        unsplashError.value = ""
        viewModelScope.launch {
            when (val result = unsplashRepo.searchPhotos(query, unsplashCurrentPage)) {
                is Resource.Success -> {
                    unsplashEndReached.value =
                        (unsplashCurrentPage * PAGE_SIZE) >= result.data!!.total

                    val list = result.data.results.map {
                        it.urls.thumb
                    }
                    unsplashImagesList.value += list
                }
                is Resource.Error -> {
                    unsplashError.value = result.message!!
                }
            }
            isUnsplashLoading.value = false
        }
    }

    fun searchPexelsImage(query: String = searchQuery.value) {
        if(query==searchQuery.value) {
            pexelsCurrentPage++
        }else{
            isPexelsLoading.value = true
            pexelsImagesList.value = listOf()
            pexelsCurrentPage = 1
            searchQuery.value = query
        }
        pexelsError.value = ""
        viewModelScope.launch {
            when (val result = pexelsRepo.searchPhotos(query, pexelsCurrentPage)) {
                is Resource.Success -> {
                    pexelsEndReached.value =
                        (pexelsCurrentPage * PAGE_SIZE) >= result.data!!.totalResults

                    val list = result.data.photos.map {
                        it.src.original
                    }
                    pexelsImagesList.value += list
                }
                is Resource.Error -> {
                    pexelsError.value = result.message!!
                }
            }
            isPexelsLoading.value = false
        }
    }
}