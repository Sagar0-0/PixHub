package com.example.pixhub.ui.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhub.repository.UnsplashRepository
import com.example.pixhub.utils.PAGE_SIZE
import com.example.pixhub.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: UnsplashRepository
) : ViewModel() {
    private var unsplashCurrentPage = 1
    var unsplashImagesList = mutableStateOf<List<String>>(listOf())
    var searchQuery = mutableStateOf("")
    var isUnsplashLoading = mutableStateOf(false)
    var unsplashEndReached = mutableStateOf(false)
    var unsplashError = mutableStateOf("")

    fun searchUnsplashImage(query: String = searchQuery.value) {
        if(query==searchQuery.value) {
            unsplashCurrentPage++
        }else{
            isUnsplashLoading.value = true
            unsplashImagesList.value = listOf()
            unsplashCurrentPage = 1
            searchQuery.value = query
        }
        viewModelScope.launch {
            when (val result = repository.searchPhotos(query, unsplashCurrentPage)) {
                is Resource.Success -> {
                    unsplashEndReached.value =
                        (unsplashCurrentPage * PAGE_SIZE) >= result.data!!.total

                    val list = result.data.results.map {
                        it.urls.thumb
                    }
                    unsplashImagesList.value = unsplashImagesList.value.plus(list)
                }
                is Resource.Error -> {
                    unsplashError.value = result.message!!
                }
            }
            isUnsplashLoading.value = false
        }
    }
}