package com.hapticlabs.hapticwatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import io.hapticlabs.hapticlabsplayer.HapticlabsPlayer
import com.hapticlabs.hapticwatch.presentation.theme.HapticWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    HapticWatchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            HapticButtons()
        }
    }
}

@Composable
fun HapticButtons() {
    val context = LocalContext.current
    val hapticlabsPlayer = remember { HapticlabsPlayer(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            hapticlabsPlayer.play(
                "Android samples/button",
                completionCallback = { println("completed playback") })
            println("Hapticlabs Button Clicked")
        }, enabled = true, content = { Text("Hapticlabs sample") })
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            hapticlabsPlayer.playBuiltIn("Double Click")
            println("Built-in haptics Button Clicked")
        }, enabled = true, content = { Text("Built-in haptics") })
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}