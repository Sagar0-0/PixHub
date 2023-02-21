package com.example.pixhub.ui.screens

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pixhub.R
import com.example.pixhub.ui.data.ImageViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSearchGrid(navController: NavHostController ,imageViewModel: ImageViewModel) {
    val images by remember{ imageViewModel.unsplashImagesList }
    val endReached by remember{ imageViewModel.unsplashEndReached }
    val isLoading by remember { imageViewModel.isUnsplashLoading }
    val loadingError by remember { imageViewModel.unsplashError }

    Box(modifier = Modifier.fillMaxSize() ,
        contentAlignment = Alignment.Center
    ){
        if(isLoading){
            CircularProgressIndicator()
        }
        if(images.isNotEmpty()){
            val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
                StaggeredGridCells.Adaptive(minSize = 175.dp)
            } else StaggeredGridCells.Fixed(2)
            LazyVerticalStaggeredGrid(
                modifier = Modifier.padding(10.dp),
                columns = cellConfiguration,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(images) {index,item->
                    AsyncImage(
                        model = item,
                        contentDescription = "Image",
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    )
                    if(index>=images.size/2- 1 && !imageViewModel.unsplashEndReached.value){
                        imageViewModel.searchUnsplashImage()
                    }
                }
            }
        }

        if(loadingError.isNotEmpty()){
            Text(text = imageViewModel.unsplashError.value)
        }
    }

}

