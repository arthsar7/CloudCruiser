package com.Cloud.Cruiser.cloudgame.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Cloud.Cruiser.cloudgame.MainActivity
import com.Cloud.Cruiser.cloudgame.R
import com.Cloud.Cruiser.cloudgame.ui.theme.CloudCruiserTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MenuScreen(
    onPlay: () -> Unit = {},
    onOptions: () -> Unit = {},
    onPrivacy: () -> Unit = {},
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(horizontal = 16.dp)
                .clickable(onClick = onPlay),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Play",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(horizontal = 16.dp)
                .clickable(onClick = onOptions),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Options",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(horizontal = 16.dp)
                .clickable(onClick = onPrivacy),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Policy",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(horizontal = 16.dp)
                .clickable {
                    (context as MainActivity).finish()
                },
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Exit",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
@Preview
fun MenuScreenPreview() {
    CloudCruiserTheme {
        MenuScreen()
    }
}