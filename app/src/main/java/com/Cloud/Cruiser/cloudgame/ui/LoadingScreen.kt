package com.Cloud.Cruiser.cloudgame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.Cloud.Cruiser.cloudgame.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    onLoaded: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onLoaded()
    }
    Image(
        painter = painterResource(id = R.drawable.loading), contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}