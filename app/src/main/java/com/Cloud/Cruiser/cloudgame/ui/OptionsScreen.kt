package com.Cloud.Cruiser.cloudgame.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
fun OptionsScreen(
    onBack: () -> Unit = {},
    musicLevel: Float = 0.5f,
    onMusicChange: (Float) -> Unit = {},
    soundLevel: Float = 0.5f,
    onSoundChange: (Float) -> Unit = {}
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

        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.big_btn_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Options",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .paint(
                    painter = painterResource(id = R.drawable.text_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText(
                text = "Music:",
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )
            CustomSlider(
                value = musicLevel,
                onValueChange = onMusicChange
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedText(
                text = "Sound:",
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )
            CustomSlider(
                value = soundLevel,
                onValueChange = onSoundChange
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
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center
        ) {
            OutlinedText(
                text = "Back",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
    }
}

@Preview
@Composable
fun OptionsScreenPreview() {
    CloudCruiserTheme {
        OptionsScreen()
    }
}


@Composable
fun CustomSlider(
    value: Float = 0.5f,
    onValueChange: (Float) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(300.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(25.dp)) // rounded shape for the track
            .padding(4.dp)
            .background(Color.Transparent)
            .border(
                width = 4.dp,
                color = Color(0xFF76012B),
                shape = RoundedCornerShape(25.dp)
            ), // Transparent center to give it border effect
        contentAlignment = Alignment.CenterStart
    ) {
        // Draw the slider track
        if (value != 0f) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .padding(start = 2.dp)
            ) {
                // Draw the background track
                drawRoundRect(
                    color = Color(0xFF76012B), // border color (maroon)
                    size = Size(size.width * value, size.height), // full size for the border
                    cornerRadius = CornerRadius(100f, 100f),
                    style = Stroke(10f)
                )

                // Draw the inner green track based on slider value
                val trackWidth = size.width * value
                drawRoundRect(
                    color = Color(0xFF4CAF50), // green track color
                    size = Size(trackWidth, size.height),
                    cornerRadius = CornerRadius(100f, 100f),
                )
            }
        }

        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .width(250.dp)
                .alpha(0f),
            colors = SliderDefaults.colors(
                thumbColor = Color.Transparent, // Hide original thumb
                activeTrackColor = Color.Transparent, // Hide original track
                inactiveTrackColor = Color.Transparent
            )
        )
    }

    // Slider control (hidden but for updating value)

}

@Preview(showBackground = true)
@Composable
fun CustomSliderPreview() {
    CustomSlider()
}