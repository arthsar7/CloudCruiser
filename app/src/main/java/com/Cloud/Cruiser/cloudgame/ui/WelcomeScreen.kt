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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Cloud.Cruiser.cloudgame.R
import com.Cloud.Cruiser.cloudgame.ui.theme.CloudCruiserTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WelcomeScreen(
    onPrivacy: () -> Unit = {},
    onOk: () -> Unit = {}
) {
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

        OutlinedText(
            text = "Welcome",
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.text_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "The program is\nfree and securely\nprotected.\n\nClick \"OK\" to\naccept our\nPrivacy Policy.",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.big_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(16.dp)
                .clickable(onClick = onPrivacy),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Privacy\nPolicy",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(horizontal = 16.dp)
                .clickable(onClick = onOk),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "OK",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
@Preview
fun WelcomeScreenPreview() {
    CloudCruiserTheme {
        WelcomeScreen()
    }
}