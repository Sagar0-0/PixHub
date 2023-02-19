package com.example.pixhub.ui.screens

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pixhub.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun ImageSearchGrid() {
    val images: List<String> = listOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
        "https://images.pexels.com/photos/674010/pexels-photo-674010.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        "https://imgd.aeplcdn.com/1056x594/n/cw/ec/102709/ntorq-125-right-front-three-quarter.jpeg?isig=0&q=75",
        "https://images.unsplash.com/photo-1495344517868-8ebaf0a2044a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8c2VhcmNofGVufDB8fDB8fA%3D%3D&w=1000&q=80",
        "https://static.vecteezy.com/packs/media/vectors/term-bg-1-3d6355ab.jpg",
        "https://images.ctfassets.net/kdawwlsweh27/2LtummpjO849eQ83yGGiUN/b33c73279163c84b65241cdfcc1c8844/Fresh_Stock_Content.jpg",
        "https://images.ctfassets.net/hrltx12pl8hq/5596z2BCR9KmT1KeRBrOQa/4070fd4e2f1a13f71c2c46afeb18e41c/shutterstock_451077043-hero1.jpg",
        "https://media.istockphoto.com/id/1368264124/photo/extreme-close-up-of-thrashing-emerald-ocean-waves.jpg?b=1&s=170667a&w=0&k=20&c=qha_PaU54cu9QCu1UTlORP4-sW0MqLGERkdFKmC06lI=",
        "https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?cs=srgb&dl=pexels-pixabay-268533.jpg&fm=jpg"
    )

    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        StaggeredGridCells.Adaptive(minSize = 175.dp)
    } else StaggeredGridCells.Fixed(2)
    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(10.dp),
        columns = cellConfiguration,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(images, key = { it }) {
            AsyncImage(
                model = it,
                contentDescription = "Image",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            )
        }
    }
}

