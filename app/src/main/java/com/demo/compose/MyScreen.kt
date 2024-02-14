package com.demo.compose

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay

/**
 * Created by Vikram Mane on 2/10/24.
 */

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MyScreen() {
    var showSpotlight by remember { mutableStateOf(true) }
    var buttonRect by remember { mutableStateOf(Rect(0f, 0f, 0f, 0f)) }
    val spotlightRadius = maxOf(buttonRect.width, buttonRect.height) / 2

    BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { showSpotlight = !showSpotlight },
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    val position = layoutCoordinates.positionInRoot()
                    val size = IntSize(layoutCoordinates.size.width, layoutCoordinates.size.height)
                    buttonRect = Rect(position.x, position.y, position.x + size.width, position.y + size.height)
                }
        ) {
            Text("Hello Linkedin")
        }

        if (showSpotlight) {
            SpotlightEffect(targetRect = buttonRect, baseRadius = spotlightRadius)
        }
    }
}

@Composable
fun SpotlightEffect(targetRect: Rect, baseRadius: Float) {
    var targetRadius by remember { mutableStateOf(baseRadius) }
    val animatedRadius by animateFloatAsState(
        targetValue = targetRadius,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(Unit) {
        while (true) {
            targetRadius = if (targetRadius == baseRadius) baseRadius + 220F else baseRadius
            delay(1000)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        with(drawContext.canvas.nativeCanvas) {
            val checkPoint = saveLayer(null, null)
            drawRect(color = Color.Black.copy(alpha = 0.85f))

            val maskPaint = Paint().apply {
                color = Color(0f, 0f, 0f, 0f)
                blendMode = BlendMode.Clear
            }

            val spotlightCenterX = targetRect.left + targetRect.width / 2
            val spotlightCenterY = targetRect.top + targetRect.height / 2

            drawCircle(
                spotlightCenterX,
                spotlightCenterY,
                animatedRadius,
                maskPaint.asFrameworkPaint()
            )

            restoreToCount(checkPoint)
        }
    }
}