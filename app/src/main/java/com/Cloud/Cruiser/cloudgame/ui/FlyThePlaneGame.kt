package com.Cloud.Cruiser.cloudgame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Cloud.Cruiser.cloudgame.Prefs
import com.Cloud.Cruiser.cloudgame.R
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameScreen(
    level: Int = 1,              // Current level
    targetScore: Int = 1000,     // Target score to win the level
    onBack: () -> Unit = {},     // Callback to go back to the main menu
    onMenu: () -> Unit = {}      // Callback when the player loses the game
) {
    var score by remember { mutableIntStateOf(0) }
    var timeLeft by remember { mutableIntStateOf(60) }
    var rocketPosition by remember { mutableFloatStateOf(0f) } // Horizontal position of the rocket
    val rocketSpeed = 5f // Reduced speed for smoother movement
    var coins by remember { mutableStateOf(listOf<Coin>()) } // Generate coins based on level
    var gameOver by remember { mutableStateOf(false) }
    var levelCompleted by remember { mutableStateOf(false) } // Track if the level is completed
    var isMovingLeft by remember { mutableStateOf(false) }
    var isMovingRight by remember { mutableStateOf(false) }

    // Screen width to enforce boundaries

    // Rocket movement logic with boundary checks


    // Countdown timer
    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        }
        else {
            gameOver = true
        }
    }

    LaunchedEffect(levelCompleted) {
        if (levelCompleted) {
            Prefs.passLevel(level)
        }
    }

    if (!gameOver && !levelCompleted) {
        // Game UI
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg),
                    contentScale = ContentScale.FillBounds
                ), // Pinkish background
            contentAlignment = Alignment.Center
        ) {
            val maxHeight = constraints.maxHeight
            val screenWidth = constraints.maxWidth * 0.3f
            LaunchedEffect(isMovingLeft, isMovingRight) {
                while (isMovingLeft || isMovingRight) {
                    delay(16L) // Frame delay for roughly 60 FPS

                    if (isMovingLeft) {
                        // Left boundary limit
                        val leftLimit = -screenWidth + 80f
                        if (rocketPosition > leftLimit) {
                            rocketPosition -= rocketSpeed
                        } else {
                            rocketPosition = leftLimit // Set position exactly to the left limit
                            isMovingLeft = false // Stop further movement to the left
                        }
                    }

                    if (isMovingRight) {
                        // Right boundary limit
                        val rightLimit = screenWidth - 80f
                        if (rocketPosition < rightLimit) {
                            rocketPosition += rocketSpeed
                        } else {
                            rocketPosition = rightLimit // Set position exactly to the right limit
                            isMovingRight = false // Stop further movement to the right
                        }
                    }
                }
            }


            // Display coins
            coins.forEach { coin ->
                CoinItem(coin.x, coin.y)
            }
// Display score
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .paint(
                        painter = painterResource(id = R.drawable.score_bg),
                        contentScale = ContentScale.FillBounds
                    )
                    .align(Alignment.TopStart)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedText(
                    text = "Score: $score",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(150.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.exit), // Replace with your cloud drawable
                contentDescription = "Exit",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(60.dp)
                    .clickable(onClick = onBack),
                contentScale = ContentScale.Fit
            )
            // Display time left
            OutlinedText(
                text = "Time: ${timeLeft}s",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
            // Display rocket
            Box(
                modifier = Modifier
                    .offset(
                        x = rocketPosition.dp,
                        y = (maxHeight * 0.1f).dp
                    ) // 15% from the top of the screen
                    .size(80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rocket), // Replace with your rocket drawable
                    contentDescription = "Rocket",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .pointerInteropFilter {
                        when (it.action) {
                            android.view.MotionEvent.ACTION_DOWN -> {
                                isMovingLeft = true
                            }

                            android.view.MotionEvent.ACTION_UP,
                            android.view.MotionEvent.ACTION_CANCEL -> {
                                isMovingLeft = false
                            }
                        }
                        true
                    }
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .pointerInteropFilter {
                        when (it.action) {
                            android.view.MotionEvent.ACTION_DOWN -> {
                                isMovingRight = true
                            }

                            android.view.MotionEvent.ACTION_UP,
                            android.view.MotionEvent.ACTION_CANCEL -> {
                                isMovingRight = false
                            }
                        }
                        true
                    })
            }
            // Movement buttons (left and right)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 120.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { rocketPosition -= rocketSpeed },
                    modifier = Modifier
                        .pointerInteropFilter {
                            when (it.action) {
                                android.view.MotionEvent.ACTION_DOWN -> {
                                    isMovingLeft = true
                                }

                                android.view.MotionEvent.ACTION_UP,
                                android.view.MotionEvent.ACTION_CANCEL -> {
                                    isMovingLeft = false
                                }
                            }
                            true
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { rocketPosition += rocketSpeed },
                    modifier = Modifier
                        .pointerInteropFilter {
                            when (it.action) {
                                android.view.MotionEvent.ACTION_DOWN -> {
                                    isMovingRight = true
                                }

                                android.view.MotionEvent.ACTION_UP,
                                android.view.MotionEvent.ACTION_CANCEL -> {
                                    isMovingRight = false
                                }
                            }
                            true
                        }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            LaunchedEffect(Unit) {
                coins += generateCoins(level, screenWidth, maxHeight)
                while (timeLeft > 0) {
                    delay(10000L)
                    coins += generateCoins(level, screenWidth, maxHeight)
                }
            }
            // Smooth coin falling and respawn logic
            LaunchedEffect(Unit) {
                while (timeLeft > 0) {
                    delay(16L) // Smooth animation with high FPS (60 frames per second)

                    // Move coins down smoothly
                    coins = coins.map { coin ->
                        val newCoin =
                            coin.copy(y = coin.y + 4) // Move by smaller steps for smoother animation
                        if (newCoin.y > maxHeight) {
                            // Regenerate coin once it falls off the screen, spawn it above the screen
                            Coin(
                                (-screenWidth.roundToInt()..screenWidth.roundToInt()).random()
                                    .toFloat(),
                                Random.nextFloat() * -maxHeight - 50f // Spawn coins slightly higher (adjust this value as needed)
                            )
                        }
                        else {
                            newCoin
                        }
                    }

                    // Collision detection and filtering out collected coins
                    coins = coins.filter { coin ->
                        val coinCollected =
                            coin.y > (maxHeight * 0.1f) && coin.y < (maxHeight * 0.1f + 80) &&
                                    abs(coin.x - rocketPosition) < 50
                        if (coinCollected) {
                            score += 100 // Increment score if the coin is collected
                        }
                        !coinCollected // Only keep coins that are not collected
                    }

                    // Check if the player won the level
                    if (score >= targetScore) {
                        levelCompleted = true
                    }
                }
            }
        }
    }
    else if (levelCompleted) {
        // Level Complete screen, go to next level
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

            // Score Display Box
            Box(
                modifier = Modifier
                    .paint(
                        painter = painterResource(id = R.drawable.big_btn_bg), // Button background
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Level complete\nScore: $score",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Menu Button
            Box(
                modifier = Modifier
                    .paint(
                        painter = painterResource(id = R.drawable.small_btn_bg), // Button background
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(16.dp)
                    .clickable(onClick = onMenu),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Menu",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
        }
    }
    else {
        // Game Over screen
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

            // Score Display Box
            Box(
                modifier = Modifier
                    .paint(
                        painter = painterResource(id = R.drawable.big_btn_bg), // Button background
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedText(
                    text = "Game over\nScore: $score",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Menu Button
            Box(
                modifier = Modifier
                    .paint(
                        painter = painterResource(id = R.drawable.small_btn_bg), // Button background
                        contentScale = ContentScale.FillBounds
                    )
                    .padding(16.dp)
                    .clickable(onClick = onMenu),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Menu",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
            }
        }
    }
}

// Coin data class to store position
data class Coin(val x: Float, val y: Float)

// Coin composable for drawing a coin
@Composable
fun CoinItem(x: Float, y: Float) {
    Image(
        modifier = Modifier
            .offset(x = x.dp, y = y.dp)
            .size(40.dp),
        painter = painterResource(id = R.drawable.coin),
        contentDescription = "Coin",
        contentScale = ContentScale.Fit
    )
}

// Function to generate initial set of coins based on the level
fun generateCoins(level: Int, screenWidth: Float, height: Int): List<Coin> {
    val numberOfCoins = 3 + level // Increase the number of coins per level
    return List(numberOfCoins) {
        // Spawn coins at the top, with a y-coordinate above the screen, and x-position randomly
        Coin(
            (-screenWidth.roundToInt()..screenWidth.roundToInt()).random().toFloat(),
            Random.nextFloat() * -height - 50f
        ) // Coins spawn off-screen above the visible area
    }
}
