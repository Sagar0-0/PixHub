package com.example.pixhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pixhub.ui.data.ImageViewModel
import com.example.pixhub.utils.ApiType
import com.example.pixhub.utils.ImageTabItem
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalAnimationApi::class, ExperimentalPagerApi::class
)
@Composable
fun ImageSearchGrid(navController: NavHostController, imageViewModel: ImageViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val tabRowItems = listOf(
        ImageTabItem(
            apiType = ApiType.UNSPLASH,
            screen = { Profile() }
        ),
        ImageTabItem(
            apiType = ApiType.PEXELS,
            screen = { Settings() }
        ),
        ImageTabItem(
            apiType = ApiType.PIXABAY,
            screen = { History() }
        )
    )


    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            backgroundColor = Color.Transparent.copy(0.1f),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(50)),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                customIndicator(tabPositions = tabPositions, pagerState = pagerState)
            }
        ) {
            tabRowItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .padding(horizontal = 16.dp)
                        .paint(
                            painter = painterResource(id = item.apiType.logo)
                        ),
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

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun customIndicator(tabPositions: List<TabPosition>, pagerState: PagerState) {
    val transition = updateTransition(pagerState.currentPage, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 100f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 100f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Box(
        Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .border(BorderStroke(2.dp, Color(0xFF00FFCC)), RoundedCornerShape(50))
            .padding(5.dp)
    )
}

