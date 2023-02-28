package com.example.pixhub.ui.screens

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pixhub.R
import com.example.pixhub.ui.data.ImageViewModel
import com.example.pixhub.utils.ApiType
import com.example.pixhub.utils.NO_SCROLL_NUMBER
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalAnimationApi::class
)
@Composable
fun ImageSearchGrid(navController: NavHostController, imageViewModel: ImageViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        var selectedApi by rememberSaveable { mutableStateOf(ApiType.UNSPLASH) }
        ApiHeader(selectedApi = selectedApi, onClick = {
            selectedApi = it
        })

        when(selectedApi){
            ApiType.UNSPLASH->{
                imageViewModel.searchUnsplashImage()
            }
            ApiType.PEXELS->{
                imageViewModel.searchPexelsImage()
            }
            ApiType.PIXABAY->{
                imageViewModel.searchPixabayImage()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            val images by remember { imageViewModel.pixabayImagesList }
            val endReached by remember { imageViewModel.unsplashEndReached }
            val isLoading by remember { imageViewModel.isPixabayLoading }
            val loadingError by remember { imageViewModel.unsplashError }
            val size by remember { mutableStateOf(images.size) }
            val listState = rememberLazyStaggeredGridState()
            val coroutineScope = rememberCoroutineScope()

            if (images.isNotEmpty()) {
                val cellConfiguration =
                    if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
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

            androidx.compose.animation.AnimatedVisibility(
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
}

@Composable
fun ApiHeader(selectedApi: ApiType, onClick: (ApiType) -> Unit) {

    val getBorderStroke: (Boolean) -> BorderStroke? = { selected ->
        if (selected) {
            BorderStroke(1.dp, Color.Cyan.copy(0.8f))
        } else {
            null
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end =  4.dp,top =4.dp)
    ) {
        Card(
            border = getBorderStroke(selectedApi == ApiType.UNSPLASH),
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .weight(1f)
                .height(40.dp)
                .background(color = Color.White.copy(0.8f), shape = RoundedCornerShape(15.dp))
                .clickable { onClick(ApiType.UNSPLASH) }
        ) {
            Image(
                modifier = Modifier.padding(12.dp),
                painter = painterResource(id = R.drawable.unsplash_logo),
                contentDescription = "Unsplash"
            )
        }
        Card(
            border = getBorderStroke(selectedApi == ApiType.PEXELS),
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .weight(1f)
                .height(40.dp)
                .background(color = Color.White.copy(0.8f), shape = RoundedCornerShape(15.dp))
                .clickable { onClick(ApiType.PEXELS) }
        ) {
            Image(
                modifier = Modifier.padding(3.dp),
                painter = painterResource(id = R.drawable.pexels_logo),
                contentDescription = "Pexels"
            )
        }
        Card(
            border = getBorderStroke(selectedApi == ApiType.PIXABAY),
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .weight(1f)
                .height(40.dp)
                .background(color = Color.Green.copy(0.8f), shape = RoundedCornerShape(15.dp))
                .clickable { onClick(ApiType.PIXABAY) }
        ) {
            Image(
                modifier = Modifier.padding(12.dp),
                painter = painterResource(id = R.drawable.pixabay_logo),
                contentDescription = "Pixabay"
            )
        }
    }
}

@Composable
@Preview
fun ApiHeaderPreview() {
    ApiHeader(selectedApi = ApiType.UNSPLASH, onClick = {})
}

