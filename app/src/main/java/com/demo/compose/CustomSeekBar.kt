package com.demo.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Created by Vikram Mane on 2/10/24.
 */

@Composable
fun CustomSeekBar(){

    val context = LocalContext.current
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Discrete SeekBar with tick marks
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            },
            valueRange = 0f..100f,
            steps = 10,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp),
            colors = SliderDefaults.colors(
                activeTickColor = Color.Green, // Color of active tick marks
                inactiveTickColor = Color.Red, // Color of inactive tick marks
                activeTrackColor = Color.Green, // Color of active track
                inactiveTrackColor = Color.Red, // Color of inactive track
                thumbColor = Color.Black // Color of the thumb
            )
        )
    }
}