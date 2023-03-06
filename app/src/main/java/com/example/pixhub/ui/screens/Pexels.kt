package com.example.pixhub.ui.screens

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pixhub.R
import com.example.pixhub.ui.data.ImageViewModel
import com.example.pixhub.utils.NO_SCROLL_NUMBER
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Pexels(imageViewModel: ImageViewModel,navController: NavHostController) {

    if(imageViewModel.pexelsImagesList.value.isEmpty())imageViewModel.searchPexelsImage()
    Box(modifier = Modifier.fillMaxSize()) {
        val images by remember { imageViewModel.pexelsImagesList }
        val endReached by remember { imageViewModel.pexelsEndReached }
        val isLoading by remember { imageViewModel.isPexelsLoading }
        val loadingError by remember { imageViewModel.pexelsError }
        val size by remember { mutableStateOf(images.size) }

        val listState = rememberLazyStaggeredGridState()
        val coroutineScope = rememberCoroutineScope()

        if (images.isNotEmpty()) {
            val cellConfiguration =
                if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    StaggeredGridCells.Adaptive(minSize = 175.dp)
                } else StaggeredGridCells.Fixed(2)
            LazyVerticalStaggeredGrid(
                state = listState,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
                columns = cellConfiguration,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(images) { index, item ->
                    AsyncImage(
                        model = item,
                        contentDescription = "Image",
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .combinedClickable(
                                onClick = {
                                    navController.navigate(Screen.Home.title) {
                                        launchSingleTop = true
                                    }
                                },
                                onLongClick = {

                                }
                            ),
                    )
                    if (index >= size - 1 && !endReached && !isLoading) {
                        imageViewModel.searchPixabayImage()
                    }
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd),
            visible = listState.firstVisibleItemIndex > NO_SCROLL_NUMBER,
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
            exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
        ) {
            Icon(
                modifier = Modifier
                    .padding(15.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .size(50.dp)
                    .background(Color.Cyan)
                    .clickable {
                        coroutineScope.launch {
                            listState.animateScrollToItem(index = 0)
                        }
                    },
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Up"
            )
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}