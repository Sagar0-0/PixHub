package com.example.pixhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pixhub.R
import com.example.pixhub.ui.data.ImageViewModel
import com.example.pixhub.utils.ApiType
import com.example.pixhub.utils.ImageTabItem
import com.example.pixhub.utils.customDrawerShape
import com.example.pixhub.utils.toPx
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun ImageSearchGrid(navController: NavHostController, imageViewModel: ImageViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    coroutineScope.launch { imageViewModel.searchPexelsImage() }
    coroutineScope.launch { imageViewModel.searchPixabayImage() }


    val tabRowItems = listOf(
        ImageTabItem(
            apiType = ApiType.UNSPLASH,
            screen = { Unsplash(imageViewModel = imageViewModel, navController = navController) }
        ),
        ImageTabItem(
            apiType = ApiType.PEXELS,
            screen = { Pexels(imageViewModel = imageViewModel, navController = navController) }
        ),
        ImageTabItem(
            apiType = ApiType.PIXABAY,
            screen = { Pixabay(imageViewModel = imageViewModel, navController = navController) }
        )
    )


    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            modifier = Modifier.padding(horizontal = 5.dp),
            backgroundColor = Color.Transparent,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colors.secondary
                )
            }
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .paint(painterResource(id = item.apiType.logo)),
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }
                )
            }
        }

        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen()
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
            .padding(start = 4.dp, end = 4.dp, top = 4.dp)
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

