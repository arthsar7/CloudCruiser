package com.Cloud.Cruiser.cloudgame.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.Cloud.Cruiser.cloudgame.Prefs
import com.Cloud.Cruiser.cloudgame.SoundManager

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "loading") {
        composable("loading") {
            LoadingScreen {
                if (Prefs.isPrivacyAccepted) {
                    navController.navigate("menu") {
                        popUpTo("loading") { inclusive = true }
                    }
                }
                else {
                    navController.navigate("welcome") {
                        popUpTo("loading") { inclusive = true }
                    }
                }
            }
        }
        composable("welcome") {
            WelcomeScreen(
                onOk = {
                    Prefs.isPrivacyAccepted = true
                    navController.navigate("menu") {
                        popUpTo("welcome") { inclusive = true }
                    }
                    SoundManager.playSound()
                },
                onPrivacy = {
                    navController.navigate("privacy")
                    SoundManager.playSound()
                }
            )
        }
        composable("privacy") {
            PrivacyPolicyScreen(
                onBack = {
                    navController.popBackStack()
                    SoundManager.playSound()
                },
            )
        }
        composable("menu") {
            MenuScreen(
                onPlay = {
                    SoundManager.playSound()
                    navController.navigate("levels")
                },
                onOptions = {
                    SoundManager.playSound()
                    navController.navigate("options")
                },
                onPrivacy = {
                    SoundManager.playSound()
                    navController.navigate("privacy")
                }
            )
        }
        composable("levels") {
            LevelsScreen(
                onBack = {
                    navController.popBackStack()
                    SoundManager.playSound()
                },
                onLevelSelected = {
                    SoundManager.playSound()
                    navController.navigate("game/$it")
                }
            )
        }
        composable("options") {
            var musicVolume by remember {
                mutableFloatStateOf(Prefs.musicVolume)
            }
            var soundVolume by remember {
                mutableFloatStateOf(Prefs.soundVolume)
            }
            OptionsScreen(
                onBack = {
                    navController.popBackStack()
                    SoundManager.playSound()
                },
                musicLevel = musicVolume,
                onMusicChange = {
                    musicVolume = it
                    Prefs.musicVolume = it
                    SoundManager.setMusicVolume()
                },
                soundLevel = soundVolume,
                onSoundChange = {
                    soundVolume = it
                    Prefs.soundVolume = it
                    SoundManager.setSoundVolume()
                }
            )
        }
        composable("game/{level}") {
            val level = it.arguments?.getString("level")?.toInt() ?: 1
            GameScreen(
                level = level,
                targetScore = level * 1000,
                onBack = {
                    navController.popBackStack()
                    SoundManager.playSound()
                },
                onMenu = {
                    SoundManager.playSound()
                    navController.navigate("menu") {
                        popUpTo("levels") { inclusive = true }
                    }
                },

                )
        }
    }
}