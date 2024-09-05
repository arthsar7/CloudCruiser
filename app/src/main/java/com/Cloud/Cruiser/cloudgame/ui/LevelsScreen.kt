package com.Cloud.Cruiser.cloudgame.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Cloud.Cruiser.cloudgame.Prefs
import com.Cloud.Cruiser.cloudgame.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LevelsScreen(
    onLevelSelected: (Int) -> Unit = {},
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bg), // Background image
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Grid of levels (2 columns layout)
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            for (rowIndex in 0 until 5) { // Create 5 rows (10 levels)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LevelButton(level = rowIndex * 2 + 1, onLevelSelected = onLevelSelected)
                    LevelButton(level = rowIndex * 2 + 2, onLevelSelected = onLevelSelected)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Back button at the bottom
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.small_btn_bg), // Background of back button
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LevelButton(level: Int, onLevelSelected: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp, 50.dp) // Size of each arrow button
            .alpha(if (Prefs.isLevelActive(level)) 1f else 0.5f)
            .paint(
                painter = painterResource(id = R.drawable.arrow_bg), // Arrow background
                contentScale = ContentScale.FillBounds
            )
            .clickable { if (Prefs.isLevelActive(level)) onLevelSelected(level) },
        contentAlignment = Alignment.Center
    ) {
        OutlinedText(
            text = "Level $level",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.alpha(if (Prefs.isLevelActive(level)) 1f else 0.75f)
        )
    }
}