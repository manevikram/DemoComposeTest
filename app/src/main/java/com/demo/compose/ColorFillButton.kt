package com.demo.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Created by Vikram Mane on 10/28/24.
 */

@Composable
fun ColorFillButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Click me",
    backgroundColor: Color = Color.Blue,
    contentColor: Color = Color.White,
    animationDuration: Int = 300 // Milliseconds
) {
    var isClicked by remember { mutableStateOf(false) }
    val animatedBackgroundColor = animateColorAsState(
        targetValue = if (isClicked) backgroundColor.copy(alpha = 0.5f) else backgroundColor,
        animationSpec = tween(durationMillis = animationDuration)
    )

    Box(
        modifier = modifier
            .clickable {
                isClicked = true
                onClick()
            }
            .background(color = animatedBackgroundColor.value, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun AnimatedFillButton(
    text: String,
    onClick: () -> Unit,
    animationDurationMillis: Int = 1000,
    startColor: Color = Color.Gray,
    endColor: Color = Color.Blue
) {
    val animatedWidth = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .background(startColor)
            .clickable {
                coroutineScope.launch {
                    animatedWidth.snapTo(0f) // Reset the animation
                    animatedWidth.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = animationDurationMillis, easing = LinearEasing)
                    )
                    onClick()
                }
            }
            .width(200.dp)
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background fill animation
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedWidth.value) // Expands from left to right
                .background(endColor)
        )

        // Button Text
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}